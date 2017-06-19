package modeles;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Config extends Model {

    @Id
    private Long id;

    @Constraints.Required
    @Constraints.MaxLength(50)
    @Column(length = 50, nullable = false)
    private String configKey;

    @Constraints.Required
    @Column(nullable = false)
    private String value;

    private String description;

    public static Model.Finder<Long, Config> finder = new Model.Finder<Long, Config>(Config.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getString(String key){
        Config config = finder.where().eq("configKey", key).findUnique();
        return config.getValue();
    }

    public static void setString(String key, String value){
        Config config = finder.where().eq("configKey", key).findUnique();
        if(config != null){
            config.setValue(value);
            config.update();
        }else{
            config = new Config();
            config.setConfigKey(key);
            config.setValue(value);
            config.save();
        }
    }

    public static void setString(String key, String value, String description){
        Config config = finder.where().eq("configKey", key).findUnique();
        if(config != null){
            config.setValue(value);
            config.setDescription(description);
            config.update();
        }else{
            config = new Config();
            config.setConfigKey(key);
            config.setValue(value);
            config.setDescription(description);
            config.save();
        }
    }
}
