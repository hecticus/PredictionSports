
package backend.jobs.opta.webentity.team;

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
        "type",
        "teamType",
        "countryId",
        "country",
        "status",
        "city",
        "postalAddress",
        "addressZip",
        "founded",
        "lastUpdated"
})
public class ContestantWebEntity {

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
    @JsonProperty("type")
    private String type;
    @JsonProperty("teamType")
    private String teamType;
    @JsonProperty("countryId")
    private String countryId;
    @JsonProperty("country")
    private String country;
    @JsonProperty("status")
    private String status;
    @JsonProperty("city")
    private String city;
    @JsonProperty("postalAddress")
    private String postalAddress;
    @JsonProperty("addressZip")
    private String addressZip;
    @JsonProperty("founded")
    private String founded;
    @JsonProperty("lastUpdated")
    private String lastUpdated;

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

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("teamType")
    public String getTeamType() {
        return teamType;
    }

    @JsonProperty("teamType")
    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    @JsonProperty("countryId")
    public String getCountryId() {
        return countryId;
    }

    @JsonProperty("countryId")
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("postalAddress")
    public String getPostalAddress() {
        return postalAddress;
    }

    @JsonProperty("postalAddress")
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    @JsonProperty("addressZip")
    public String getAddressZip() {
        return addressZip;
    }

    @JsonProperty("addressZip")
    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    @JsonProperty("founded")
    public String getFounded() {
        return founded;
    }

    @JsonProperty("founded")
    public void setFounded(String founded) {
        this.founded = founded;
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
