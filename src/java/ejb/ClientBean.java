package ejb;

import dtos.ClientDTO;
import entity.Client;
import entity.Configuration;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClientBean {

    @PersistenceContext
    EntityManager em;
    
    public void create(String username, String password, String name, String email, String address, String contact) {
        try {
            Client client = new Client(username, password, name, email, address, contact);      
            
            em.persist(client);
        } catch(Exception e){
            throw new EJBException("Problem creating Client (DB persist) -> " + e.getMessage());
        }
    }
    
    public void update(String username, String password, String name, String email, String address, String contact) {
        try{
            Client client = (Client) em.find(Client.class, username);
            if (client == null) {
                return;
            }
            
            client.setUsername(username);
            client.setPassword(password);
            client.setName(name);
            client.setEmail(email);
            client.setAddress(address);
            client.setContact(contact);
            
            em.merge(client);     
        }catch(Exception e){
            throw new EJBException("Problem updating Client DB -> " + e.getMessage());
        }
    }
    
    public void remove(String username) {
        try {
            Client client = (Client) em.find(Client.class, username);
            if (client == null) {
                return;
            }
            
            em.remove(client);
        }catch(Exception e){
            throw new EJBException("Problem removing Client from DB -> " + e.getMessage());
        }
    }
    
    
    public List<ClientDTO> getAll() {
        try {
            List<Client> clients = em.createNamedQuery("getAllClients").getResultList(); 
            return clientsToDTOs(clients);
        } catch(Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void addConfiguration(String username, int configurationId){
        try{
            Client client = (Client) em.find(Client.class, username);
            Configuration configuration = (Configuration) em.find(Configuration.class, configurationId);
            if(client == null || configuration == null){
                return;
            }
            configuration.addClient(client);
            client.addConfiguration(configuration);
            
            em.merge(client);    
            em.merge(configuration);
        }catch(Exception e){
            throw new EJBException("Problem adding Client Configuration -> " + e.getMessage());
        }
    }
    
    
    
    public ClientDTO isValid(String username, String password) {
        try {
            Client client = em.find(Client.class, username);
            if (client == null || !client.getPassword().equals(password)) {
                return null;
            }
            return clientToDTO(client);
        } catch (Exception e) {
            throw new EJBException("Problem validating Client -> " + e.getMessage());
        }
    }
    
    
    ClientDTO clientToDTO(Client client) {
        return new ClientDTO(client.getUsername(),
                             client.getPassword(), 
                             client.getName(),
                             client.getEmail(),
                             client.getAddress(),
                             client.getContact());
    }
    
    List<ClientDTO> clientsToDTOs(List<Client> clients) {
        List<ClientDTO> dtos = new ArrayList<>();
        clients.forEach((s) -> {
            dtos.add(clientToDTO(s));
        });
        return dtos;
    }
    
}



