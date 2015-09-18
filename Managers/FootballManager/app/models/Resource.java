package models;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.football.News;
import play.db.ebean.Model;
import play.libs.Json;
import utils.Utils;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sorcerer on 3/20/14.
 */
@Entity
@Table(name="resources")
public class Resource extends FootballModel {

    //definir constantes de type

    @Id
    private long idResource;
    private String name;
    private String filename;
    private String remoteLocation;
    private String genericName;
    private String description;
    private String res;
    private Integer type; //1 principal, 2 principal reducido, 3 secundaria
    private Integer status;

    private String insertedTime;
    private String creationTime;

    private String externalId;

    private String md5;

    @ManyToOne
    @JoinColumn(name = "id_app")
    private Apps app;

    @ManyToOne
    @JoinColumn(name="news_id_news")
    private News parent;

    @OneToOne
    @JoinColumn(name = "id_resolution")
    private Resolution resolution;

    private static Model.Finder<Long,Resource> finder = new Model.Finder<Long, Resource>(Long.class, Resource.class);

    public Resource(String name, String filename, String remoteLocation, String description, String insertedTime, String creationTime,String metadata, Apps app) {
        this.name = name;
        this.filename = filename;
        this.remoteLocation = remoteLocation;
        this.genericName = "";
        this.description = description;
        //res
        this.type = 1;
        this.status = 0;
        if (creationTime.isEmpty()){
            this.creationTime = ""+Utils.currentTimeStamp(Utils.APP_TIMEZONE);
        }else {
            this.creationTime = creationTime;
        }

        this.insertedTime = insertedTime;
        this.externalId = metadata;
        this.app = app;
        //parent news null
    }

    public Resource(String name, String filename, String remoteLocation, String creationTime, String insertedTime, Integer type, Integer status, String externalId, Apps app, News parent, String genericName, String description, String res, String md5, Resolution resolution) {
        this.name = name;
        this.filename = filename;
        this.remoteLocation = remoteLocation;
        this.creationTime = creationTime;
        this.insertedTime = insertedTime;
        this.type = type;
        this.status = status;
        this.externalId = externalId;
        this.app = app;
        this.parent = parent;
        this.genericName = genericName;
        this.description = description;
        this.res = res;
        this.md5 = md5;
        this.resolution = resolution;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode tr = Json.newObject();
        tr.put("type", type);
        tr.put("file", remoteLocation);
        return tr;
    }

    public static boolean imageExist(String filename, int idApp){
        boolean tr = false;
        Resource exist = finder.where().eq("id_app",idApp).eq("filename",filename).setMaxRows(1).findUnique();
        if (exist != null){
            tr = true;
        }
        return tr;
    }

    public static List<Resource> getAllResource(){
        return finder.all();
    }

    public static List<Resource> getAllResourcesAvailable(){
        return finder.where().eq("news_id_news", null).findList();
//        com.avaje.ebean.Query<Resource>  query  = com.avaje.ebean.Ebean.createQuery(Resource.class);
//        return query.where("NOT EXISTS (SELECT * FROM news_resource  WHERE news_resource.resource_id = id)").findList();
    }

    public static Resource getResource(long idResouce) {
        return finder.byId(idResouce);
    }

    /**************************** GETTERS AND SETTERS ****************************************************/

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

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getIdResource() {
        return idResource;
    }

    public void setIdResource(long idResource) {
        this.idResource = idResource;
    }

    public String getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(String insertedTime) {
        this.insertedTime = insertedTime;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Apps getApp() {
        return app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }

    public String getRemoteLocation() {
        return remoteLocation;
    }

    public void setRemoteLocation(String remoteLocation) {
        this.remoteLocation = remoteLocation;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public News getParent() {
        return parent;
    }

    public void setParent(News parent) {
        this.parent = parent;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }
}
