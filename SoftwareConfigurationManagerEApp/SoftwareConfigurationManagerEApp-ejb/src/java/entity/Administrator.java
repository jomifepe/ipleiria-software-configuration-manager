package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "getAllAdministrators", query = "SELECT a from Administrator a ORDER BY a.name")
})
public class Administrator extends User implements Serializable {
    
    @NotNull 
    private String role;
    
    public Administrator() {
    }
    
    public Administrator(String username, String password, String name, String email, String role) {
        super(username, password, name, email);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
