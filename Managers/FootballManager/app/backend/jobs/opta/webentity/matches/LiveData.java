
package backend.jobs.opta.webentity.matches;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "matchDetails",
    "goal",
    "card",
    "substitute",
    "matchDetailsExtra"
})
public class LiveData {

    @JsonProperty("matchDetails")
    private MatchDetails matchDetails;
    @JsonProperty("goal")
    private List<Goal> goal = null;
    @JsonProperty("card")
    private List<Card> card = null;
    @JsonProperty("substitute")
    private List<Substitute> substitute = null;
    @JsonProperty("matchDetailsExtra")
    private MatchDetailsExtra matchDetailsExtra;

    @JsonProperty("matchDetails")
    public MatchDetails getMatchDetails() {
        return matchDetails;
    }

    @JsonProperty("matchDetails")
    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }

    @JsonProperty("goal")
    public List<Goal> getGoal() {
        return goal;
    }

    @JsonProperty("goal")
    public void setGoal(List<Goal> goal) {
        this.goal = goal;
    }

    @JsonProperty("card")
    public List<Card> getCard() {
        return card;
    }

    @JsonProperty("card")
    public void setCard(List<Card> card) {
        this.card = card;
    }

    @JsonProperty("substitute")
    public List<Substitute> getSubstitute() {
        return substitute;
    }

    @JsonProperty("substitute")
    public void setSubstitute(List<Substitute> substitute) {
        this.substitute = substitute;
    }

    @JsonProperty("matchDetailsExtra")
    public MatchDetailsExtra getMatchDetailsExtra() {
        return matchDetailsExtra;
    }

    @JsonProperty("matchDetailsExtra")
    public void setMatchDetailsExtra(MatchDetailsExtra matchDetailsExtra) {
        this.matchDetailsExtra = matchDetailsExtra;
    }

}
