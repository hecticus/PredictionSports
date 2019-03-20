
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
    "playerOnId",
    "playerOnName",
    "playerOffId",
    "playerOffName"
})
public class Substitute {

    @JsonProperty("contestantId")
    private String contestantId;
    @JsonProperty("periodId")
    private Integer periodId;
    @JsonProperty("timeMin")
    private Integer timeMin;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("playerOnId")
    private String playerOnId;
    @JsonProperty("playerOnName")
    private String playerOnName;
    @JsonProperty("playerOffId")
    private String playerOffId;
    @JsonProperty("playerOffName")
    private String playerOffName;

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

    @JsonProperty("playerOnId")
    public String getPlayerOnId() {
        return playerOnId;
    }

    @JsonProperty("playerOnId")
    public void setPlayerOnId(String playerOnId) {
        this.playerOnId = playerOnId;
    }

    @JsonProperty("playerOnName")
    public String getPlayerOnName() {
        return playerOnName;
    }

    @JsonProperty("playerOnName")
    public void setPlayerOnName(String playerOnName) {
        this.playerOnName = playerOnName;
    }

    @JsonProperty("playerOffId")
    public String getPlayerOffId() {
        return playerOffId;
    }

    @JsonProperty("playerOffId")
    public void setPlayerOffId(String playerOffId) {
        this.playerOffId = playerOffId;
    }

    @JsonProperty("playerOffName")
    public String getPlayerOffName() {
        return playerOffName;
    }

    @JsonProperty("playerOffName")
    public void setPlayerOffName(String playerOffName) {
        this.playerOffName = playerOffName;
    }

}
