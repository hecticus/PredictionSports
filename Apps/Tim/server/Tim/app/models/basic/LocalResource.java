package models.basic;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HecticusModel;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sorcerer on 2/23/15.
 */

@Entity
@Table(name="local_resource")
public class LocalResource extends HecticusModel {

    @Id
    private Long id;
    private String name;
    private String filename;
    private String url;
    private int width;
    private int height;
    private int type;
    //private String metadata; //optional

    //finder
    private static Model.Finder<Long,LocalResource> finder = new Model.Finder<Long, LocalResource>(Long.class, LocalResource.class);

    public LocalResource(){
        //contructor por defecto
    }

    /**************************************GETTERS AND SETTERS ********************************************************/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /*************************************  METHODS *******************************************************************/
    @Override
    public ObjectNode toJson() {
        ObjectNode objNode = Json.newObject();
        objNode.put("org_name",name);
        objNode.put("filename",filename);
        objNode.put("url",url);
        objNode.put("width",width);
        objNode.put("height",height);
        objNode.put("type",type);
        return objNode;
    }

    public static LocalResource getByNameW(String filename, int width){
        //String actual = filename + "_" + width;
        return finder.where().eq("name", filename).eq("width", width).findUnique();
    }

    public static LocalResource getByNameWH(String filename, int width, int height){
        String actual = filename + "_" + width + "_" + height;
        return finder.where().eq("filename", actual).eq("width", width).eq("height",height).findUnique();
    }

    public static LocalResource getByNamePercentage(String filename, String percentage){
        return finder.where().eq("filename", filename + "_" + percentage).findUnique();
    }

}
