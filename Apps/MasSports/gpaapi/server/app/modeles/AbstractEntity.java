package modeles;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import play.data.format.Formats;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *
 *
 * @author  Yenny Fung
 * @since   2016
 */
@MappedSuperclass
public abstract class AbstractEntity extends Model{

    @Id
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
