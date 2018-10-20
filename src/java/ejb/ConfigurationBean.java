package ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class ConfigurationBean {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    private ClientBean clientBean;
    
    @EJB
    private AdministratorBean administratorBean;
    
    @EJB
    private SoftwareBean softwareBean;
    
    
    @PostConstruct
    public void populateDB() {
        
        administratorBean.create("luis", "123", "Luís Flores", "luis@mail.pt");
            
        clientBean.create("zepato", "123", "Ze", "ze@pato.pt");
        clientBean.create("ana", "123", "Ana Pereira", "ana@mail.pt");
        clientBean.create("maria", "123", "Maria Amelia", "maria@mail.pt");
        clientBean.create("joana", "123", "Joana Preto", "joana@mail.pt");
             
        softwareBean.create(1, "Office 365", "v1.0");
        softwareBean.create(2, "NetBeans", "v13.0");
        softwareBean.create(3, "Pycharm", "v11.0");
        softwareBean.create(4, "Steam", "v19.2");
        
        
        
        
        
    }
    
}
