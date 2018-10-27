package ejb;

import dtos.SoftwareDTO;
import entity.Software;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SoftwareBean {
    
    @PersistenceContext
    EntityManager em;
    
   public void create(int id, String name, String baseVersion){
        try{
           Software software = new Software(id, name, baseVersion);      
            em.persist(software);
        }catch(Exception e){
            throw new EJBException("Problem creating Software (DB persist) -> " + e.getMessage());
        }
    }
    
   public void update(int id, String name, String baseVersion){
       try{
           Software software = em.find(Software.class, id);
           if(software==null){
               return ;
            }
            software.setName(name);
            software.setBaseVersion(baseVersion);
            
            em.merge(software);
            
        }catch(Exception e){
            throw new EJBException("Problem editing Software (DB persist) -> " + e.getMessage());
        }
   }
   
    public void remove(String username){
        try{
            Software software = em.find(Software.class, username);
            if(software == null){
                return;
            }
            em.remove(software);
        }catch(Exception e){
            throw new EJBException("Problem removing Software from DB -> " + e.getMessage());
        }
    }
    
    
    public List<SoftwareDTO> getAll(){
        try{
            List<Software> softwares = em.createNamedQuery("getAllSoftwares").getResultList(); 
            return softwaresToDTOs(softwares);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    SoftwareDTO softwareToDTO(Software software){
        return new SoftwareDTO(software.getId(),
                                    software.getName(), 
                                    software.getBaseVersion());
    }
    
    List<SoftwareDTO> softwaresToDTOs(List<Software> softwares){
        List<SoftwareDTO> dtos=new ArrayList<>();
        for(Software s: softwares){
            dtos.add(softwareToDTO(s));
        }
        return dtos;
    }
    
    
    
    
}
