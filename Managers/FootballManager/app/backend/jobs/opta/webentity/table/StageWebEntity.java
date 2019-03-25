
package backend.jobs.opta.webentity.table;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "formatId",
    "name",
    "vertical",
    "startDate",
    "endDate",
    "division"
})
public class StageWebEntity {

    @JsonProperty("id")
    private String id;
    @JsonProperty("formatId")
    private String formatId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("vertical")
    private Integer vertical;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("division")
    private List<Division> division = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("formatId")
    public String getFormatId() {
        return formatId;
    }

    @JsonProperty("formatId")
    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("vertical")
    public Integer getVertical() {
        return vertical;
    }

    @JsonProperty("vertical")
    public void setVertical(Integer vertical) {
        this.vertical = vertical;
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

    @JsonProperty("division")
    public List<Division> getDivision() {
        return division;
    }

    @JsonProperty("division")
    public void setDivision(List<Division> division) {
        this.division = division;
    }

}
