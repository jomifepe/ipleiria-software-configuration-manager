package ejb;

import entity.ConfigurationType;
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
        administratorBean.create("luis", "123", "Luís Jejum Flures", "flures@fax.pt", "merjador");
        administratorBean.create("ze", "123", "José Pereira", "ze@plebking.pt", "sudo");
        administratorBean.create("ruben", "123", "Rúben Santos", "ruben@mail.pt", "pato");
        
        clientBean.create("valentim", "123", "Valentim Terra", "valentim@mail.pt", "Rua das ruas", "915548796");
        clientBean.create("aron", "123", "Aron Bado", "aron@mail.pt", "Travessa das travessas", "965984123");
        clientBean.create("maria", "123", "Maria Amelia", "maria@mail.pt", "Avenida das avenidas", "925863214");
        clientBean.create("joana", "123", "Joana Preto", "joana@mail.pt", "Quinta das quintas", "936581278");
             
        softwareBean.create(1, "Primavera", "v1.0");
        softwareBean.create(2, "NetBeans", "v13.0");
        softwareBean.create(3, "Pycharm", "v11.0");
        softwareBean.create(4, "Steam", "v19.2");
         
        parameterBean.create(1,"loginAttemps","10");      
        parameterBean.create(2,"version","v3.4.1");  
         
        moduleBean.create(1, "Recursos Humanos",1);
        moduleBean.addParameter(1,1);
        moduleBean.addParameter(1,2);
        
        moduleBean.addExtension(1, "Calculo empresarial");
        

        configurationBean.createTemplate(1,"Configuração Office 365",1,ConfigurationType.TEMPLATE);
        configurationBean.createTemplate(2,"Configuração NetBeans",2 ,ConfigurationType.TEMPLATE);
        configurationBean.createTemplate(3,"Configuração Pycharm",3,ConfigurationType.TEMPLATE);
        
        configurationBean.createConfiguration(4, "Melhor config para o Aron",1,"aron", ConfigurationType.CONFIGURATION,"Contrato de 6 meses",Status.ACTIVE);
         configurationBean.addHardware(4,"CPU: Intel core 2 duo");
        configurationBean.addHardware(4,"RAM: 4 GB ddr3");
        configurationBean.addHardware(4,"MB: Gigabyte");
        configurationBean.addLicense(4,"940-664-6353");
            
    }   
}
