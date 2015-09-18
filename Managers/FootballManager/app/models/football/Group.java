package models.football;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballModel;
import models.Language;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by plesse on 3/5/15.
 */
@Entity
@Table(name="groups")
public class Group extends FootballModel {


    @Id
    private Long idGroup;
    @ManyToOne
    @JoinColumn(name = "id_competition")
    private Competition competition;
    @Constraints.Required
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupHasLocalization> localizations;

    private static Model.Finder<Long,Group> finder = new Model.Finder<Long,Group>(Long.class,Group.class);

    public Group(Competition competition, String name) {
        this.competition = competition;
        this.name = name;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ObjectNode toJson() {
        return null;
    }

    public static Group findByNameCompetition(Competition competition, String name){
        return finder.where().eq("competition", competition).eq("name", name).findUnique();
    }

    public void validate(Language language){
        Group toValidate = findByNameCompetition(this.competition, this.name);
        if (toValidate != null){
            this.idGroup = toValidate.idGroup;
            this.competition = toValidate.competition;
            this.name = toValidate.name;
            this.localizations = toValidate.localizations;
        }else {
            this.save();
        }

        GroupHasLocalization groupHasLocalization = new GroupHasLocalization(this, language, this.name);
        if(!GroupHasLocalization.exists(groupHasLocalization)){
            this.localizations.add(groupHasLocalization);
            groupHasLocalization.save();
            this.update();
        }
    }

}
