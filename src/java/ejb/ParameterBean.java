/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Module;
import entity.Parameter;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ruben
 */
@Stateless
public class ParameterBean {

   @PersistenceContext
    EntityManager em;
    
    public void create(int id, String name, String value){
        try{
            Parameter parameter = new Parameter(id, name, value);
            em.persist(parameter);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
}
