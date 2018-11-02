package entity;

import static com.oracle.webservices.api.databinding.DatabindingModeFeature.ID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Parameter implements Serializable {
    @Id private int id;
    
    private String name;
    private String value;
    
    @ManyToMany(mappedBy = "parameters")
    private List<Configuration> configurations;

    public Parameter() {
        this.configurations = new ArrayList<>();
    }
    
    
    public Parameter(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.configurations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
    
    
    
    
    
    
}
