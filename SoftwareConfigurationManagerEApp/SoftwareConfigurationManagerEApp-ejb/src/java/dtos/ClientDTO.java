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
public class ClientDTO extends UserDTO implements Serializable {
    private String address;
    private String contact;

    public ClientDTO() {
    }

    public ClientDTO(String username, String password, String name, String email, String address, String contact) {
        super(username, password, name, email);
        this.address = address;
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    @Override
    public void reset(){
        super.reset();
        setAddress(null);
        setContact(null);
    }
    
}
