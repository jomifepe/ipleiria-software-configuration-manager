package ejb;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConfigurationBean {

    @PersistenceContext
    EntityManager em;
    
    public ConfigurationBean() {
    }
    
}
