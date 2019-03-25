
package backend.jobs.opta.webentity.matches;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "periodId",
    "matchStatus",
    "winner",
    "matchLengthMin",
    "matchLengthSec",
    "period",
    "scores"
})
public class MatchDetails {

    @JsonProperty("periodId")
    private Integer periodId;
    @JsonProperty("matchStatus")
    private String matchStatus;
    @JsonProperty("winner")
    private String winner;
    @JsonProperty("matchLengthMin")
    private Integer matchLengthMin;
    @JsonProperty("matchLengthSec")
    private Integer matchLengthSec;
    @JsonProperty("period")
    private List<Period> period = null;
    @JsonProperty("scores")
    private Scores scores;

    @JsonProperty("periodId")
    public Integer getPeriodId() {
        return periodId;
    }

    @JsonProperty("periodId")
    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    @JsonProperty("matchStatus")
    public String getMatchStatus() {
        return matchStatus;
    }

    @JsonProperty("matchStatus")
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    @JsonProperty("winner")
    public String getWinner() {
        return winner;
    }

    @JsonProperty("winner")
    public void setWinner(String winner) {
        this.winner = winner;
    }

    @JsonProperty("matchLengthMin")
    public Integer getMatchLengthMin() {
        return matchLengthMin;
    }

    @JsonProperty("matchLengthMin")
    public void setMatchLengthMin(Integer matchLengthMin) {
        this.matchLengthMin = matchLengthMin;
    }

    @JsonProperty("matchLengthSec")
    public Integer getMatchLengthSec() {
        return matchLengthSec;
    }

    @JsonProperty("matchLengthSec")
    public void setMatchLengthSec(Integer matchLengthSec) {
        this.matchLengthSec = matchLengthSec;
    }

    @JsonProperty("period")
    public List<Period> getPeriod() {
        return period;
    }

    @JsonProperty("period")
    public void setPeriod(List<Period> period) {
        this.period = period;
    }

    @JsonProperty("scores")
    public Scores getScores() {
        return scores;
    }

    @JsonProperty("scores")
    public void setScores(Scores scores) {
        this.scores = scores;
    }

}
