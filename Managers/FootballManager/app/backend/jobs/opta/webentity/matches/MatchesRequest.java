
package backend.jobs.opta.webentity.matches;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "match"
})
public class MatchesRequest {

    @JsonProperty("match")
    private List<MatchWebEntity> match = null;

    @JsonProperty("match")
    public List<MatchWebEntity> getMatch() {
        return match;
    }

    @JsonProperty("match")
    public void setMatch(List<MatchWebEntity> match) {
        this.match = match;
    }

}
