package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllClients", query = "SELECT c from Client c ORDER BY c.name")
})
public class Client extends User implements Serializable {
    @NotNull private String address;
    @NotNull private String contact;
    
    @ManyToMany(mappedBy = "clients")
    private List<Configuration> configurations;

    public Client() {
        configurations = new ArrayList<>();
    }

    public Client(String username, String password, String name, String email, String address, String contact) {
        super(username, password, name, email);
        this.address = address;
        this.contact = contact;
        configurations = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
