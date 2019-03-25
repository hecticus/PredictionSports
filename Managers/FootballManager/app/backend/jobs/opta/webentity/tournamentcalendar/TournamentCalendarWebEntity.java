
package backend.jobs.opta.webentity.tournamentcalendar;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "includesVenues",
    "ocId",
    "name",
    "startDate",
    "endDate",
    "active",
    "lastUpdated",
    "includesStandings"
})
public class TournamentCalendarWebEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("includesVenues")
    private String includesVenues;
    @JsonProperty("ocId")
    private String ocId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("active")
    private String active;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("includesStandings")
    private String includesStandings;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("includesVenues")
    public String getIncludesVenues() {
        return includesVenues;
    }

    @JsonProperty("includesVenues")
    public void setIncludesVenues(String includesVenues) {
        this.includesVenues = includesVenues;
    }

    @JsonProperty("ocId")
    public String getOcId() {
        return ocId;
    }

    @JsonProperty("ocId")
    public void setOcId(String ocId) {
        this.ocId = ocId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("active")
    public String getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(String active) {
        this.active = active;
    }

    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("includesStandings")
    public String getIncludesStandings() {
        return includesStandings;
    }

    @JsonProperty("includesStandings")
    public void setIncludesStandings(String includesStandings) {
        this.includesStandings = includesStandings;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
