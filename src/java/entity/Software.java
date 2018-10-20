package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SOFTWARE", uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"}))
public class Software implements Serializable {

    @Id
    private Long id;
    private String name;
    private String baseVersion;
    
//    @OneToMany(mappedBy = "software", cascade = CascadeType.REMOVE)
    private Configuration configuration;

    public Software() {
    }

    public Software(Long id, String name, String baseVersion, Configuration configuration) {
        this.id = id;
        this.name = name;
        this.baseVersion = baseVersion;
        this.configuration = configuration;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(String baseVersion) {
        this.baseVersion = baseVersion;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
}
