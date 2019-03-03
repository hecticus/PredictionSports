
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "matchInfo"
})
public class MatchWebEntity {

    @JsonProperty("matchInfo")
    private MatchInfo matchInfo;

    @JsonProperty("matchInfo")
    public MatchInfo getMatchInfo() {
        return matchInfo;
    }

    @JsonProperty("matchInfo")
    public void setMatchInfo(MatchInfo matchInfo) {
        this.matchInfo = matchInfo;
    }

}
