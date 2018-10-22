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
public class TestBean {

    @PersistenceContext
    EntityManager em;
    
    @EJB private ClientBean clientBean;    
    @EJB private AdministratorBean administratorBean;    
    @EJB private SoftwareBean softwareBean;
    
    @PostConstruct
    public void populateDB() {
        administratorBean.create("luis", "123", "Luís Jejum Flures", "flures@fax.pt", "pleb");
        administratorBean.create("ze", "123", "José Pereira", "ze@plebking.pt", "sudo");
        
        clientBean.create("valentim", "123", "Valentim Terra", "valentim@mail.pt", "Rua das ruas", "915548796");
        clientBean.create("aron", "123", "Aron Bado", "aron@mail.pt", "Travessa das travessas", "965984123");
        clientBean.create("maria", "123", "Maria Amelia", "maria@mail.pt", "Avenida das avenidas", "925863214");
        clientBean.create("joana", "123", "Joana Preto", "joana@mail.pt", "Quinta das quintas", "936581278");
             
        softwareBean.create(1, "Office 365", "v1.0");
        softwareBean.create(2, "NetBeans", "v13.0");
        softwareBean.create(3, "Pycharm", "v11.0");
        softwareBean.create(4, "Steam", "v19.2");
    }   
}
