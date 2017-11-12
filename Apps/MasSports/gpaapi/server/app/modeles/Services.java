package modeles;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import com.avaje.ebean.Model;
import play.data.format.Formats;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ferck on 14/6/2017.
 */

@Entity
public class Services extends Model  {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Constraints.MaxLength(16)
    @Column(length = 16, nullable = false)
    protected String name;

    @Constraints.MaxLength(16)
    @Column(length = 16, nullable = false)
    protected String identifier;

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @Constraints.MaxLength(150)
    @Column(length = 150, nullable = false)
    protected String sms;

    protected int shortCode;

    protected String productIdentifier;

    protected String descripcionProducto;

//    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
//    private List<Clients> clients = new ArrayList<>();

    public static Model.Finder<Long, Services> finder = new Model.Finder<Long, Services>(Services.class);

    public Services(String name, String identifier, String sms, int shortCode, String productIdentifier, String descripcionProducto) {
        this.name = name;
        this.identifier = identifier;
        this.sms = sms;
        this.shortCode = shortCode;
        this.productIdentifier = productIdentifier;
        this.descripcionProducto = descripcionProducto;
    }

    public Services() {
    }

    public String getName() {
        return name;
    }

    public String getProductIdentifier() { return productIdentifier;   }

    public void setProductIdentifier(String productIdentifier) {   this.productIdentifier = productIdentifier; }

    public String getDescripcionProducto() { return descripcionProducto;   }

    public void setDescripcionProducto(String descripcionProducto) {   this.descripcionProducto = descripcionProducto; }

    public void setName(String name) {  this.name = name;   }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getShortCode() {
        return shortCode;
    }

    public void setShortCode(int shortCode) {
        this.shortCode = shortCode;
    }

    public Services getServiceByName(String identifier)
    {
        //1	md	1	Tu pin es: @pin. Ingresalo en la pagina web para continuar.	9090	COPA	test

        Services obj = new Services();
        obj.setName("md");
        obj.setIdentifier("1");
        obj.setSms("Tu pin es: @pin. Ingresalo en la pagina web para continuar.");
        obj.setShortCode(9090);
        obj.setProductIdentifier("COPA");
        obj.setDescripcionProducto("test");
        return obj;

        //return finder.where().eq("name", identifier).findUnique();
    }
}
