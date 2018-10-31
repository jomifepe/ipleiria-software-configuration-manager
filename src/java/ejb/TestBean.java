package ejb;

import entity.Status;
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
    @EJB private ConfigurationBean configurationBean;
    @EJB private ModuleBean moduleBean;
    @EJB private ParameterBean parameterBean;
    
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
        
        moduleBean.create(1, "One Drive");
        moduleBean.create(2, "Dropbox");
        
        parameterBean.create(1,"loginAttemps","10");
        
        configurationBean.create(1,"Configuração Office 365",1);
        configurationBean.addModule(1,1);
        configurationBean.addHardware(1,"CPU: Intel core 2 duo");
        configurationBean.addHardware(1,"RAM: 4 GB ddr3");
        configurationBean.addHardware(1,"MB: Gigabyte");
        configurationBean.addLicense(1,"940-664-6353");
        configurationBean.addParameter(1,1);
        configurationBean.addExtension(1,"plugins para google docs");
        
        configurationBean.create(2,"Configuração NetBeans",2);
        configurationBean.create(3,"Configuração Pycharm",3);
        
        clientBean.addConfiguration("aron", 1);
        
            
    }   
}
