package ejb;

import entity.Administrator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdministratorBean {

    @PersistenceContext
    EntityManager em;
    
    public void create(String username, String password, String name, String email, String role){
        try{
           Administrator administrator = new Administrator(username, password, name, email, role);      
           em.persist(administrator);
        }catch(Exception e){
            throw new EJBException("Problem creating Administrator (DB persist) -> " + e.getMessage());
        }
    }
    
    public void remove(String username){
        try{
            Administrator administrator = em.find(Administrator.class, username);
            if(administrator == null){
                return;
            }
            em.remove(administrator);
        }catch(Exception e){
            throw new EJBException("Problem removing Administrator from DB -> " + e.getMessage());
        }
    }
    
    
    public List<Administrator> getAll(){
        try{
            List<Administrator> administrators = em.createNamedQuery("getAllAdministrators").getResultList(); 
            return administrators;
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    
    public void update(String username, String password, String name, String email){
        try{
            Administrator administrator = (Administrator) em.find(Administrator.class, username);
            if(administrator == null){
                return;
            }   
            administrator.setUsername(username);
            administrator.setPassword(password);
            administrator.setName(name);
            administrator.setEmail(email);
            em.merge(administrator);     
        }catch(Exception e){
            throw new EJBException("Problem updating Administrator DB -> " + e.getMessage());
        }
    }
    
    public Administrator validAdmin(String username, String password) {
        try {
            Administrator administrator = em.find(Administrator.class, username);
            if (administrator == null || !administrator.getPassword().equals(password)) {
                return null;
            }
            
            return administrator;
        } catch (Exception e) {
            throw new EJBException("Problem validating Administrator -> " + e.getMessage());
        }
    }
}
