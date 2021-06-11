/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.EnseignantDao;
import ca.hajofa.entites.Enseignants;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.jpa.EntityManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author jonat
 */
public class JpaEnseignants implements EnseignantDao{

    @Override
    public List<Enseignants> findAll() {
       EntityManager em = EntityManagerSingleton.getEntityManager();
       Query requete = em.createNamedQuery("Enseignants.findAll");
       List<Enseignants> listeProfs = requete.getResultList();
       return listeProfs;
    }

    @Override
    public Enseignants findByEmail(String email) {
       EntityManager em = EntityManagerSingleton.getEntityManager();
       try{
          Query query = em.createNamedQuery("Enseignants.findByEmail");
       query.setParameter("email", email);
       Enseignants prof = (Enseignants)query.getSingleResult(); 
       return prof;
       }catch(NoResultException e){
           return null;
       }
       
       
    }

    @Override
    public boolean createEnseignant(Enseignants prof) {
        boolean created = false;
        EntityManager em = null;
        EntityTransaction transaction = null;
        em = EntityManagerSingleton.getEntityManager();
        Query requete = em.createNamedQuery("Enseignants.findAll");
        List<Enseignants> listeProf = requete.getResultList();
        if(validerDoublon(prof, listeProf)) {
            //message d erreure
            JOptionPane.showMessageDialog(null, prof.getNom() + " "
                    + prof.getPrenom() + " existe déjà",
                    "Erreur d'ajout",
                    JOptionPane.ERROR_MESSAGE);
            created = false;
        } else {
           
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(prof);
            transaction.commit();
            System.out.println(prof.getIDProf()+ "a ete cree!");
            created =  true;
        } catch (Exception e){
            System.out.println("Une erreur est survenue lors de la création"
                    + " d'un enseignant.");
           if( transaction != null){
                transaction.rollback();
            }
        }
    }
        return created;
    }
    
    /**
     *La methode validerDoublon() permet de verifier si un enseignant existe deja
     * @param prof
     * @param liste
     * @return
     */
     public static boolean validerDoublon(Enseignants prof, List<Enseignants> liste) {
        //attribut
        boolean trouve = false;
        //parcourir liste
        for (Enseignants tmp : liste) {
            if (tmp.getEmail().equals(prof.getEmail())) {
                trouve = true;
            }
        }
        return trouve;
    }

     /**
     *La methode update() permet de modifier le mdp d'un enseigannt
     * @param courriel
     * @param motpass
     * @return
     */
    public boolean update(String courriel, String motpass) {
        Enseignants prof = findByEmail(courriel);
        int idProf = prof.getIDProf();
        EntityManager em = null;
        
        EntityTransaction transaction = null;
        boolean motPasseChange = false;
         try {
            em = EntityManagerSingleton.getEntityManager();
            transaction = em.getTransaction();
            
            prof.setPasswd(motpass);
            transaction.begin();
            em.merge(prof);
            transaction.commit();
            motPasseChange = true;
        } catch (NoResultException nor){
            System.out.println("Aucun prof n'a été trouvé.");
        }

        return motPasseChange;
    }
    

    
}
