package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Module implements Serializable { 
    @Id
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "modules")
    private List<Configuration> configurations;

    public Module() {
        this.configurations=new LinkedList<>();
    }
    
    
    
    public Module(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.configurations=new LinkedList<>();
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
    
    
}
