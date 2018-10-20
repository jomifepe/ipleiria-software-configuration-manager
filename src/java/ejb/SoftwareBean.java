package ejb;

import entity.Software;
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
    
    
    public List<Software> getAll(){
        try{
            List<Software> softwares = em.createNamedQuery("getAllSoftwares").getResultList(); 
            return softwares;
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    
    public void update(int id, String name, String baseVersion){
        try{
            Software software = (Software) em.find(Software.class, id);
            if(software == null){
                return;
            }   
            
            software.setId(id);
            software.setName(name);
            software.setBaseVersion(baseVersion);  
            em.merge(software);     
        }catch(Exception e){
            throw new EJBException("Problem updating Software DB -> " + e.getMessage());
        }
    }
    
}
