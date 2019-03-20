
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "start",
    "end",
    "lengthMin",
    "lengthSec"
})
public class Period {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("start")
    private String start;
    @JsonProperty("end")
    private String end;
    @JsonProperty("lengthMin")
    private Integer lengthMin;
    @JsonProperty("lengthSec")
    private Integer lengthSec;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("end")
    public String getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(String end) {
        this.end = end;
    }

    @JsonProperty("lengthMin")
    public Integer getLengthMin() {
        return lengthMin;
    }

    @JsonProperty("lengthMin")
    public void setLengthMin(Integer lengthMin) {
        this.lengthMin = lengthMin;
    }

    @JsonProperty("lengthSec")
    public Integer getLengthSec() {
        return lengthSec;
    }

    @JsonProperty("lengthSec")
    public void setLengthSec(Integer lengthSec) {
        this.lengthSec = lengthSec;
    }

}
