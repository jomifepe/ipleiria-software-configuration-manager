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
    
    
    
    private List<String> hardware;
    private List<String> licences;
   
    private String contractInfo;
    private Status status;
    private ConfigurationType configurationType;
    private int templateId;
   

    public Configuration() {
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
    
    public Configuration(int id,String description, Software software, ConfigurationType configurationType) {
        this.id = id;
        this.description = description;
        this.software = software;
        this.configurationType=configurationType;
        
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public Configuration(int id, String description, Software software, List<String> hardware, List<String> licences, String contractInfo, Status status, ConfigurationType configurationType, int templateId) {
        this.id = id;
        this.description = description;
        this.software = software;
        this.clients = new ArrayList<>();
        this.hardware = hardware;
        this.licences = licences;
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

    public List<String> getLicences() {
        return licences;
    }

    public void setLicences(List<String> licences) {
        this.licences = licences;
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
    
    public void addHardware(String hardware){
        this.hardware.add(hardware);
    }
    public void addLicense(String license){
        this.licences.add(license);
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
        return "Configuration{" + "id=" + id + ", description=" + description + ", software=" + software + ", clients=" + clients +", hardware=" + hardware + ", licences=" + licences +", contractInfo=" + contractInfo + ", status=" + status + ", configurationType=" + configurationType + ", templateId=" + templateId + '}';
    }
    
    
     
     
}
