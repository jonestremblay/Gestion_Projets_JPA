/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.jpa;

import ca.hajofa.control.AppParameters;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jonat
 */
public class EntityManagerSingleton {
    private static EntityManager entityManager;
    private static String persistenceUnit = AppParameters.persistenceUnit;
    
    public static EntityManager getEntityManager(){
        if(entityManager == null || !entityManager.isOpen()){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    public static void setPersistenceUnit(String persistenceUnit) {
        EntityManagerSingleton.persistenceUnit = persistenceUnit;
    }
    
    
    
}  
