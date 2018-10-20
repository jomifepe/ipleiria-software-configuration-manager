/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author luisflores
 */
@Entity
public class Configuration implements Serializable {

    @Id
    private Long id;
    
    @NotNull
    private String description;
    
    @ManyToOne
    @JoinColumn(name="SOFTWARE_CODE")
    @NotNull
    private Software software;
    
    @ManyToMany
    @JoinTable(name = "CLIENTS_CONFIGURATIONS",
        joinColumns = @JoinColumn(name = "CONFIGURATION_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "CLIENT_USERNAME", referencedColumnName = "USERNAME"))
    private List<Client> clients;
    
    
    private List<Module> modules;
    private List<String> hardwares;
    private List<String> services;
    private List<License> licences;
    private List<Parameter> parameters;
    private List<String> extensions;
    
    

    
    @NotNull
    private String contract_data;
    
    @NotNull
    private Status status;

    public Configuration() {
    }

    public Configuration(Long id, String description, Software software, String contract_data, Status status) {
        this.id = id;
        this.description = description;
        this.software = software;
        this.contract_data = contract_data;
        this.status = status;
    }

    
    public void addClient(Client client){
        clients.add(client);
    }
    
    public void removeClient(Client client){
        clients.remove(client);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return hardwares;
    }

    public void setHardwares(List<String> hardwares) {
        this.hardwares = hardwares;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<License> getLicences() {
        return licences;
    }

    public void setLicences(List<License> licences) {
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

    public String getContract_data() {
        return contract_data;
    }

    public void setContract_data(String contract_data) {
        this.contract_data = contract_data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
 
}
