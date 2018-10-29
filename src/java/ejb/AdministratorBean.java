package ejb;

import dtos.AdministratorDTO;
import entity.Administrator;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AdministratorBean {

    @PersistenceContext
    EntityManager em;
    
    public void create(String username, String password, String name, String email, String role) {
        try{
           Administrator administrator = new Administrator(username, password, name, email, role);      
           em.persist(administrator);
        }catch(Exception e){
            throw new EJBException("Problem creating Administrator (DB persist) -> " + e.getMessage());
        }
    }
    
    public void update(String username, String password, String name, String email, String role) {
        try{
            Administrator administrator = (Administrator) em.find(Administrator.class, username);
            if (administrator == null) {
                return;
            }
            
            administrator.setUsername(username);
            administrator.setPassword(password);
            administrator.setName(name);
            administrator.setEmail(email);
            administrator.setRole(role);
            
            em.merge(administrator);     
        } catch(Exception e) {
            throw new EJBException("Problem updating Administrator DB -> " + e.getMessage());
        }
    }
    
    public void remove(String username) {
        try {
            Administrator administrator = em.find(Administrator.class, username);
            if(administrator == null){
                return;
            }
            em.remove(administrator);
        } catch(Exception e) {
            throw new EJBException("Problem removing Administrator from DB -> " + e.getMessage());
        }
    }
    
    
    public List<AdministratorDTO> getAll(){
        try{
            List<Administrator> administrators = em.createNamedQuery("getAllAdministrators").getResultList(); 
            return administratorsToDTOs(administrators);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public AdministratorDTO validAdmin(String username, String password) {
        try {
            Administrator administrator = em.find(Administrator.class, username);
            if (administrator == null || !administrator.getPassword().equals(password)) {
                return null;
            }
            
            return administratorToDTO(administrator);
        } catch (Exception e) {
            throw new EJBException("Problem validating Administrator -> " + e.getMessage());
        }
    }
    
    
     AdministratorDTO administratorToDTO(Administrator administrator){
        return new AdministratorDTO(administrator.getUsername(),
                                    administrator.getPassword(), 
                                    administrator.getName(), 
                                    administrator.getEmail(),
                                    administrator.getRole());
    }
    
    List<AdministratorDTO> administratorsToDTOs(List<Administrator> administrators){
        List<AdministratorDTO> dtos=new ArrayList<>();
        for(Administrator s: administrators){
            dtos.add(administratorToDTO(s));
        }
        return dtos;
    }
}
