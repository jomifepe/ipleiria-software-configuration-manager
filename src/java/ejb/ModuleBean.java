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
    
    public void create(int id, String name){
        try{
            Module module = new Module(id, name);
            em.persist(module);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    
    public List<ModuleDTO> getCurrentTemplateModules(int templateCode){
         try{
           Configuration template=em.find(Configuration.class, templateCode);
           if(template==null){
               return null;
           }
           return modulesToDTOs(template.getModules());
           
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

}
