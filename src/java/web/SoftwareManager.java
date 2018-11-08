/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.AdministratorDTO;
import dtos.ClientDTO;
import dtos.ConfigurationDTO;
import dtos.ModuleDTO;
import dtos.ParameterDTO;
import dtos.SoftwareDTO;
import dtos.UserDTO;
import dtos.ModuleDTO;
import ejb.AdministratorBean;
import ejb.ClientBean;
import ejb.ConfigurationBean;
import ejb.ModuleBean;
import ejb.ParameterBean;
import ejb.SoftwareBean;
import entity.ConfigurationType;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
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
 
    private String msg;
    private String userUsername;
    private String userPassword;
    
    private UserDTO loggedUser;

    private ClientDTO currentClient;
    private AdministratorDTO currentAdministrator;
    private ConfigurationDTO currentConfiguration,newTemplate,currentTemplate;
    private SoftwareDTO currentSoftware, newSoftware;
    
    @EJB private ClientBean clientBean;
    @EJB private AdministratorBean administratorBean;
    @EJB private ConfigurationBean configurationBean;
    @EJB private SoftwareBean softwareBean;
    @EJB private ModuleBean moduleBean;    
    @EJB private ParameterBean parameterBean;


    
    public SoftwareManager() {
        this.currentAdministrator = new AdministratorDTO();
        this.currentClient = new ClientDTO();
        this.loggedUser = new UserDTO();
        this.currentConfiguration = new ConfigurationDTO();
        this.newSoftware = new SoftwareDTO();
        this.newTemplate=new ConfigurationDTO();
    }
  
    //////////////////////////////////////// CRUD
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
    
    public String createTemplate() {
        try{
            configurationBean.createTemplate(
                    newTemplate.getId(), 
                    newTemplate.getDescription(),
                    currentSoftware.getId(),
                    ConfigurationType.TEMPLATE);            
            newTemplate.reset();
        } catch (Exception e) {
            logger.warning("Problem creating teample of software in method createTemplate.");
            return "admin_template_create?face-redirect=true";
        }
         return "admin_software_manager?faces-redirect=true";
    }
    
    public String updateTemplate() {
        try{
            configurationBean.updateTemplate(
                    currentTemplate.getId(),
                    currentTemplate.getDescription());
        } catch(Exception e) {
            logger.warning("Problem updating template in method updateTemplate");
            return "admin_template_update";
        }
        return "admin_software_manager?face-redirect=true";
    }
    
    public String deleteTemplate(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteTemplateId");
            String id=param.getValue().toString();
            
            configurationBean.deleteTemplate(Integer.parseInt(id));
        } catch (Exception e) {
            logger.warning("Problem deleting template in method deleteTemplate");
        }
        
        return "admin_software_manager?faces-redirect=true";
    }
    
     ////////////////////////////////////////
    
    
    
    public List<ConfigurationDTO> currentClientConfigurations(){
        return configurationBean.getClientConfigurations(currentClient.getUsername());
    }
    
    public SoftwareDTO currentClientConfigurationSoftware(){
        return configurationBean.getClientConfigurationSoftware(currentConfiguration.getId());
    }
    

    public String validateUsernamePassword() {
        String typedUsername = loggedUser.getUsername();
        String typedPassword = loggedUser.getPassword();
        
        loggedUser = clientBean.isValid(typedUsername, typedPassword);
        if(loggedUser == null){ //É client?
            loggedUser = administratorBean.validAdmin(typedUsername, typedPassword);
            if(loggedUser == null){//É admin?
                return "login?faces-redirect=true";
            }
        }
        return "user_overview?faces-redirect=true";
    }
    
    
    public List<ModuleDTO> getCurrentClientConfigurationModules(int configId){
        return configurationBean.getModules(configId);
    }
    
   
   public List<ParameterDTO> getCurrentTemplateParameters(){
         return parameterBean.getConfigurationParameters(currentTemplate.getId());
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
    
    public boolean hasCurrentClientGotConfiguration(){
        return clientBean.hasClientGotConfigurations(currentClient.getUsername());
    }

    public void setCurrentConfiguration(ConfigurationDTO currentConfiguration) {
        this.currentConfiguration = currentConfiguration;
    }
   
    
    public List<ConfigurationDTO> getCurrentSoftwareTemplates(){
        try {
            return configurationBean.getCurrentSoftwareTempates(currentSoftware.getId());
        } catch (Exception e) {
            logger.warning("Problem fetching all configurations in method getCurrentSoftwareConfigurations");
            return null;
        }
    }
    
    
        public SoftwareDTO getNewSoftware() {
        return newSoftware;
    }

    public void setNewSoftware(SoftwareDTO newSoftware) {
        this.newSoftware = newSoftware;
    }

    public ConfigurationDTO getCurrentTemplate() {
        return currentTemplate;
    }

    public void setCurrentTemplate(ConfigurationDTO currentTemplate) {
        this.currentTemplate = currentTemplate;
    }
    
    public String getUserPassword() {
            return userPassword;
    }

    public AdministratorDTO getCurrentAdmin() {
        return currentAdministrator;
    }

    public void setCurrentAdmin(AdministratorDTO curreAdministratorDTO) {
        this.currentAdministrator = curreAdministratorDTO;
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
  
    public String getUserUsername() {
            return userUsername;
    }

    public void setUserUsername(String user) {
            this.userUsername = user;
    }
    
    public String getUserFirstName() {
        return loggedUser.getName().split(" ")[0];
    }
    
    public boolean isLoggedUserAdmin() {
        return loggedUser instanceof AdministratorDTO;
    }
    
    public String getLoggedUserRole(){
        if(loggedUser instanceof AdministratorDTO){
            return ((AdministratorDTO)loggedUser).getRole();
        }
        return null;
    }

    public ConfigurationDTO getNewTemplate() {
        return newTemplate;
    }

    public void setNewTemplate(ConfigurationDTO newTemplate) {
        this.newTemplate = newTemplate;
    }
    
    
    
    
}
