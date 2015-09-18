package models;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.mutable.Buffer;

import javax.persistence.*;
import java.util.*;

/**
 * Created by plesse on 4/10/15.
 */
@Entity
@Table(name="resolutions")
public class Resolution extends FootballModel {

    @Id
    private Integer idResolution;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private Integer width;

    @Constraints.Required
    private Integer height;

    @OneToOne(mappedBy="resolution")
    private Resource resource;

    private static Model.Finder<Integer, Resolution> finder = new Model.Finder<>(Integer.class, Resolution.class);

    public Resolution(String name, Integer width, Integer height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public Integer getIdResolution() {
        return idResolution;
    }

    public void setIdResolution(Integer idResolution) {
        this.idResolution = idResolution;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return name + " (" + width + " x " + height + ")";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id_resolution", idResolution);
        response.put("name", name);
        response.put("width", width);
        response.put("height", height);
        return response;
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        List<Resolution> resolutions = finder.where().findList();
        for(Resolution resolution: resolutions) {
            options.put(resolution.getIdResolution().toString(), resolution.getFullName());
        }
        return options;
    }

    public static scala.collection.immutable.List<Tuple2<String, String>> toSeq() {
        List<Resolution> resolutions = finder.all();
        ArrayList<Tuple2<String, String>> proxy = new ArrayList<>();
        for(Resolution resolution : resolutions) {
            Tuple2<String, String> t = new Tuple2<>(resolution.getIdResolution().toString(), resolution.getFullName());
            proxy.add(t);
        }
        Buffer<Tuple2<String, String>> sportBuffer = JavaConversions.asScalaBuffer(proxy);
        scala.collection.immutable.List<Tuple2<String, String>> sportList = sportBuffer.toList();
        return sportList;
    }

    public static Page<Resolution> page(int page, int pageSize, String sortBy, String order, String filter) {
        return finder.where().ilike("name", "%" + filter + "%").orderBy(sortBy + " " + order).findPagingList(pageSize).getPage(page);
    }

    //Finder Operations

    public static Resolution getByID(int id){
        return finder.byId(id);
    }

    public static List<Resolution> all(){
        return finder.all();
    }

    public static Iterator<Resolution> getPage(int pageSize, int page){
        Iterator<Resolution> iterator = null;
        if(pageSize == 0){
            iterator = finder.all().iterator();
        }else{
            iterator = finder.where().setFirstRow(page).setMaxRows(pageSize).findList().iterator();
        }
        return  iterator;
    }
}
