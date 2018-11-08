package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SOFTWARE", uniqueConstraints = @UniqueConstraint(columnNames = {"NAME"}))
@NamedQueries({
    @NamedQuery(name = "getAllSoftwares", query = "SELECT s from Software s ORDER BY s.name")
})
public class Software implements Serializable {

    @Id private int id;
    private String name;
    private String baseVersion;
    
    @OneToMany(mappedBy = "software", cascade = CascadeType.REMOVE)
    private List<Configuration> configurations;

    
    @OneToMany(mappedBy = "software", cascade = CascadeType.REMOVE)
    private List<Module> modules;
    
    public Software() {
        configurations = new ArrayList<>();
        modules = new ArrayList<>();
    }

    
    public Software(int id, String name, String baseVersion) {
        this.id = id;
        this.name = name;
        this.baseVersion = baseVersion; 
        this.configurations = new ArrayList<>();
        this.modules = new ArrayList<>();
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
    
    public void addConfiguration(Configuration config){
        configurations.add(config);
    }
    
    public void remveConfiguration(Configuration config){
        configurations.remove(config);
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

    public String getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(String baseVersion) {
        this.baseVersion = baseVersion;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
    
    public List<Configuration> getTemplates(){
        List<Configuration> templates=new ArrayList<>();
        for(Configuration c:configurations){
            if(c.getConfigurationType()==ConfigurationType.TEMPLATE){
                templates.add(c);
            }
        }
        
        return templates;
    }
    
    public void addModule(Module module){
        this.modules.add(module);
    }
}
