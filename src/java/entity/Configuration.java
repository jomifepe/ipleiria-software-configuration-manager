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
    @NotNull 
    private Software software;
    
    @ManyToMany
    @JoinTable(name = "CONFIGURATIONS_CLIENTS",
    joinColumns = @JoinColumn(name = "CONFIGURATION_CODE", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "CLIENT_CODE", referencedColumnName = "username"))
    private List<Client> clients;
    @ManyToMany
    @JoinTable(name = "CONFIGURATIONS_MODULES",
    joinColumns = @JoinColumn(name = "CONFIGURATION_CODE", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "MODULE_CODE", referencedColumnName =
    "id"))
    private List<Module> modules;
    private List<String> hardware;
    private List<String> licences;
    @ManyToMany
    @JoinTable(name = "CONFIGURATIONS_PARAMETERS",
    joinColumns = @JoinColumn(name = "CONFIGURATION_CODE", referencedColumnName = "ID"),
    inverseJoinColumns = @JoinColumn(name = "PARAMENTER_CODE", referencedColumnName =
    "id"))
    private List<Parameter> parameters;
    private List<String> extensions;
    private String contractInfo;
    private Status status;

    public Configuration() {
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.clients=new ArrayList<>();
    }
    
    
    
    public Configuration(int id,String description, Software software) {
        this.id=id;
        this.description = description;
        this.software = software;
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.clients=new ArrayList<>();
    }

   
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

    public List<String> getHardwares() {
        return hardware;
    }

    public void setHardwares(List<String> hardwares) {
        this.hardware = hardwares;
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
}
