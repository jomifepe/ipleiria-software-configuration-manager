/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entity.ConfigurationType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private String status;
    private List<String> hardware;
    private int template_id;
    private ConfigurationType  configurationType;
    private List<String> licences;

    public ConfigurationDTO() {
    }

    public ConfigurationDTO(int id, String description, int softwareCode, String softwareName, List<String> extensions, String contractInfo, String status, List<String> hardware, int template_id, ConfigurationType configurationType, List<String> licences) {
        this.id = id;
        this.description = description;
        this.softwareCode = softwareCode;
        this.softwareName = softwareName;
        this.extensions = extensions;
        this.contractInfo = contractInfo;
        this.status = status;
        this.hardware = hardware;
        this.template_id = template_id;
        this.configurationType = configurationType;
        this.licences = licences;
    }

    public ConfigurationDTO(int id, String description, int softwareCode, String softwareName, List<String> extensions, List<String> hardware, int templateCode, ConfigurationType configurationType) {
       this.id = id;
        this.description = description;
        this.softwareCode = softwareCode;
        this.softwareName = softwareName;
        this.extensions = extensions;
        this.hardware = hardware;
        this.template_id = templateCode;
        this.configurationType = configurationType;
    }

 

    /*public ConfigurationDTO(int id, String description, int software_code, String software_name) {
        this.id = id;
        this.description = description;
        this.softwareCode = software_code;
        this.softwareName = software_name;
        this.extensions=new ArrayList<>();
    }*/
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public List<String> getHardware() {
        return hardware;
    }

    public void setHardware(List<String> hardware) {
        this.hardware = hardware;
    }

    public List<String> getLicences() {
        return licences;
    }

    public void setLicences(List<String> licences) {
        this.licences = licences;
    }
    
    
    
    
    
    
}
