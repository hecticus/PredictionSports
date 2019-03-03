
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "neutral",
    "longName",
    "shortName"
})
public class Venue {

    @JsonProperty("id")
    private String id;
    @JsonProperty("neutral")
    private String neutral;
    @JsonProperty("longName")
    private String longName;
    @JsonProperty("shortName")
    private String shortName;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("neutral")
    public String getNeutral() {
        return neutral;
    }

    @JsonProperty("neutral")
    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }

    @JsonProperty("longName")
    public String getLongName() {
        return longName;
    }

    @JsonProperty("longName")
    public void setLongName(String longName) {
        this.longName = longName;
    }

    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
