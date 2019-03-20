
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "home",
    "away"
})
public class TotalWebEntity {

    @JsonProperty("home")
    private Integer home;
    @JsonProperty("away")
    private Integer away;

    @JsonProperty("home")
    public Integer getHome() {
        return home;
    }

    @JsonProperty("home")
    public void setHome(Integer home) {
        this.home = home;
    }

    @JsonProperty("away")
    public Integer getAway() {
        return away;
    }

    @JsonProperty("away")
    public void setAway(Integer away) {
        this.away = away;
    }

}
