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
import javax.inject.Named;


@Named(value = "softwareManager")
@SessionScoped
public class SoftwareManager implements Serializable {
    private static final long serialVersionUID = 1094801825228386363L;
    private static final Logger logger = Logger.getLogger("web.SoftwareManager");
 
    private String msg;
    private String userUsername;
    private String userPassword;
    
    private UserDTO currentUser;
    private AdministratorDTO currentAdmin;
    private ClientDTO currentClient;
    
    private ConfigurationDTO currentConfiguration;
    
    private SoftwareDTO currentSoftware, newSoftware;
    
    @EJB private ClientBean clientBean;
    @EJB private AdministratorBean administratorBean;
    @EJB private ConfigurationBean configurationBean;
    @EJB private SoftwareBean softwareBean;

    public SoftwareManager() {
        this.newSoftware =new SoftwareDTO();
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
    
    public String getUserPassword() {
            return userPassword;
    }

    public void setUserPassword(String pwd) {
            this.userPassword = pwd;
    }

    public String getMsg() {
            return msg;
    }

    public void setMsg(String msg) {
            this.msg = msg;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDTO currentUser) {
        this.currentUser = currentUser;
    }
    
    public String getUserUsername() {
            return userUsername;
    }

    public void setUserUsername(String user) {
            this.userUsername = user;
    }
    
    public String getUserFirstName() {
        return currentUser.getName().split(" ")[0];
    }
    
    public boolean isCurrentUserAdmin() {
        return currentUser instanceof AdministratorDTO;
    }

    public String validateUsernamePassword() {
        currentUser = null;
   
        currentUser = clientBean.isValid(userUsername, userPassword);
      
        if(currentUser == null){ //É client?
            currentUser = administratorBean.validAdmin(userUsername, userPassword);
            if(currentUser == null){//É admin?
                return "login";
            }
        }
        
        return "user_overview";
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
    
    
    public String createSoftware(){
        try{
            int newId=newSoftware.getId();
            softwareBean.create(newId,newSoftware.getName(), 
                    newSoftware.getBaseVersion());            
            newSoftware.reset();
           
        } catch (Exception e) {
            logger.warning("Problem creating software in method createSoftware.");
            return "admin_products_create?face-redirect=true";
        }
         return "admin_user_manager?faces-redirect=true";
    }
   
    
    
    
    public String updateSoftware(){
        try{
            softwareBean.update(currentSoftware.getId(),
                    currentSoftware.getName(),
                    currentSoftware.getBaseVersion());
        }catch(Exception e){
            logger.warning("Problem updating software in method updateSoftware");
            return "admin_products_update";
        }
        return "user_overview?face-redirect=true";
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