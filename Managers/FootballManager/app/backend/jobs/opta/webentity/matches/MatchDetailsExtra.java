
package backend.jobs.opta.webentity.matches;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "attendance",
    "matchOfficial"
})
public class MatchDetailsExtra {

    @JsonProperty("attendance")
    private String attendance;
    @JsonProperty("matchOfficial")
    private List<MatchOfficial> matchOfficial = null;

    @JsonProperty("attendance")
    public String getAttendance() {
        return attendance;
    }

    @JsonProperty("attendance")
    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @JsonProperty("matchOfficial")
    public List<MatchOfficial> getMatchOfficial() {
        return matchOfficial;
    }

    @JsonProperty("matchOfficial")
    public void setMatchOfficial(List<MatchOfficial> matchOfficial) {
        this.matchOfficial = matchOfficial;
    }

}
