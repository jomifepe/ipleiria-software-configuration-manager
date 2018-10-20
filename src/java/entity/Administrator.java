package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
        {@NamedQuery(name = "getAllAdministrators", query = "SELECT a from Administrator a ORDER BY a.name")}
)
public class Administrator extends User implements Serializable {
    
    public Administrator(){
    }
    
    public Administrator(String username, String password, String name, String email) {
        super(username, password, name, email);
    }  
}
