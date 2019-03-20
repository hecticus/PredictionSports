
package backend.jobs.opta.webentity.table;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sport",
    "ruleset",
    "competition",
    "tournamentCalendar",
    "stage",
    "lastUpdated"
})
public class TableRequest {

    @JsonProperty("sport")
    private Sport sport;
    @JsonProperty("ruleset")
    private Ruleset ruleset;
    @JsonProperty("competition")
    private Competition competition;
    @JsonProperty("tournamentCalendar")
    private TournamentCalendar tournamentCalendar;
    @JsonProperty("stage")
    private List<StageWebEntity> stage = null;
    @JsonProperty("lastUpdated")
    private String lastUpdated;

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
    public List<StageWebEntity> getStage() {
        return stage;
    }

    @JsonProperty("stage")
    public void setStage(List<StageWebEntity> stage) {
        this.stage = stage;
    }

    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
