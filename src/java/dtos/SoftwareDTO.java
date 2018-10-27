/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author ruben
 */
public class SoftwareDTO implements Serializable {
    private int id;
    private String name;
    private String baseVersion;

    public SoftwareDTO() {
    }

    public SoftwareDTO(int id, String name, String baseVersion) {
        this.id = id;
        this.name = name;
        this.baseVersion = baseVersion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(String baseVersion) {
        this.baseVersion = baseVersion;
    }
    
    
    public void reset(){
        this.setId(0);
        this.setName(null);
        this.setBaseVersion(null);
    }
    
}
