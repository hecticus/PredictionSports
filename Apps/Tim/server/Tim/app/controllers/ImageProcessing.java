package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Config;
import models.basic.LocalResource;
import play.mvc.Result;
import utils.ImageManager;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by sorcerer on 2/18/15.
 */
public class ImageProcessing extends FootballController {

    public static Result getLocalResourceBySize(String file, String size){
        try {
            String localFileLocation = Config.getString("image-processing-location");
            //low, med, high,
            int percentage = 0;
            if (size.equalsIgnoreCase("low")){
                percentage = 25;
            }else if (size.equalsIgnoreCase("med")){
                percentage = 50;
            }else if (size.equalsIgnoreCase("high")){
                percentage = 75;
            }else {
                //error throw error
                throw  new Exception("el tamaño solicitado no existe");
            }

            //get params
            LocalResource inDb = LocalResource.getByNamePercentage(file, ""+percentage);
            if (inDb == null) {
                //generate new file
                int height = 0; //its gonna be calculated by aspect ratio
                File brandNewImage = ImageManager.resizeImageFromHDD(localFileLocation + file, percentage);
                BufferedImage modImage = ImageIO.read(brandNewImage);

                //upload and insert in cloud
                Utils.uploadLocalResources(brandNewImage, "localResources");

                inDb = new LocalResource();
                inDb.setName(file);
                inDb.setFilename(brandNewImage.getName());
                inDb.setHeight(modImage.getHeight());
                inDb.setWidth(modImage.getWidth());
                inDb.setUrl(Config.getString("rks-CDN-URL") + "localResources/" + brandNewImage.getName());
                inDb.setType(0);
                //insert in db
                inDb.save();
                //delete local file
                brandNewImage.delete();
            }
            ObjectNode response = null;
            response = buildBasicResponse(0, "OK", inDb.toJson());
            return ok(response);
        }catch (Exception ex){
            Utils.printToLog(ImageProcessing.class,
                    "Error procesando images WAP",
                    "ocurrio un error con el recurso local:" + file,
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
            return badRequest(buildBasicResponse(-1, "procesando los recursos locales", ex));
        }
    }

    public static Result getLocalResourceByWidth(String file, Integer width){
        String localFileLocation = "";
        try {
            //get params
            LocalResource inDb = LocalResource.getByNameW(file, width);
            localFileLocation = Config.getString("image-processing-location");
            if (inDb == null) {
                //generate new file
                int height = 0; //its gonna be calculated by aspect ratio
                File brandNewImage = ImageManager.resizeImageFromHDD(localFileLocation + file, width, height,
                        ImageManager.IMGRESIZE_KEEPASPECT_WIDTH);
                BufferedImage modImage = ImageIO.read(brandNewImage);

                //upload and insert in cloud
                Utils.uploadLocalResources(brandNewImage, "localResources");

                inDb = new LocalResource();
                inDb.setName(file);
                inDb.setFilename(brandNewImage.getName());
                inDb.setHeight(modImage.getHeight());
                inDb.setWidth(modImage.getWidth());
                inDb.setUrl(Config.getString("rks-CDN-URL") + "localResources/" + brandNewImage.getName());
                inDb.setType(0);
                //insert in db
                inDb.save();
                //delete local file
                brandNewImage.delete();
            }
            ObjectNode response = null;
            response = buildBasicResponse(0, "OK", inDb.toJson());
            return ok(response);
        } catch (Exception ex) {
            Utils.printToLog(ImageProcessing.class,
                    "Error procesando images WAP",
                    "ocurrio un error con el recurso local:" + file,
                    true,
                    ex,
                    "support-level-1",
                    Config.LOGGER_ERROR);
            return badRequest(buildBasicResponse(-1, "procesando los recursos locales", ex));
        }
    }
}
