package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries(
        {@NamedQuery(name = "getAllClients", query = "SELECT c from Client c ORDER BY c.name")}
)
public class Client extends User implements Serializable {
    
    @ManyToMany(mappedBy = "clients")
    private List<Configuration> configurations;

    public Client() {
    }

    public Client(String username, String password, String name, String email) {
        super(username, password, name, email);
    }
    
    
    public void addConfiguration(Configuration config){
        configurations.add(config);
    }
    
    public void removeConfiguration(Configuration config){
        configurations.remove(config);
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
      
}
