/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AdministratorBean;
import ejb.ClientBean;
import ejb.ConfigurationBean;
import entity.Administrator;
import entity.Client;
import entity.Configuration;
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
    
    private User currentUser;

    
    private Configuration currentConfiguration;
    
    @EJB private ClientBean clientBean;
    @EJB private AdministratorBean administratorBean;
    @EJB private ConfigurationBean configurationBean;

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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
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
        return currentUser instanceof Administrator;
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
    
    public List<Client> getAllClients() {
        try {
            return clientBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all clients in method getAllClients");
            return null;
        }
    }
    
    public List<Administrator> getAllAdministrators() {
        try {
            return administratorBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all administrators in method getAllAdministrators");
            return null;
        }
    }
    
    public List<Configuration> getAllConfigurations(){
        try {
            return configurationBean.getAll();
        } catch (Exception e) {
            logger.warning("Problem fetching all administrators in method getAllAdministrators");
            return null;
        }
    }

    public Configuration getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void setCurrentConfiguration(Configuration currentConfiguration) {
        this.currentConfiguration = currentConfiguration;
    } 
    
}