package ejb;

import entity.Client;
import entity.User;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientBean {

    @PersistenceContext
    EntityManager em;
    
    public void create(String username, String password, String name, String email, String address, String contact){
        try{
           Client client = new Client(username, password, name, email, address, contact);      
            em.persist(client); //Inserir na BD
        }catch(Exception e){
            throw new EJBException("Problem creating Client (DB persist) -> " + e.getMessage());
        }
    }
    
    public void remove(String username){
        try{
            Client client = em.find(Client.class, username);
            if(client == null){
                return;
            }
            em.remove(client);
        }catch(Exception e){
            throw new EJBException("Problem removing Client from DB -> " + e.getMessage());
        }
    }
    
    
    public List<Client> getAll(){
        try{
            List<Client> clients = em.createNamedQuery("getAllClients").getResultList(); 
            return clients;
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    
    public void update(String username, String password, String name, String email){
        try{
            Client client = (Client) em.find(Client.class, username);
            if(client == null){
                return;
            }   
            client.setUsername(username);
            client.setPassword(password);
            client.setName(name);
            client.setEmail(email);
            em.merge(client);     
        }catch(Exception e){
            throw new EJBException("Problem updating Client DB -> " + e.getMessage());
        }
    }
    
    public Client isValid(String username, String password) {
        try {
            Client client = em.find(Client.class, username);
            if (client == null || !client.getPassword().equals(password)) {
                return null;
            }
            
            return client;
        } catch (Exception e) {
            throw new EJBException("Problem validating Client -> " + e.getMessage());
        }
    }
    
    
}



