
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "competitionCode",
    "competitionFormat",
    "country"
})
public class Competition {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("competitionCode")
    private String competitionCode;
    @JsonProperty("competitionFormat")
    private String competitionFormat;
    @JsonProperty("country")
    private CountryWebEntity country;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("competitionCode")
    public String getCompetitionCode() {
        return competitionCode;
    }

    @JsonProperty("competitionCode")
    public void setCompetitionCode(String competitionCode) {
        this.competitionCode = competitionCode;
    }

    @JsonProperty("competitionFormat")
    public String getCompetitionFormat() {
        return competitionFormat;
    }

    @JsonProperty("competitionFormat")
    public void setCompetitionFormat(String competitionFormat) {
        this.competitionFormat = competitionFormat;
    }

    @JsonProperty("country")
    public CountryWebEntity getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(CountryWebEntity country) {
        this.country = country;
    }

}
