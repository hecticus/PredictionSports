package utils;

import com.google.common.base.Predicate;
import com.hecticus.rackspacecloud.RackspaceCreate;
import com.hecticus.rackspacecloud.RackspacePublish;
import models.Config;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Utils extends JobCoreUtils{
    private static final int TTL = 900;

    /**
     * Data para el generador de cadenas alfanumericas
     */
    private static char[] symbols;
    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    /***
     * Funcion que devuelve el timestamp actual en formato YYYYMMDDHHMMSS
     * @param tz	timezone a consultar
     * @return		fecha en formato YYYYMMDDHHMMSS
     */
    public static long currentTimeStamp(TimeZone tz) {

        Calendar actualDate = new GregorianCalendar(tz);
        int auxMonth = actualDate.get(Calendar.MONTH) + 1;
        int auxDay = actualDate.get(Calendar.DAY_OF_MONTH);
        int auxHour = actualDate.get(Calendar.HOUR_OF_DAY);
        int auxMinute = actualDate.get(Calendar.MINUTE);
        int auxSecond = actualDate.get(Calendar.SECOND);

        StringBuffer fechaInicio = new StringBuffer(12);
        fechaInicio.append(actualDate.get(Calendar.YEAR));
        if (auxMonth < 10) {
            fechaInicio.append("0");
        }
        fechaInicio.append(auxMonth);
        if (auxDay < 10) {
            fechaInicio.append("0");
        }
        fechaInicio.append(auxDay);
        if (auxHour < 10) {
            fechaInicio.append("0");
        }
        fechaInicio.append(auxHour);
        if (auxMinute < 10) {
            fechaInicio.append("0");
        }
        fechaInicio.append(auxMinute);
        if (auxSecond < 10) {
            fechaInicio.append("0");
        }
        fechaInicio.append(auxSecond);


        return Long.parseLong(fechaInicio.toString());
    }


    /**
     * Funcion que genera una cadena pseudoaleatoria alfanumerica de tamaño length
     * @param length El tamaño de la cadena
     * @return la cadena!!!
     */
    public static String tokenGenerator(int length){
        Random random = new Random();
        char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

//    public static String getMD5(String path) throws IOException, NoSuchAlgorithmException {
//        String checksum = null;
//        FileInputStream fis = new FileInputStream(path);
//        try {
//            checksum = DigestUtils.md5Hex(fis);
//        } finally {
//            fis.close();
//        }
//        return checksum;
//    }
//
//    public static String getMD5(File file) throws IOException, NoSuchAlgorithmException {
//        String checksum = null;
//        FileInputStream fis = new FileInputStream(file);
//        try {
//            checksum = DigestUtils.md5Hex(fis);
//        } finally {
//            fis.close();
//        }
//        return checksum;
//    }

    public static String moveAttachmentForAttachment(String file, int idTrivia) throws IOException {
        File afile =new File(Config.getString("ftp-route") + file);
        File folder =new File(Config.getString("attachments-route") + idTrivia);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        File dest = new File(Config.getString("attachments-route") + idTrivia + "/" + afile.getName());
        FileUtils.copyFile(afile, dest);
        return dest.getAbsolutePath();
    }

    public static String uploadAttachment(String file, int idWoman) throws IOException {
        File afile =new File(Config.getString("ftp-route") + file);
        File folder =new File(Config.getString("attachments-route") + idWoman);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        File dest = new File(Config.getString("attachments-route") + idWoman + "/" + afile.getName());
        FileUtils.copyFile(afile, dest);

        String fileName = dest.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")-1, fileName.length());

        UUID idFile = UUID.randomUUID();
        File dest2 = new File(Config.getString("attachments-route")+idFile+fileExtension);
        dest.renameTo(dest2);
        boolean uploaded = uploadAndPublish(dest2, ""+idWoman);
        if(uploaded){
            String name = Config.getString("rks-CDN-URL") + idWoman + "/" + dest2.getName();
            dest2.delete();
            return name;
        }
        return null;
//        return dest.getAbsolutePath();
    }

    public static String uploadAttachment(File file, int idWoman, String fileExtension) throws IOException {
        File folder =new File(Config.getString("attachments-route") + idWoman);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        File dest = new File(Config.getString("attachments-route") + idWoman + "/" + file.getName());
        FileUtils.copyFile(file, dest);

        UUID idFile = UUID.randomUUID();
        File dest2 = new File(Config.getString("attachments-route")+idFile+fileExtension);
        dest.renameTo(dest2);
        boolean uploaded = uploadAndPublish(dest2, ""+idWoman);
        if(uploaded){
            String name = Config.getString("rks-CDN-URL") + idWoman + "/" + dest2.getName();
            dest2.delete();
            return name;
        }
        return null;
//        return dest.getAbsolutePath();
    }

    private static boolean uploadAndPublish(File file, String parent){
        String containerName = Config.getString("cdn-container");
        String username = Config.getString("rackspace-username");
        String apiKey = Config.getString("rackspace-apiKey");
        String provider = Config.getString("rackspace-provider");
        RackspaceCreate upload = new RackspaceCreate(username, apiKey, provider);
        RackspacePublish pub = new RackspacePublish(username, apiKey, provider);
        long init = System.currentTimeMillis();
        int retry = 3;
        if(upload == null || pub == null){
            return false;
        }
        try {
            upload.createContainer(containerName);
            Utils.printToLog(Utils.class, "", "Creado container " + containerName, false, null, "", Config.LOGGER_INFO);
            //resources
            boolean uploaded = uploadFile(upload, retry, containerName, file, parent, init);
            Utils.printToLog(Utils.class, "", "Archivo" + (!uploaded ? " NO" : "") + " subido " + file.getAbsolutePath(), false, null, "", Config.LOGGER_INFO);
            if(uploaded){
                //publish
                pub.enableCdnContainer(containerName, TTL);
                Utils.printToLog(Utils.class, "", "Container CDN enabled", false, null, "", Config.LOGGER_INFO);
            }
            return uploaded;
        }catch (Exception ex){
            Utils.printToLog(Utils.class, "", "Error subiendo el archivo al CDN", false, ex, "", Config.LOGGER_ERROR);
            return false;
        }
    }

    public static boolean uploadFile(RackspaceCreate upload,int retry,String container, File file, String parent, long init) throws InterruptedException{
        boolean uploaded = false;
        while(retry > 0 && !uploaded){
            Utils.printToLog(Utils.class, "", "Subiendo el archivo " + file.getName() + " intento " + retry, false, null, "", Config.LOGGER_INFO);
            try {
//                upload.uploadObject(container,file);
                upload.uploadObject(container, file, null, parent);
                uploaded = true;
            } catch (Exception ex) {
                Utils.printToLog(Utils.class, "Falla subiendo el archivo " + (System.currentTimeMillis() - init) + " ms", "Se realizará reintento en 3 minutos", false, ex, "", Config.LOGGER_ERROR);
                Thread.sleep(5000);
                retry--;
            }
        }

        if(!uploaded){
            Utils.printToLog(Utils.class, "Luego de " + retry + " intentos, el archivo no pudo ser cargado el cloud", "-", false, null, "", Config.LOGGER_ERROR);
            return false;
        }

        return true;
    }

    /**
     * Funcion para obtener la cantidad de dias entre 2 fechas
     * @param startDate		fecha de inicio
     * @param endDate		fecha final
     * @return				long con la cantidad de dias transcurridos
     */
    public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    public static boolean isDateBefore(Calendar first, Calendar second) {
        if((first.get(Calendar.YEAR) > second.get(Calendar.YEAR)) || (first.get(Calendar.MONTH) > second.get(Calendar.MONTH)) || (first.get(Calendar.DATE) > second.get(Calendar.DATE))){
            return true;
        }
        return false;
    }


    public static <T> Collection<T> filterCollection(Collection<T> col, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        for (T element: col) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static void uploadLocalResources(File toUpload, String parent) throws Exception {
        boolean uploaded = uploadAndPublish(toUpload, parent);
        if(!uploaded){
            //throw error
            throw new Exception("El archivo" + toUpload.getName() +" no se pudo subir al cloud");
        }
    }

    public static String getFootballManagerHost() {
        return Config.getString("football-manager-url");
    }

    public static String getAndroidVersion() {
        return Config.getString("android-version");
    }

    public static String getAndroidVersionURL() {
        return Config.getString("android-version-url");
    }

    public static String getiOSVersion() {
        return Config.getString("ios-version");
    }

    public static String getiOSVersionURL() {
        return Config.getString("ios-version-url");
    }

    public static boolean getIsSecured() {
        try {
            int intSecured = Config.getInt("secured");
            return intSecured == 0 ? false : true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean acceptHeader() {
        try {
            int intSecured = Config.getInt("accept-header");
            return intSecured == 0 ? false : true;
        } catch (Exception e){
            return false;
        }
    }
}
