/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejb.AdministratorBean;
import ejb.ClientBean;
import entity.Client;
import entity.User;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "SoftwareManager")
@SessionScoped
public class SoftwareManager implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;
    
    private User currentUser;

    @EJB
    private ClientBean clientBean;

    @EJB
    private AdministratorBean administratorBean;

    public String getPwd() {
            return pwd;
    }

    public void setPwd(String pwd) {
            this.pwd = pwd;
    }

    public String getMsg() {
            return msg;
    }

    public void setMsg(String msg) {
            this.msg = msg;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    

    public String getUser() {
            return user;
    }

    public void setUser(String user) {
            this.user = user;
    }

    public String validateUsernamePassword() {
        currentUser = null;
        //USER BEAN??
        
        
        currentUser = clientBean.validClient(user, pwd);
      
        if(currentUser == null){ //É client?
            currentUser = administratorBean.validAdmin(user, pwd);
            if(currentUser == null){//É admin?
                return "login";
            }else{
                return "admin_overview";
            }
        }else{
            return "client_overview";
        }		
    }
}