package modeles;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Id;

public class ClienteRender extends Model {
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
