/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.AdministratorDTO;
import dtos.ClientDTO;
import dtos.ConfigurationDTO;
import dtos.SoftwareDTO;
import dtos.UserDTO;
import ejb.AdministratorBean;
import ejb.ClientBean;
import ejb.ConfigurationBean;
import ejb.SoftwareBean;
import entity.Administrator;
import entity.Client;
import entity.Configuration;
import entity.Software;
import entity.User;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.inject.Named;


@Named(value = "softwareManager")
@SessionScoped
public class SoftwareManager implements Serializable {
    private static final long serialVersionUID = 1094801825228386363L;
    private static final Logger logger = Logger.getLogger("web.SoftwareManager");
 
    private UserDTO loggedUser;
    private ClientDTO currentClient;
    private AdministratorDTO currentAdministrator;
    private ConfigurationDTO currentConfiguration;
    private SoftwareDTO currentSoftware, newSoftware;
    
    @EJB private ClientBean clientBean;
    @EJB private AdministratorBean administratorBean;
    @EJB private ConfigurationBean configurationBean;
    @EJB private SoftwareBean softwareBean;

    public SoftwareManager() {
        this.loggedUser = new UserDTO();
        this.currentClient = new ClientDTO();
        this.currentAdministrator = new AdministratorDTO();
        this.currentConfiguration = new ConfigurationDTO();
        this.newSoftware = new SoftwareDTO();
    }
    
    public String createClient() {
        try {
            clientBean.create(
                    currentClient.getUsername(), 
                    currentClient.getPassword(), 
                    currentClient.getName(), 
                    currentClient.getEmail(), 
                    currentClient.getAddress(), 
                    currentClient.getContact());
            currentClient.reset();
        } catch (Exception e) {
            logger.warning("Problem creating client in method createClient.");
            return "admin_client_create?faces-redirect=true";
        }
        
        return "admin_user_manager?faces-redirect=true";
    }
    
    public String createAdministrator() {
        try {
            administratorBean.create(
                    currentAdministrator.getUsername(), 
                    currentAdministrator.getPassword(), 
                    currentAdministrator.getName(), 
                    currentAdministrator.getEmail(), 
                    currentAdministrator.getRole());
            currentAdministrator.reset();
        } catch (Exception e) {
            logger.warning("Problem creating administrator in method createAdministrator.");
            return "admin_administrator_create?faces-redirect=true";
        }
        
        return "admin_user_manager?faces-redirect=true";
    }
    
    public String createSoftware() {
        try{
            softwareBean.create(
                    newSoftware.getId(), 
                    newSoftware.getName(), 
                    newSoftware.getBaseVersion());            
            newSoftware.reset();
        } catch (Exception e) {
            logger.warning("Problem creating software in method createSoftware.");
            return "admin_software_create?face-redirect=true";
        }
         return "admin_software_manager?faces-redirect=true";
    }
    
    public String updateClient() {
        try {
            clientBean.update(
                    currentClient.getUsername(), 
                    currentClient.getPassword(),
                    currentClient.getName(),
                    currentClient.getEmail(),
                    currentClient.getAddress(),
                    currentClient.getContact());
            currentClient.reset();
        } catch (Exception e) {
            logger.warning("Problem updating client in method updateClient");
            return "admin_client_update";
        }
        
        return "admin_user_manager?faces-redirect=true";
    }
    
    public String updateAdministrator() {
        try {
            administratorBean.update(
                    currentAdministrator.getUsername(), 
                    currentAdministrator.getPassword(),
                    currentAdministrator.getName(),
                    currentAdministrator.getEmail(),
                    currentAdministrator.getRole());
            currentAdministrator.reset();
        } catch (Exception e) {
            logger.warning("Problem updating administrator in method updateAdministrator");
            return "admin_administrator_update";
        }
        
        return "admin_user_manager?faces-redirect=true";
    }
    
    public String updateSoftware() {
        try{
            softwareBean.update(
                    currentSoftware.getId(),
                    currentSoftware.getName(),
                    currentSoftware.getBaseVersion());
        } catch(Exception e) {
            logger.warning("Problem updating software in method updateSoftware");
            return "admin_software_update";
        }
        return "admin_software_manager?face-redirect=true";
    }
    
    public String deleteClient(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteClientUsername");
            String username = param.getValue().toString();
            
            clientBean.remove(username);
        } catch (Exception e) {
            logger.warning("Problem deleting client in method deleteClient");
        }
        
        return "admin_user_manager?faces-redirect=true";
    }
    
    public String deleteAdministrator(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteAdministratorUsername");
            String username = param.getValue().toString();
            
            administratorBean.remove(username);
        } catch (Exception e) {
            logger.warning("Problem deleting administrator in method deleteAdministrator");
        }
        
        return "admin_user_manager?faces-redirect=true";
    }
    

    public SoftwareDTO getNewSoftware() {
        return newSoftware;
    }

    public void setNewSoftware(SoftwareDTO newSoftware) {
        this.newSoftware = newSoftware;
    }
    
    public SoftwareDTO getCurrentSoftware() {
        return currentSoftware;
    }

    public void setCurrentSoftware(SoftwareDTO currentSoftware) {
        this.currentSoftware = currentSoftware;
    }

    public UserDTO getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserDTO loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ClientDTO getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(ClientDTO currentClient) {
        this.currentClient = currentClient;
    }

    public AdministratorDTO getCurrentAdministrator() {
        return currentAdministrator;
    }

    public void setCurrentAdministrator(AdministratorDTO currentAdministrator) {
        this.currentAdministrator = currentAdministrator;
    }
    
    public boolean isLoggedUserAdmin() {
        return loggedUser instanceof AdministratorDTO;
    }
   
    public String validateUsernamePassword() {
        String typedUsername = loggedUser.getUsername();
        String typedPassword = loggedUser.getPassword();
        
        loggedUser = clientBean.isValid(typedUsername, typedPassword);
        
        if (loggedUser == null) { //É client?
            loggedUser = administratorBean.validAdmin(typedUsername, typedPassword);
            if (loggedUser == null) { //É admin?
                loggedUser.reset();
                return "login?faces-redirect=true";
            }
        }
        
        return "user_overview?faces-redirect=true";
    }
    
    public String logOutUser() {
        loggedUser.reset();
        return "login?faces-redirect=true";
    }
    
    public List<ClientDTO> getAllClients() {
        try {
            return clientBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all clients in method getAllClients");
            return null;
        }
    }
     public List<SoftwareDTO> getAllSoftwares() {
        try {
            return softwareBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all administrators in method getAllAdministrators");
            return null;
        }
    }
    
    
    public List<AdministratorDTO> getAllAdministrators() {
        try {
            return administratorBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all administrators in method getAllAdministrators");
            return null;
        }
    }
    
    public List<ConfigurationDTO> getAllConfigurations(){
        try {
            return configurationBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all administrators in method getAllAdministrators");
            return null;
        }
    }

    public ConfigurationDTO getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void setCurrentConfiguration(ConfigurationDTO currentConfiguration) {
        this.currentConfiguration = currentConfiguration;
    }
    
    public List<ConfigurationDTO> getCurrentSoftwareConfigurations(){
        try {
            return configurationBean.getConfigurationsForSoftware(currentSoftware.getId());
        } catch (Exception e) {
            logger.warning("Problem fetching all configurations in method getCurrentSoftwareConfigurations");
            return null;
        }
    }
}