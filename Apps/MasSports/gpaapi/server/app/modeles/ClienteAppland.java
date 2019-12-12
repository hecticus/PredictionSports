package modeles;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClienteAppland extends Model {
    @Id
    public Long id;

    @Column(nullable = false)
    public String msisdn;

    @Column(nullable = false)
    public String identifier;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public long status;
}
