
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contestantId",
    "periodId",
    "timeMin",
    "lastUpdated",
    "type",
    "scorerId",
    "scorerName",
    "assistPlayerId",
    "assistPlayerName",
    "optaEventId",
    "homeScore",
    "awayScore"
})
public class Goal {

    @JsonProperty("contestantId")
    private String contestantId;
    @JsonProperty("periodId")
    private Integer periodId;
    @JsonProperty("timeMin")
    private Integer timeMin;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("type")
    private String type;
    @JsonProperty("scorerId")
    private String scorerId;
    @JsonProperty("scorerName")
    private String scorerName;
    @JsonProperty("assistPlayerId")
    private String assistPlayerId;
    @JsonProperty("assistPlayerName")
    private String assistPlayerName;
    @JsonProperty("optaEventId")
    private String optaEventId;
    @JsonProperty("homeScore")
    private Integer homeScore;
    @JsonProperty("awayScore")
    private Integer awayScore;

    @JsonProperty("contestantId")
    public String getContestantId() {
        return contestantId;
    }

    @JsonProperty("contestantId")
    public void setContestantId(String contestantId) {
        this.contestantId = contestantId;
    }

    @JsonProperty("periodId")
    public Integer getPeriodId() {
        return periodId;
    }

    @JsonProperty("periodId")
    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    @JsonProperty("timeMin")
    public Integer getTimeMin() {
        return timeMin;
    }

    @JsonProperty("timeMin")
    public void setTimeMin(Integer timeMin) {
        this.timeMin = timeMin;
    }

    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("scorerId")
    public String getScorerId() {
        return scorerId;
    }

    @JsonProperty("scorerId")
    public void setScorerId(String scorerId) {
        this.scorerId = scorerId;
    }

    @JsonProperty("scorerName")
    public String getScorerName() {
        return scorerName;
    }

    @JsonProperty("scorerName")
    public void setScorerName(String scorerName) {
        this.scorerName = scorerName;
    }

    @JsonProperty("assistPlayerId")
    public String getAssistPlayerId() {
        return assistPlayerId;
    }

    @JsonProperty("assistPlayerId")
    public void setAssistPlayerId(String assistPlayerId) {
        this.assistPlayerId = assistPlayerId;
    }

    @JsonProperty("assistPlayerName")
    public String getAssistPlayerName() {
        return assistPlayerName;
    }

    @JsonProperty("assistPlayerName")
    public void setAssistPlayerName(String assistPlayerName) {
        this.assistPlayerName = assistPlayerName;
    }

    @JsonProperty("optaEventId")
    public String getOptaEventId() {
        return optaEventId;
    }

    @JsonProperty("optaEventId")
    public void setOptaEventId(String optaEventId) {
        this.optaEventId = optaEventId;
    }

    @JsonProperty("homeScore")
    public Integer getHomeScore() {
        return homeScore;
    }

    @JsonProperty("homeScore")
    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    @JsonProperty("awayScore")
    public Integer getAwayScore() {
        return awayScore;
    }

    @JsonProperty("awayScore")
    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

}
