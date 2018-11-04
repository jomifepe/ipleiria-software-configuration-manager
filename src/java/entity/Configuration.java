/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CONFIGURATION")
@NamedQueries({
    @NamedQuery(name = "getAllConfigurations", query = "SELECT c from Configuration c")
})
public class Configuration implements Serializable {
private static final long serialVersionUID = 1L;   
    
    @Id private int id;
    
    @NotNull private String description;
    
    @ManyToOne
    @JoinColumn(name="SOFTWARE_ID")
    @NotNull private Software software;
    
    @ManyToMany
    @JoinTable(name = "CONFIGURATION_CLIENT",
            joinColumns = @JoinColumn(name = "CONFIGURATION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_USERNAME", referencedColumnName = "USERNAME"))
    private List<Client> clients;
    
    @ManyToMany
    @JoinTable(name = "CONFIGURATION_MODULE",
            joinColumns = @JoinColumn(name = "CONFIGURATION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID", referencedColumnName = "ID"))
    private List<Module> modules;
    
    private List<String> hardware;
    private List<String> licences;
    
    @ManyToMany
    @JoinTable(name = "CONFIGURATION_PARAMETER",
            joinColumns = @JoinColumn(name = "CONFIGURATION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PARAMENTER_ID", referencedColumnName = "ID"))
    private List<Parameter> parameters;
    private List<String> extensions;
    private String contractInfo;
    private Status status;
    private ConfigurationType configurationType;
    private int templateId;
   

    public Configuration() {
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
    
    public Configuration(int id,String description, Software software, ConfigurationType configurationType) {
        this.id = id;
        this.description = description;
        this.software = software;
        this.configurationType=configurationType;
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public Configuration(int id, String description, Software software, List<Module> modules, List<String> hardware, List<String> licences, List<Parameter> parameters, List<String> extensions, String contractInfo, Status status, ConfigurationType configurationType, int templateId) {
        this.id = id;
        this.description = description;
        this.software = software;
        this.clients = new ArrayList<>();
        this.modules = modules;
        this.hardware = hardware;
        this.licences = licences;
        this.parameters = parameters;
        this.extensions = extensions;
        this.contractInfo = contractInfo;
        this.status = status;
        this.configurationType = configurationType;
        this.templateId = templateId;
    }
    
    
    
    /*public Configuration(int id,String description, Software software, ConfigurationType configurationType, int templateId, String contractInfo, Status status) {
        this.id = id;
        this.description = description;
        this.software = software;
        this.configurationType=configurationType;
        this.templateId=templateId;
        this.contractInfo=contractInfo;
        this.status=status;
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.clients = new ArrayList<>();
    }*/
    
    
   
    public List<String> getHardware() {
        return hardware;
    }

    public void setHardware(List<String> hardware) {
        this.hardware = hardware;
    }

    public String getContractInfo() {
        return contractInfo;
    }

    public void setContractInfo(String contractInfo) {
        this.contractInfo = contractInfo;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<String> getLicences() {
        return licences;
    }

    public void setLicences(List<String> licences) {
        this.licences = licences;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

    public String getContract_info() {
        return contractInfo;
    }

    public void setContract_info(String contract_info) {
        this.contractInfo = contract_info;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addModule(Module module){
        this.modules.add(module);
    }
    
    public void addHardware(String hardware){
        this.hardware.add(hardware);
    }
    public void addLicense(String license){
        this.licences.add(license);
    }
    
    public void addParameter(Parameter parameter){
        this.parameters.add(parameter);
    }
    
     public void addExtension(String extension){
        this.extensions.add(extension);
    }
     
     public void addClient(Client client){
         this.clients.add(client);
     }
     
     public void removeClient(Client client){
         this.clients.remove(client);
     }

    public ConfigurationType getConfigurationType() {
        return configurationType;
    }

    public void setConfigurationType(ConfigurationType configurationType) {
        this.configurationType = configurationType;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "Configuration{" + "id=" + id + ", description=" + description + ", software=" + software + ", clients=" + clients + ", modules=" + modules + ", hardware=" + hardware + ", licences=" + licences + ", parameters=" + parameters + ", extensions=" + extensions + ", contractInfo=" + contractInfo + ", status=" + status + ", configurationType=" + configurationType + ", templateId=" + templateId + '}';
    }
    
    
     
     
}
