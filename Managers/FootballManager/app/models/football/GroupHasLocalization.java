package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;

/**
 * Created by plesse on 3/5/15.
 */
@Entity
@Table(name="group_has_localization")
public class GroupHasLocalization extends FootballModel {


    @Id
    private Integer idGroupHasLocalization;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "id_language")
    private Language language;

    @Constraints.Required
    private String name;

    private static Model.Finder<Integer, GroupHasLocalization> finder = new Model.Finder<Integer, GroupHasLocalization>(Integer.class, GroupHasLocalization.class);

    public GroupHasLocalization(Group group, Language language, String name) {
        this.group = group;
        this.language = language;
        this.name = name;
    }

    public Integer getIdGroupHasLocalization() {
        return idGroupHasLocalization;
    }

    public void setIdGroupHasLocalization(Integer idGroupHasLocalization) {
        this.idGroupHasLocalization = idGroupHasLocalization;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ObjectNode toJson() {
        ObjectNode node = Json.newObject();
        node.put("language", language.toJson());
        node.put("name", name);
        return node;
    }

    public static boolean exists(GroupHasLocalization groupHasLocalization){
        GroupHasLocalization unique = finder.where().eq("group", groupHasLocalization.getGroup()).eq("language", groupHasLocalization.getLanguage()).findUnique();
        boolean exists = unique != null;
        if(exists && !unique.getName().equalsIgnoreCase(groupHasLocalization.getName())){
            unique.setName(groupHasLocalization.getName());
            unique.update();
        }
        return exists;
    }
}
