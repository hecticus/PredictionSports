package utils;

/**
* Created by sorcerer on 2/18/15.
*/


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;

import models.Config;
import org.apache.commons.io.FileUtils;

/**
*
* @author chrirod
*/
public class ImageManager {
    public static String imageDir = "/home/footballbrazil/files/imageManager/";

    public static final int IMGRESIZE_EXACT = 0;
    public static final int IMGRESIZE_KEEPASPECT_WIDTH = 1;
    public static final int IMGRESIZE_KEEPASPECT_HEIGHT = 2;

    /**
     * Funcion que permite guardar en archivo temporal tanto el original como la imagen escalada segun la forma de escalado
     * 0 para que sea de tamaño maxWidth x maxHeight
     * 1 para que sea de tamaño maxWidth x Y (con Y manteniendo el aspectRatio)
     * 2 para que sea de tamaño X x maxHeight (con X manteniendo el aspectRatio)
     * @param url tiene que ser un url directo a la imagen
     * @param maxWidth
     * @param maxHeight
     * @param scaleType segun lo antes explicado se rigen por las constantes IMGRESIZE_ de esta clase
     * @return File ya escalado
     * @throws IOException
     * @throws URISyntaxException
     * @throws UnsupportedOperationException
     */
    public static File resizeImage(String url, String fileRoute, int maxWidth, int maxHeight, int scaleType) throws IOException, URISyntaxException{
        //File f = new File(new URI("file:///Users/chrirod/Desktop/original.jpg"));
        //FileUtils.copyURLToFile(new URL("http://imagenes.tvn-2.com/noticias_img/133397.jpg"), f);
        imageDir = Config.getString("image-processing-location");
        String fileName = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
        String fileExt = url.substring(url.lastIndexOf("."));
        File f = new File(imageDir+fileRoute+fileName+fileExt);
        FileUtils.copyURLToFile(new URL(url), f);
        BufferedImage originalImage = ImageIO.read(f);//change path to where file is located
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizeImageJpg = resizeLocalImage(originalImage, type, maxWidth, maxHeight, scaleType);
        //File f2 = new File(new URI("file:///Users/chrirod/Desktop/testresized3.jpg"));
        File f2 = new File(imageDir+fileRoute+fileName+"_scale"+fileExt);
        ImageIO.write(resizeImageJpg, "jpg", f2); //change path where you want it saved
        return f2;
    }

    /**
     * En vez de escalar una imagen escala todas las imagenes que se encuentren en el ArrayList files con el formato
     *
     * [{"fileName":"quality","width":MaxX,"height":MaxY}, ...]
     *
     * tambien se pueden pasar los porcentajes de la siguiente forma
     *
     * [{"fileName":"mid","percentage":75.0}, ...] o [{"fileName":"mid","percentage":0.75}, ...]
     *
     * Example:
     * ArrayList<Map> test = new ArrayList<Map>();
     * Map file1 = new HashMap();
     * file1.put("fileName", "mid");
     * file1.put("width", 300);
     * file1.put("height", 300);
     * Map file2 = new HashMap();
     * file2.put("fileName", "low");
     * file2.put("width", 100);
     * file2.put("height", 100);
     * test.add(file1);
     * test.add(file2);
     * ImageManager.resizeLocalImage("http://imagenes.tvn-2.com/noticias_img/133397.jpg","", test, ImageManager.IMGRESIZE_KEEPASPECT_WIDTH);
     *
     * @param url
     * @param fileRoute
     * @param files
     * @param scaleType
     * @return Array de files que escalaron
     * @throws IOException
     * @throws URISyntaxException
     */
    public static ArrayList<File> resizeImage(String url, String fileRoute, ArrayList<Map> files, int scaleType) throws IOException, URISyntaxException{
        url = url.replaceAll(" ", "%20");
        String fileName = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
        String fileExt = url.substring(url.lastIndexOf("."));
        imageDir = Config.getString("image-processing-location");
        File f = new File(imageDir+fileRoute+fileName+fileExt);
        FileUtils.copyURLToFile(new URL(url), f);
        BufferedImage originalImage = ImageIO.read(f);//change path to where file is located
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        ArrayList<File> filesToReturn = new ArrayList<File>();
        filesToReturn.add(f);
        for(int i=0;i<files.size();i++){
            Map fileDesc = files.get(i);
            BufferedImage resizeImageJpg;
            if(fileDesc.containsKey("percentage")){
                //vienen porcentajes
                float percentage = (Float)fileDesc.get("percentage");
                if(percentage>1){
                    percentage = percentage/100.0f;
                }
                float realW = originalImage.getWidth();
                float realH = originalImage.getHeight();
                float resultW = realW*percentage;
                float resultH = realH*percentage;
                resizeImageJpg = resizeLocalImage(originalImage, type, (int) resultW, (int) resultH, IMGRESIZE_EXACT);
            }else{
                //vienen valores de altura o ancho
                resizeImageJpg = resizeLocalImage(originalImage, type, (Integer) fileDesc.get("width"), (Integer) fileDesc.get("height"), scaleType);
            }
            String fileSizeDesc = (String)fileDesc.get("fileName");
            File f2 = new File(imageDir+fileRoute+fileName+"_"+fileSizeDesc+fileExt);
            ImageIO.write(resizeImageJpg, "jpg", f2); //change path where you want it saved
            filesToReturn.add(f2);
        }
        return filesToReturn;
    }

    /**
     *
     * @param originalImage
     * @param type
     * @param IMG_WIDTH
     * @param IMG_HEIGHT
     * @param aspectRatioType
     * @return
     * @throws UnsupportedOperationException
     */
    public static BufferedImage resizeLocalImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT, int aspectRatioType) throws UnsupportedOperationException {
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

    public static File resizeImageFromHDD(String fileRoute, int maxWidth, int maxHeight, int scaleType) throws IOException, URISyntaxException {
        imageDir = Config.getString("image-processing-location");
        String fileName = fileRoute.substring(fileRoute.lastIndexOf("/") + 1, fileRoute.lastIndexOf("."));
        String fileExt = fileRoute.substring(fileRoute.lastIndexOf("."));
        File f = new File(fileRoute);
        BufferedImage originalImage = ImageIO.read(f);//change path to where file is located
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizeImageJpg = resizeLocalImage(originalImage, type, maxWidth, maxHeight, scaleType);
        File f2 = new File(imageDir + fileName + "_scale_"+maxWidth+ ".jpg");
        ImageIO.write(resizeImageJpg, "jpg", f2); //change path where you want it saved
        return f2;
    }

    public static File resizeImageFromHDD(String fileRoute, int percentageVal) throws IOException, URISyntaxException {
        imageDir = Config.getString("image-processing-location");
        String fileName = fileRoute.substring(fileRoute.lastIndexOf("/") + 1, fileRoute.lastIndexOf("."));
        String fileExt = fileRoute.substring(fileRoute.lastIndexOf("."));
        File f = new File(imageDir + fileName + fileExt);

        BufferedImage originalImage = ImageIO.read(f);//change path to where file is located
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

        BufferedImage resizeImageJpg;
        float percentage = percentageVal;
        if (percentage > 1) {
            percentage = percentage / 100.0f;
        }
        float realW = originalImage.getWidth();
        float realH = originalImage.getHeight();
        float resultW = realW * percentage;
        float resultH = realH * percentage;
        resizeImageJpg = resizeLocalImage(originalImage, type, (int) resultW, (int) resultH, IMGRESIZE_EXACT);

        File f2 = new File(imageDir + fileName + "_" + percentageVal + fileExt);
        ImageIO.write(resizeImageJpg, "jpg", f2);


        return f2;
    }
}
