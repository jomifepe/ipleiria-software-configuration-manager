package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "MODULES")
public class Module implements Serializable { 
    @Id private int id;
    private String name;
    
    @OneToMany(mappedBy = "module", cascade = CascadeType.REMOVE)
    private List<Parameter> parameters;
    
    private List<String> extensions;
    
    
    @ManyToOne
    @JoinColumn(name="SOFTWARE_ID")
    private Software software;
        
    public Module() {
        this.parameters = new ArrayList<>();
        this.extensions=new ArrayList<>();
    }
    
    public Module(Integer id, String name, Software software) {
        this.id = id;
        this.name = name;
        this.software=software;
        this.parameters = new ArrayList<>();
        this.extensions=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addParameter(Parameter parameter){
        this.parameters.add(parameter);
    }
    
    public void addExtension(String extension){
        this.extensions.add(extension);
    }
    
}
