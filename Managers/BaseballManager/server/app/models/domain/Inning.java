package models.domain;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by palenge on 12/28/16.
 */
@Entity
public class Inning extends Model  {

    @Id
    private Long idInning;

    @Constraints.Required
    @ManyToOne
    @JoinColumn(nullable = false)
    private Game game;

    @Constraints.Required
    @Column(nullable = false)
    private int inningNumber;

    @Constraints.Required
    @Column(nullable = false)
    private int home;

    @Constraints.Required
    @Column(nullable = false)
    private int away;

    public static Model.Finder<Long, Inning> finder = new Model.Finder<Long, Inning>(Inning.class);

    public Long getIdInning() {
        return idInning;
    }

    public void setIdInning(Long idInning) {
        this.idInning = idInning;
    }

    public int getInningNumber() {
        return inningNumber;
    }

    public void setInningNumber(int inningNumber) {
        this.inningNumber = inningNumber;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    public void setHomeAway(int home, int away)
    {
        this.home = home;
        this.away = away;
    }

    public Inning(Game game, int inningNumber, int home, int away) {
        this.game = game;
        this.inningNumber = inningNumber;
        this.home = home;
        this.away = away;
    }

    public Inning(int inningNumber, int home, int away) {
        this.inningNumber = inningNumber;
        this.home = home;
        this.away = away;

    }

    public static Inning getByNumberAndGame(Game game, int InningNumber){
        //EbeanServer server = Ebean.getServer("clients");
        return finder.where().eq("game_id_game", game.getIdGame()).eq("inning_number", InningNumber).findUnique();
    }
}
