package models;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.MappedSuperclass;
import java.net.URLDecoder;
import java.net.URLEncoder;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class FootballModel extends HecticusModel {

    public static final int MAX_SIZE = 20;

    public static final String CDN_URL = "http://9412fdf6c01bc9771bc2-f7308435bd9ae7a6ffc150d7e895a463.r87.cf1.rackcdn.com/img/";

    public String decode(String val){
        String tr = null;
        try {
            tr= URLDecoder.decode(val, "UTF-8");
        }catch (Exception ex){

        }
        return tr;
    }

    public String encode(String val){
        String tr = null;
        try {
            tr= URLEncoder.encode(val, "UTF-8");
        }catch (Exception ex){

        }
        return tr;
    }

    public String createMd5(String message){
        String tr = DigestUtils.md5Hex(message);
        return tr;
    }

    public static final String DEFAULT_TIMEZONE = "America/Caracas";
}
