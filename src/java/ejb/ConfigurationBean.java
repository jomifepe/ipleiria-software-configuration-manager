package ejb;

import entity.Client;
import entity.Configuration;
import entity.Module;
import entity.Parameter;
import entity.Software;
import entity.Status;
import java.util.List;
import javax.ejb.EJBException;
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
    
    
    public void create(int id,String description, int software_id, String contractInfo, Status status){
        try{
            Software software=em.find(Software.class, software_id);
            if(software==null){
                return ;
            }
            
            Configuration configuration = new Configuration(id,description,software,contractInfo, status);
            
            software.addConfiguration(configuration);
            em.persist(configuration);
            em.merge(software);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Configuration> getAll(){
        try{
            List<Configuration> clients = em.createNamedQuery("getAllConfigurations").getResultList(); 
            return clients;
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public void addModule(int id_config, int id_module){
        try{
            Configuration configuration = em.find(Configuration.class, id_config);
            if(configuration==null){
                return;
            }
            Module module = em.find(Module.class, id_module);
            if(module==null){
                return;
            }
            
            configuration.addModule(module);
            
            em.persist(configuration);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
   
    public void addHardware(int id_config, String hardware){
        try{
            Configuration configuration = em.find(Configuration.class, id_config);
            if(configuration==null){
                return;
            }            
            configuration.addHardware(hardware);
            
            em.persist(configuration);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public void addLicense(int id_config, String license){
        try{
            Configuration configuration = em.find(Configuration.class, id_config);
            if(configuration==null){
                return;
            }            
            configuration.addLicense(license);
            
            em.persist(configuration);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public void addLicense(int id_config, int id_parameter){
        try{
            Configuration configuration = em.find(Configuration.class, id_config);
            if(configuration==null){
                return;
            } 
            
            Parameter parameter = em.find(Parameter.class, id_parameter);
            if(parameter==null){
                return;
            } 
            
            configuration.addParameter(parameter);
            
            em.persist(configuration);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public void addExtension(int id_config,String extension){
        try{
            Configuration configuration = em.find(Configuration.class, id_config);
            if(configuration==null){
                return;
            } 
            
            
            configuration.addExtension(extension);
           
            em.persist(configuration);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
     public void addParameter(int id_config,int id_param){
        try{
            Configuration configuration = em.find(Configuration.class, id_config);
            if(configuration==null){
                return;
            } 
            Parameter parameter=em.find(Parameter.class, id_param);
            if(parameter==null){
                return;
            } 
            
            configuration.addParameter(parameter);
           
            em.persist(configuration);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
}
