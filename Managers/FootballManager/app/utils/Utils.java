package utils;

import com.google.common.base.Predicate;
import com.hecticus.rackspacecloud.RackspaceCreate;
import com.hecticus.rackspacecloud.RackspacePublish;
import models.Config;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Utils extends JobCoreUtils{
    private static final int TTL = 900;

    public static final TimeZone APP_TIMEZONE = TimeZone.getTimeZone("America/Caracas");
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

    /***
     * Metodo para borrar un archivo
     * @param fileName ruta completa del archivo
     */
    public static void delete(String fileName) {
        try {
            File target = new File(fileName);
            if (!target.exists()) {
                String emsg = "El archivo: " + fileName + " NO EXISTE";
                return;
            }
            if (target.delete()) {
                String emsg = "El archivo: " + fileName + " se ha borrado con exito";
                //logger.info(emsg);
            } else {
                String emsg = "Error el archivo: " + fileName + " no se pudo borrar";
                //logger.error(emsg);
            }
        } catch (SecurityException ex) {
            //System.err.println("Unable to delete " + fileName + "("+ e.getMessage() + ")");
            String emsg = "Error el archivo: " + fileName + " no se pudo borrar";
        }
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
            Utils.printToLog(Utils.class, "", "Archivo" + (!uploaded?" NO":"") + " subido " + file.getAbsolutePath(), false, null, "", Config.LOGGER_INFO);
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
            Utils.printToLog(Utils.class,"Luego de "+retry+" intentos, el archivo no pudo ser cargado el cloud","-",false,null,"",Config.LOGGER_ERROR);
            return false;
        }

        return true;
    }

    public static String uploadResource(File imageFile) {
        boolean uploaded = uploadAndPublish(imageFile, "resources");
        if(uploaded){
            String name = Config.getString("rks-CDN-URL") + "resources/" + imageFile.getName();
            imageFile.delete();
            return name;
        }
        return null;
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

    public static <T> Collection<T> filterCollection(List<T> col, Predicate<T> predicate, int page, int pageSize) {
        Collection<T> result = new ArrayList<T>();
        int n = 0, k = 0;
        for (int i = 0; n < pageSize && i < col.size(); ++i) {
            T element = col.get(i);
            if (predicate.apply(element)) {
                ++k;
                if(k > page) {
                    ++n;
                    result.add(element);
                }
            }
        }
        return result;
    }

    public static String getMD5(File path) throws IOException, NoSuchAlgorithmException {
        FileInputStream fis = new FileInputStream(path);
        return getMD5(fis);
    }

    private static String getMD5(FileInputStream fis) throws IOException, NoSuchAlgorithmException {
        String checksum = null;
        try {
            checksum = DigestUtils.md5Hex(fis);
        } finally {
            fis.close();
        }
        return checksum;
    }
}
