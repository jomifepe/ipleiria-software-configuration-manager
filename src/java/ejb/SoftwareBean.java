package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SoftwareBean {
    
    @PersistenceContext
    EntityManager em;
    
    public SoftwareBean() {
    }
    
}
