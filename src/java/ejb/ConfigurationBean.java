package ejb;

import dtos.ConfigurationDTO;
import dtos.ModuleDTO;
import dtos.SoftwareDTO;
import entity.Client;
import entity.Configuration;
import entity.Module;
import entity.Software;
import entity.ConfigurationType;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConfigurationBean {

    @PersistenceContext
    EntityManager em;
    
    public ConfigurationBean() {
    }
    
    public void createTemplate(int id,String description, int software_id, ConfigurationType configurationType){
        try{
            Software software=em.find(Software.class, software_id);
            if(software==null){
                return ;
            }

            Configuration configuration = new Configuration(id,description,software,configurationType);

            software.addConfiguration(configuration);
            em.persist(configuration);
            em.merge(software);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public void createConfiguration(int id,String description, int templateId, String username,ConfigurationType configurationType,String contractInfo, Status status){
        try{     
            Client client = em.find(Client.class, username);
            if(client==null){
                return ;
            }
            Configuration template = em.find(Configuration.class, templateId);
           
            Software software = em.find(Software.class, template.getSoftware().getId());
            if(software==null){
                return ;
            }
   
            Configuration configuration = new Configuration(id, description, software, template.getHardware(), template.getLicences(),contractInfo, status, configurationType, templateId);
             
            software.addConfiguration(configuration);
            configuration.addClient(client);
            client.addConfiguration(configuration);
            
            em.persist(configuration);
            em.merge(software);
            em.merge(client);
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
    
    public void updateTemplate(int id,String description){
        try{
            Configuration configuration=em.find(Configuration.class, id);
            if(configuration==null){
                return ;
            }

            configuration.setDescription(description);
            em.merge(configuration);
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
     
     public List<ConfigurationDTO> getCurrentSoftwareTempates(int id) {
         try{
            Software software = em.find(Software.class, id);
            if(software==null){
                return null;
            } 
            return templatesToDTOs(software.getTemplates());
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
            List<Configuration> configs = client.getConfigurations();
            return configurationsToDTOs(configs);
         }catch(Exception e){
             throw new EJBException(e.getMessage());
         }
     }
     
     public SoftwareDTO getClientConfigurationSoftware(int id) {
        try{
           Configuration config = em.find(Configuration.class, id);
            if(config == null){
                return null;
            }  
            return SoftwareBean.softwareToDTO(config.getSoftware());   
         }catch(Exception e){
             throw new EJBException(e.getMessage());
         } 
    }
   
    public static ConfigurationDTO templateToDTO(Configuration config){
         return new ConfigurationDTO(
                config.getId(),
                config.getDescription(),
                config.getSoftware().getId(),
                config.getSoftware().getName(),
                config.getHardware(),
                config.getTemplateId(),
                config.getConfigurationType());
    }
     
     public static List<ConfigurationDTO> templatesToDTOs(List<Configuration> configurations){
        List<ConfigurationDTO> dtos=new ArrayList<>();
        for(Configuration s: configurations){
            dtos.add(templateToDTO(s));
        }
        return dtos;
    }
    
     public static ConfigurationDTO ConfigurationToDTO(Configuration config){
        return new ConfigurationDTO(
                config.getId(),
                config.getDescription(),
                config.getSoftware().getId(),
                config.getSoftware().getName(),
                config.getContractInfo(),
                config.getStatus().toString(),
                config.getHardware(),
                config.getTemplateId(),
                config.getConfigurationType(),
                config.getLicences()
                );
    }
    
    public static List<ConfigurationDTO> configurationsToDTOs(List<Configuration> configurations){
        List<ConfigurationDTO> dtos=new ArrayList<>();
        for(Configuration s: configurations){
            dtos.add(ConfigurationToDTO(s));
        }
        return dtos;
    }

    public void deleteTemplate(int id) {
       try {
            Configuration configuration = em.find(Configuration.class, id);
            if(configuration == null){
                return;
            }
            
             List<Configuration> configurations = em.createNamedQuery("getAllConfigurations").getResultList(); 
            
             for(Configuration c: configurations){
                 if(c.getTemplateId()==id){
                     em.remove(c);
                 }
             }
             Software software=configuration.getSoftware();
             software.remveConfiguration(configuration);
            
            em.remove(configuration);
        } catch(Exception e) {
            throw new EJBException("Problem removing Template from DB -> " + e.getMessage());
        }
    }

    

    
   
}
