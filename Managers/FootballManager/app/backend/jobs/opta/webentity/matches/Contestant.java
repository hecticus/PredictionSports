
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "shortName",
    "officialName",
    "code",
    "position",
    "country"
})
public class Contestant {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("officialName")
    private String officialName;
    @JsonProperty("code")
    private String code;
    @JsonProperty("position")
    private String position;
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

    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @JsonProperty("officialName")
    public String getOfficialName() {
        return officialName;
    }

    @JsonProperty("officialName")
    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
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
