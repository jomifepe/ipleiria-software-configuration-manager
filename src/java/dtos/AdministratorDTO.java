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
public class AdministratorDTO extends UserDTO implements Serializable {
    
    private String role;

    public AdministratorDTO() {
    }
    
    
    public AdministratorDTO(String username, String password, String name, String email,String role) {
        super(username,password,name,email);
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void reset() {
        super.reset();
        setRole(null);
    } 
    
    
    
}
