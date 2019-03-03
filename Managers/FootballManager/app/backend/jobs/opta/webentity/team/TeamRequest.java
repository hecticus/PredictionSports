
package backend.jobs.opta.webentity.team;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "contestant",
    "lastUpdated"
})
public class TeamRequest {

    @JsonProperty("contestant")
    private List<ContestantWebEntity> contestant = null;
    @JsonProperty("lastUpdated")
    private String lastUpdated;

    @JsonProperty("contestant")
    public List<ContestantWebEntity> getContestant() {
        return contestant;
    }

    @JsonProperty("contestant")
    public void setContestant(List<ContestantWebEntity> contestant) {
        this.contestant = contestant;
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
