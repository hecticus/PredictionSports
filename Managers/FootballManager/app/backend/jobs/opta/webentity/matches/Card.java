
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
    "playerId",
    "playerName",
    "optaEventId"
})
public class Card {

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
    @JsonProperty("playerId")
    private String playerId;
    @JsonProperty("playerName")
    private String playerName;
    @JsonProperty("optaEventId")
    private String optaEventId;

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

    @JsonProperty("playerId")
    public String getPlayerId() {
        return playerId;
    }

    @JsonProperty("playerId")
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @JsonProperty("playerName")
    public String getPlayerName() {
        return playerName;
    }

    @JsonProperty("playerName")
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @JsonProperty("optaEventId")
    public String getOptaEventId() {
        return optaEventId;
    }

    @JsonProperty("optaEventId")
    public void setOptaEventId(String optaEventId) {
        this.optaEventId = optaEventId;
    }

}
