/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dtos.AdministratorDTO;
import dtos.ModuleDTO;
import entity.Administrator;
import entity.Configuration;
import entity.Module;
import entity.Parameter;
import entity.Software;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ruben
 */
@Stateless
public class ModuleBean {

    @PersistenceContext
    EntityManager em;
    
    public void create(int id, String name, int softwareCode){
        try{
            Software software=em.find(Software.class, softwareCode);
            if(software==null){
                return ;
            }
            Module module = new Module(id, name, software);
            
            software.addModule(module);
            em.persist(software);
            
            em.persist(module);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
            
    public static ModuleDTO moduleToDTO(Module module){
        return new ModuleDTO(module.getId(),module.getName());
    }
    
    public static List<ModuleDTO> modulesToDTOs(List<Module> modules){
        List<ModuleDTO> dtos=new ArrayList<>();
        for(Module s: modules){
            dtos.add(moduleToDTO(s));
        }
        return dtos;
    }
    
    public void addParameter(int id,int id_param){
        try{
            Module module = em.find(Module.class, id);
            if(module==null){
                return;
            } 
            Parameter parameter=em.find(Parameter.class, id_param);
            if(parameter==null){
                return;
            } 
            module.addParameter(parameter);
            em.persist(module);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    
    public void addExtension(int id,String extension){
        try{
            Module module = em.find(Module.class, id);
            if(module==null){
                return;
            } 
            module.addExtension(extension);
            em.persist(module);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    public List<ModuleDTO> getSoftwareModules(int softwareCod) {
         try{
            Software software = em.find(Software.class, softwareCod);
            if(software==null){
                return null;
            } 
            return modulesToDTOs(software.getModules());
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

}
