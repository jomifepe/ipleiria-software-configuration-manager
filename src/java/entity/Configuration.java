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
import javax.validation.constraints.NotNull;

@Entity
public class Configuration implements Serializable {
private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id private Long id;
    
    @NotNull private String description;
    
    @ManyToOne
    @JoinColumn(name="SOFTWARE_ID")
    @NotNull private Software software;
    
    @ManyToMany
    @JoinTable(name = "CLIENTS_CONFIGURATIONS",
        joinColumns = @JoinColumn(name = "CONFIGURATION_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "CLIENT_USERNAME", referencedColumnName = "USERNAME"))
    private List<Client> clients;
        
    private List<Module> modules;
    private List<String> hardware;
    private List<String> services;
    private List<String> licences;
    private List<Parameter> parameters;
    private List<String> extensions;
    
    @NotNull private String contractInfo;
    private Status status;

    public Configuration() {
        this.clients = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.services = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
    }

    public Configuration(String description, Software software, String contractInfo, Status status) {
        this.description = description;
        this.software = software;
        this.clients = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.hardware = new ArrayList<>();
        this.services = new ArrayList<>();
        this.licences = new ArrayList<>();
        this.parameters = new ArrayList<>();
        this.extensions = new ArrayList<>();
        this.contractInfo = contractInfo;
        this.status = status;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
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
    
    public void addClient(Client client){
        clients.add(client);
    }
    
    public void removeClient(Client client){
        clients.remove(client);
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

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
