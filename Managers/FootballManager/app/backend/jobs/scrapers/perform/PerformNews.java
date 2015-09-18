package backend.jobs.scrapers.perform;

import backend.HecticusThread;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hecticus.rackspacecloud.RackspaceDelete;
import exceptions.BadConfigException;
import models.*;
import models.football.News;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by plesse on 4/15/15.
 */
public class PerformNews extends HecticusThread {

    public static final int IMGRESIZE_EXACT = 0;
    public static final int IMGRESIZE_KEEPASPECT_WIDTH = 1;
    public static final int IMGRESIZE_KEEPASPECT_HEIGHT = 2;

    private String requestDomain;
    private String feedName;
    private String outletAuthToken;
    private String performImageHost;
    private String temporalDirectory;
    private Language finalLanguage;

    protected Apps app;

    public PerformNews() {
        this.setActTime(System.currentTimeMillis());
        this.setInitTime(System.currentTimeMillis());
        this.setPrevTime(System.currentTimeMillis());
        //set name
        this.setName("PerformNews-" + System.currentTimeMillis());
    }

    @Override
    public void process(Map args) {
        try {
//            Utils.printToLog(PerformNews.class,null,"Iniciando LanceNewsScraper",false,null,"support-level-1",Config.LOGGER_INFO);
            if (args.containsKey("language")) {
                finalLanguage = Language.getByID(Integer.parseInt((String) args.get("language")));
                if(finalLanguage == null) throw new BadConfigException("language no existente");
            } else throw new BadConfigException("es necesario configurar el parametro language");

            if (args.containsKey("request_domain")) {
                requestDomain =  (String) args.get("request_domain");
            } else throw new BadConfigException("es necesario configurar el parametro request_domain");

            if (args.containsKey("feed_name")) {
                feedName =  (String) args.get("feed_name");
            } else throw new BadConfigException("es necesario configurar el parametro feed_name");

            if (args.containsKey("outlet_auth_token")) {
                outletAuthToken =  (String) args.get("outlet_auth_token");
            } else throw new BadConfigException("es necesario configurar el parametro outlet_auth_token");

            performImageHost =  Config.getString("perform_image_host");

            if (args.containsKey("temporal_directory")) {
                temporalDirectory =  (String) args.get("temporal_directory");
            } else throw new BadConfigException("es necesario configurar el parametro temporal_directory");

            if (args.containsKey("app")) {
                app = Apps.findId(Integer.parseInt((String) args.get("app")));
                if(app == null)
                    throw new BadConfigException("el app configurado no existente");
            } else throw new BadConfigException("es necesario configurar el parametro app");

            ObjectNode news = getNews();
            processNews(news);

        } catch (BadConfigException ex){
            //log and deactivate? maybe throw exception
            Utils.printToLog(PerformNews.class,
                    "Error en el PerformNews",
                    "el job esta mal configurado, no puede arrancar.",
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
        } catch (Exception ex){
            Utils.printToLog(PerformNews.class,
                    "Error en el PerformNews",
                    "ocurrio un error inesperado en el LanceNewsScrapper, el proceso no se completo y sera reiniciado el job.",
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
        }
//        Utils.printToLog(PerformNews.class,null,"Finalizando PerformNews",false,null,"support-level-1",Config.LOGGER_INFO);
    }

    private void processNews(ObjectNode news) {
        Iterator<JsonNode> elements = news.get("articles").elements();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        List<Resolution> resolutions = Resolution.all();
        String title = null;
        String summary = null;
        String category = null;
        String keyword = null;
        String author = null;
        String story = null;
        long publishedTime = 0;
        Date publishedDate = null;
        long lastUpdateTime = 0;
        String source = null;
        String id = null;
        News toInsert = null;
        JsonNode next = null;
        while(isAlive() && elements.hasNext()){
            try {
                next = elements.next();
                title = next.get("headline").asText();
                summary = next.get("teaser").asText();
                category = next.get("categories").toString();
                keyword = next.get("keywords").toString();
                author = "";//next.get("author").asText();
                story = cleanBody(next.get("body").asText());
                publishedTime = next.get("publishedTime").asLong();
                publishedDate = new Date(publishedTime);
                lastUpdateTime = next.get("lastUpdateTime").asLong();
                source = next.get("externalUrl").asText();
                if (source.equalsIgnoreCase("null")) source = "";
                id = next.get("id").asText();
                toInsert = News.finder.where().eq("externalId", id).findUnique();
                if (toInsert == null) {
                    toInsert = new News(title, summary, category, keyword, author, story, sf.format(publishedDate), source, lastUpdateTime, id, app, finalLanguage);
                    if(next.has("links")) {
                        processMedia(next.get("links").elements(), toInsert, resolutions);
                    }
                    toInsert.save();
                } else {
                    if (lastUpdateTime > toInsert.getUpdatedDate()) {
                        toInsert.setTitle(title);
                        toInsert.setSummary(summary);
                        toInsert.setCategories(category);
                        toInsert.setKeyword(keyword);
                        toInsert.setAuthor(author);
                        toInsert.setNewsBody(story);
                        toInsert.setPublicationDate(sf.format(publishedDate));
                        toInsert.setSource(source);
                        toInsert.setUpdatedDate(lastUpdateTime);
                        if(next.has("links")) {
                            processMedia(next.get("links").elements(), toInsert, resolutions);
                        }
                        toInsert.update();
                    }
                }
            } catch (Exception ex){
                Utils.printToLog(PerformNews.class,
                        "Error en el PerformNews",
                        "No se pudo procesar la noticia actual, se sigue con la proxima.",
                        false,
                        ex,
                        "support-level-1",
                        Config.LOGGER_ERROR);
            }
        }
        resolutions.clear();
    }

    private void processMedia(Iterator<JsonNode> links, News news, List<Resolution> resolutions) {
        ArrayList<String> resourcesToDelete = new ArrayList<>();
        String name = null;
        String filename = null;
        String genericName = null;
        String description = null;
        String res = null;
        String externalId = null;
        String rel = null;
        String insertedTime = null;
        JsonNode next = null;
        int status = 0;
        int type = 0;
        File file = null;
        File scaledImage = null;
        String md5 = null;
        Resource resource = null;
        String remoteLocation = null;
        while(links.hasNext()){
            next = links.next();
            try {
                name = next.get("altText").asText();
                filename = next.get("url").asText();
                genericName = next.get("altText").asText();
                description = next.get("caption").asText();
                res = next.get("href").asText();
                externalId = next.get("id").asText();
                rel =next.get("rel").asText();
                type = rel.equalsIgnoreCase("IMAGE_HEADER")?1:3;
                status = 1;
                insertedTime = ""+Utils.currentTimeStamp(TimeZone.getTimeZone("America/Caracas"));
                file = getFile("http://" + performImageHost + filename);
                BufferedImage image = ImageIO.read(file);
                for (Resolution resolution : resolutions) {
                    isAlive();
                    scaledImage = scaleImage(image, filename, resolution);
                    if(scaledImage != null) {
                        try {
                            md5 = Utils.getMD5(scaledImage);
                            resource = news.getResource(externalId, resolution);
                            if (resource == null) {
                                remoteLocation = Utils.uploadResource(scaledImage);
                                if (remoteLocation == null) {
                                    continue;
                                }
                                resource = new Resource(name, filename, remoteLocation, insertedTime, insertedTime, type, status, externalId, app, news, genericName, description, res, md5, resolution);
                                news.addResource(resource);
                            } else {
                                if (!resource.getMd5().equalsIgnoreCase(md5)) {
                                    remoteLocation = Utils.uploadResource(scaledImage);
                                    if (remoteLocation == null) {
                                        continue;
                                    }
                                    resource.setFilename(filename);
                                    resourcesToDelete.add(resource.getRemoteLocation());
                                    resource.setRemoteLocation(remoteLocation);
                                }
                                resource.setDescription(description);
                                news.updateResource(resource);
                            }
                        } catch (Exception e) {
                            Utils.printToLog(PerformNews.class, null, "Error ajustando resoluciones", false, e, "support-level-1", Config.LOGGER_ERROR);
                        } finally {
                            scaledImage.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(file != null){
                    file.delete();
                }
            }
        }
        if(!resourcesToDelete.isEmpty()){
            String containerName = Config.getString("cdn-container");
            String username = Config.getString("rackspace-username");
            String apiKey = Config.getString("rackspace-apiKey");
            String provider = Config.getString("rackspace-provider");
            RackspaceDelete rackspaceDelete = new RackspaceDelete(username, apiKey, provider);
            rackspaceDelete.deleteObjectsFromContainer(containerName, resourcesToDelete);
        }
    }

    private File getFile(String path) {
        URL url = null;
        try {
            url = new URL(path);
            BufferedImage image = ImageIO.read(url);
            int lastIndexOfSlash = path.lastIndexOf("/");
            int lastIndexOfPoint = path.lastIndexOf(".");
            String fileName = path.substring(lastIndexOfSlash + 1, path.indexOf('.', lastIndexOfSlash));
            String fileExt = path.substring(lastIndexOfPoint + 1, path.indexOf('?', lastIndexOfPoint));
            File file = new File(temporalDirectory + fileName + "." + fileExt);
            ImageIO.write(image, fileExt, file);
            return file;
        } catch (Exception e) {
            Utils.printToLog(PerformNews.class, null, "Error descargando imagenes", false, e, "support-level-1", Config.LOGGER_ERROR);
        }
        return null;
    }

    private File scaleImage (BufferedImage image, String path, Resolution resolution) {
        try {
            int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
            BufferedImage resizedImage = resizeLocalImage(image, type, resolution.getWidth(), resolution.getHeight(), (image.getWidth() > image.getHeight()?IMGRESIZE_KEEPASPECT_WIDTH:(image.getHeight() > image.getWidth()?IMGRESIZE_KEEPASPECT_HEIGHT:IMGRESIZE_EXACT)));
            int lastIndexOfSlash = path.lastIndexOf("/");
            int lastIndexOfPoint = path.lastIndexOf(".");
            String fileName = path.substring(lastIndexOfSlash + 1, path.indexOf('.', lastIndexOfSlash));
            String fileExt = path.substring(lastIndexOfPoint + 1, path.indexOf('?', lastIndexOfPoint));
            File file = new File(temporalDirectory + resolution.getName() + "_" + fileName + "." + fileExt);
            ImageIO.write(resizedImage, fileExt, file);
            return file;
        } catch (Exception e) {
            Utils.printToLog(PerformNews.class, null, "Error escalando imagenes", false, e, "support-level-1", Config.LOGGER_ERROR);
        }
        return null;
    }

    private String cleanBody(String body) {
        int whitelistType = 0;
        try {
            whitelistType = Config.getInt("news-whitelist-type");
        } catch (Exception e){
            whitelistType = 0;
        }
        return Jsoup.clean(body, whitelistType==1?hecticusWhitelist():Whitelist.basic()).replaceAll("\\(Foto: Getty Images\\)", "");
    }

    private ObjectNode getNews() {
        String queryParameters = "_fmt=json&_rt=b&_fld=hl,tsr,ctg,bd,kwd,pt,lut,uuid,exu,img&_lcl=" + finalLanguage.getShortName();
        StringBuilder url = new StringBuilder();
        url.append("http://").append(requestDomain).append("/").append(feedName).append("/").append(outletAuthToken).append("/?").append(queryParameters);
        F.Promise<WSResponse> result = WS.url(url.toString()).get();
        ObjectNode response = (ObjectNode)result.get(Config.getLong("ws-timeout-millis"), TimeUnit.MILLISECONDS).asJson();
        return response;
    }

    private BufferedImage resizeLocalImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT, int aspectRatioType) throws UnsupportedOperationException {
        BufferedImage resizedImage;
        Graphics2D g;
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();
        float aspect;
        switch(aspectRatioType){
            case IMGRESIZE_EXACT:
                //si la imagen debe quedar de esa resolucion exacta
                resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
                g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
                g.dispose();
                break;
            case IMGRESIZE_KEEPASPECT_WIDTH:
                //si se quiere que la imagen se encuentre dentro del bounding box por width
                aspect = (float)IMG_WIDTH/(float)width;
                aspect = height*aspect;
                resizedImage = new BufferedImage(IMG_WIDTH, (int)aspect, type);
                g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, IMG_WIDTH, (int)aspect, null);
                g.dispose();
                break;
            case IMGRESIZE_KEEPASPECT_HEIGHT:
                //si se quiere que la imagen se encuentre dentro del bounding box por height
                aspect = (float)IMG_HEIGHT/(float)height;
                aspect = width*aspect;
                resizedImage = new BufferedImage((int)aspect, IMG_HEIGHT, type);
                g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, (int)aspect, IMG_HEIGHT, null);
                g.dispose();
                break;
            default: throw new UnsupportedOperationException("Image resize type not valid");
        }

        return resizedImage;
    }

    public Whitelist hecticusWhitelist() {
        return new Whitelist()
                .addTags("a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt",
                        "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub",
                        "sup", "u", "ul")
                .addAttributes("a", "href")
                .addAttributes("blockquote", "cite")
                .addAttributes("q", "cite")
                .addProtocols("a", "href", "ftp", "http", "https", "mailto")
                .addProtocols("blockquote", "cite", "http", "https")
                .addProtocols("cite", "cite", "http", "https")
                .addEnforcedAttribute("a", "rel", "nofollow");
    }

}
