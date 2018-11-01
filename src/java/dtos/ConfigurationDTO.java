/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.ConfigurationType;
import entity.Software;
import entity.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ruben
 */
public class ConfigurationDTO implements Serializable {
    private int id;
    private String description;
    private int softwareCode;
    private String softwareName;
    private List<String> extensions;
    private String contractInfo;
    private Status status;
    private int template_id;
    private ConfigurationType  configurationType;

    public ConfigurationDTO() {
    }

    public ConfigurationDTO(int id, String description, int software_code, String software_name) {
        this.id = id;
        this.description = description;
        this.softwareCode = software_code;
        this.softwareName = software_name;
        this.extensions=new ArrayList<>();
    }
    
    public void reset(){
        this.id=0;
        this.description=null;
        this.softwareCode=0;
        this.softwareName=null;
        this.extensions=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSoftwareCode() {
        return softwareCode;
    }

    public void setSoftwareCode(int software_code) {
        this.softwareCode = software_code;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String software_name) {
        this.softwareName = software_name;
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

    public String getContractInfo() {
        return contractInfo;
    }

    public void setContractInfo(String contractInfo) {
        this.contractInfo = contractInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public ConfigurationType getConfigurationType() {
        return configurationType;
    }

    public void setConfigurationType(ConfigurationType configurationType) {
        this.configurationType = configurationType;
    }
    
    
    
}
