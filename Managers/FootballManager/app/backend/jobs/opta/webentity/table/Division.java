
package backend.jobs.opta.webentity.table;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "ranking"
})
public class Division {

    @JsonProperty("type")
    private String type;
    @JsonProperty("ranking")
    private List<Ranking> ranking = null;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("ranking")
    public List<Ranking> getRanking() {
        return ranking;
    }

    @JsonProperty("ranking")
    public void setRanking(List<Ranking> ranking) {
        this.ranking = ranking;
    }

}
