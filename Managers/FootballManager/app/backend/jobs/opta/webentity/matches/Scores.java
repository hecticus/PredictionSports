
package backend.jobs.opta.webentity.matches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ht",
    "ft",
    "total"
})
public class Scores {

    @JsonProperty("ht")
    private Ht ht;
    @JsonProperty("ft")
    private Ft ft;
    @JsonProperty("total")
    private TotalWebEntity total;

    @JsonProperty("ht")
    public Ht getHt() {
        return ht;
    }

    @JsonProperty("ht")
    public void setHt(Ht ht) {
        this.ht = ht;
    }

    @JsonProperty("ft")
    public Ft getFt() {
        return ft;
    }

    @JsonProperty("ft")
    public void setFt(Ft ft) {
        this.ft = ft;
    }

    @JsonProperty("total")
    public TotalWebEntity getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(TotalWebEntity total) {
        this.total = total;
    }

}
