package ejb;

import dtos.ConfigurationDTO;
import dtos.SoftwareDTO;
import entity.Client;
import entity.Configuration;
import entity.Module;
import entity.Parameter;
import entity.Software;
import entity.User;
import entity.Administrator;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConfigurationBean {

    @PersistenceContext
    EntityManager em;
    
    public ConfigurationBean() {
    }
    
    public void create(int id,String description, int software_id){
        try{
            Software software=em.find(Software.class, software_id);
            if(software==null){
                return ;
            }
            
            Configuration configuration = new Configuration(id,description,software);
            
            software.addConfiguration(configuration);
            em.persist(configuration);
            em.merge(software);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<ConfigurationDTO> getAll(){
        try{
            List<Configuration> configurations = em.createNamedQuery("getAllConfigurations").getResultList(); 
            return configurationsToDTOs(configurations);
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

     public List<ConfigurationDTO> getConfigurationsForSoftware(int id) {
         try{
            Software software = em.find(Software.class, id);
            if(software==null){
                return null;
            } 
            return configurationsToDTOs(software.getConfigurations());
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
     
     public List<ConfigurationDTO> getClientConfigurations(String username){
         try{
            Client client = em.find(Client.class, username);
            if(client == null){
                return null;
            }
             System.out.println(client.getName());
            List<Configuration> configs = client.getConfigurations();
            return configurationsToDTOs(configs);
         }catch(Exception e){
             throw new EJBException(e.getMessage());
         }
     }
     
     
     ConfigurationDTO ConfigurationToDTO(Configuration config){
        return new ConfigurationDTO(config.getId(),
                             config.getDescription(), 
                             config.getSoftware().getId(),
                             config.getSoftware().getName());
    }
    
    List<ConfigurationDTO> configurationsToDTOs(List<Configuration> configurations){
        List<ConfigurationDTO> dtos=new ArrayList<>();
        for(Configuration s: configurations){
            dtos.add(ConfigurationToDTO(s));
        }
        return dtos;
    }

    
   
}
