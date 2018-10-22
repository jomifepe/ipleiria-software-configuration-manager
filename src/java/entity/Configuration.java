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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String description;
    @NotNull
    private Software software;
    private List<Module> modules;
    private List<String> hardwares;
    private List<String> services;
    private List<String> licences;
    private List<Parameter> parameters;
    private List<String> extensions;
    @NotNull
    private String contract_info;
    private Status status;

    public Configuration() {
    }

    public Configuration(String description, Software software, String contract_info, Status status) {
        this.description = description;
        this.software = software;
        this.modules=new LinkedList<Module>();
        this.hardwares=new LinkedList<String>();
        this.services=new LinkedList<String>();
        this.licences=new LinkedList<String>();
        this.parameters=new LinkedList<Parameter>();
        this.extensions=new LinkedList<String>();
        this.contract_info = contract_info;
        this.status = status;
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
        return contract_info;
    }

    public void setContract_info(String contract_info) {
        this.contract_info = contract_info;
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
