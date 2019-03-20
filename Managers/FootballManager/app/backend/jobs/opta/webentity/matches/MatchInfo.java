
package backend.jobs.opta.webentity.matches;

import java.util.List;

import backend.jobs.opta.webentity.table.StageWebEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "date",
    "time",
    "week",
    "lastUpdated",
    "description",
    "sport",
    "ruleset",
    "competition",
    "tournamentCalendar",
    "stage",
    "contestant",
    "venue"
})
public class MatchInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("time")
    private String time;
    @JsonProperty("week")
    private String week;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sport")
    private Sport sport;
    @JsonProperty("ruleset")
    private Ruleset ruleset;
    @JsonProperty("competition")
    private Competition competition;
    @JsonProperty("tournamentCalendar")
    private TournamentCalendar tournamentCalendar;
    @JsonProperty("stage")
    private StageWebEntity stage;
    @JsonProperty("contestant")
    private List<Contestant> contestant = null;
    @JsonProperty("venue")
    private VenueWebEntity venue;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("week")
    public String getWeek() {
        return week;
    }

    @JsonProperty("week")
    public void setWeek(String week) {
        this.week = week;
    }

    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("sport")
    public Sport getSport() {
        return sport;
    }

    @JsonProperty("sport")
    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @JsonProperty("ruleset")
    public Ruleset getRuleset() {
        return ruleset;
    }

    @JsonProperty("ruleset")
    public void setRuleset(Ruleset ruleset) {
        this.ruleset = ruleset;
    }

    @JsonProperty("competition")
    public Competition getCompetition() {
        return competition;
    }

    @JsonProperty("competition")
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @JsonProperty("tournamentCalendar")
    public TournamentCalendar getTournamentCalendar() {
        return tournamentCalendar;
    }

    @JsonProperty("tournamentCalendar")
    public void setTournamentCalendar(TournamentCalendar tournamentCalendar) {
        this.tournamentCalendar = tournamentCalendar;
    }

    @JsonProperty("stage")
    public StageWebEntity getStageWebEntity() {
        return stage;
    }

    @JsonProperty("stage")
    public void setStageWebEntity(StageWebEntity stage) {
        this.stage = stage;
    }

    @JsonProperty("contestant")
    public List<Contestant> getContestant() {
        return contestant;
    }

    @JsonProperty("contestant")
    public void setContestant(List<Contestant> contestant) {
        this.contestant = contestant;
    }

    @JsonProperty("venue")
    public VenueWebEntity getVenue() {
        return venue;
    }

    @JsonProperty("venue")
    public void setVenue(VenueWebEntity venue) {
        this.venue = venue;
    }

}
