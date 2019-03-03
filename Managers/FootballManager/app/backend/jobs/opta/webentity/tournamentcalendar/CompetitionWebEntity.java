
package backend.jobs.opta.webentity.tournamentcalendar;

import java.util.HashMap;
import java.util.List;
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
    "ocId",
    "opId",
    "name",
    "competitionCode",
    "displayOrder",
    "country",
    "countryId",
    "countryCode",
    "isFriendly",
    "competitionFormat",
    "type",
    "tournamentCalendar",
    "competitionType"
})
public class CompetitionWebEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ocId")
    private String ocId;
    @JsonProperty("opId")
    private String opId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("competitionCode")
    private String competitionCode;
    @JsonProperty("displayOrder")
    private Integer displayOrder;
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryId")
    private String countryId;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("isFriendly")
    private String isFriendly;
    @JsonProperty("competitionFormat")
    private String competitionFormat;
    @JsonProperty("type")
    private String type;
    @JsonProperty("tournamentCalendar")
    private List<TournamentCalendarWebEntity> tournamentCalendar = null;
    @JsonProperty("competitionType")
    private String competitionType;
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

    @JsonProperty("ocId")
    public String getOcId() {
        return ocId;
    }

    @JsonProperty("ocId")
    public void setOcId(String ocId) {
        this.ocId = ocId;
    }

    @JsonProperty("opId")
    public String getOpId() {
        return opId;
    }

    @JsonProperty("opId")
    public void setOpId(String opId) {
        this.opId = opId;
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

    @JsonProperty("displayOrder")
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    @JsonProperty("displayOrder")
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("countryId")
    public String getCountryId() {
        return countryId;
    }

    @JsonProperty("countryId")
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("isFriendly")
    public String getIsFriendly() {
        return isFriendly;
    }

    @JsonProperty("isFriendly")
    public void setIsFriendly(String isFriendly) {
        this.isFriendly = isFriendly;
    }

    @JsonProperty("competitionFormat")
    public String getCompetitionFormat() {
        return competitionFormat;
    }

    @JsonProperty("competitionFormat")
    public void setCompetitionFormat(String competitionFormat) {
        this.competitionFormat = competitionFormat;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("tournamentCalendar")
    public List<TournamentCalendarWebEntity> getTournamentCalendar() {
        return tournamentCalendar;
    }

    @JsonProperty("tournamentCalendar")
    public void setTournamentCalendar(List<TournamentCalendarWebEntity> tournamentCalendar) {
        this.tournamentCalendar = tournamentCalendar;
    }

    @JsonProperty("competitionType")
    public String getCompetitionType() {
        return competitionType;
    }

    @JsonProperty("competitionType")
    public void setCompetitionType(String competitionType) {
        this.competitionType = competitionType;
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
