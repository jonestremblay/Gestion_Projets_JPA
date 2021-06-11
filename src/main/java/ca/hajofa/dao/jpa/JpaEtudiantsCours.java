/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.EtudiantCoursDao;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.jpa.EntityManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jonat
 */
public class JpaEtudiantsCours implements EtudiantCoursDao{
    
    private static String titreCoursTrouve = "";
    private static int IDCoursTrouve = 0;
    
    @Override
    public boolean create(int idCours, int idEtudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteCoursEtudiant(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int rejoindreCours(String cleCours, int idEtudiant) {
        /* 0 = cours existe, etudiant deja inscrit 
           1 = cours existe, etudiant est ajouté
          -1 = cour n'existe pas                    */
        int resultat = -2;
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            Query query = em.createNamedQuery("Cours.findByCleCours"); 
            query.setParameter("cleCours", cleCours);
            Cours coursTrouve = (Cours) query.getSingleResult();
            if (coursTrouve != null){
                  titreCoursTrouve = coursTrouve.getTitre();
                  IDCoursTrouve = coursTrouve.getIDCours();
                  if (!verifierInscriptionCours(IDCoursTrouve, idEtudiant)){
                      if (ajouterCours(IDCoursTrouve, idEtudiant)){
                          System.out.println("Etudiant " + idEtudiant + " ajouté !");
                          resultat = 1;
                      }
                  } else {
                      resultat = 0;
                      System.out.println("Etudiant " + idEtudiant + " fais déjà parti du cours. ");
                  }
            } 
        } catch(NoResultException nor){
            resultat = -1;
            System.out.println("Nous n'avons pas trouvé de cours avec cette clé.");
        } finally {
            if (em != null){
                em.close();
            }
        }
        return resultat;
    }
    
    @Override
    public boolean verifierInscriptionCours(int idCours, int idEtudiant) {
        boolean dejaInscrit = false;
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            Etudiants etudiant = em.find(Etudiants.class, idEtudiant);
            Cours cours = em.find(Cours.class, idCours);
            if (cours.getEtudiantsCollection().contains(etudiant)){
                dejaInscrit = true;
            }
        } catch (NoResultException nor){
            System.out.println(nor.getMessage());
        }
        
        return dejaInscrit;
    }

    @Override
    public boolean ajouterCours(int idCours, int idEtudiant) {
        boolean ajoute = false;
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = EntityManagerSingleton.getEntityManager();
            transaction = em.getTransaction();
            Etudiants etudiant = em.find(Etudiants.class, idEtudiant);
            Cours cours = em.find(Cours.class, idCours);
            cours.inscrireEtudiant(etudiant);
            transaction.begin();
            em.merge(cours);
            transaction.commit();
            ajoute = true;
        } catch (NoResultException e){
            System.out.println(e.getMessage());
            if( transaction != null){
                transaction.rollback();
            }
        }
        return ajoute;
    }

    @Override
    public List<Cours> findAllCoursByEtudiant(int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<Cours> listeCours = null;
        Etudiants etudiant = null;
        try {
            etudiant = em.find(Etudiants.class, idEtudiant);
            listeCours = (List<Cours>)etudiant.getCoursCollection();
        } catch(NoResultException nor){
            System.out.println("Aucun étudiant n'a été trouvé.");
        }
        return listeCours;
    }
    
    public static String getTitreCoursTrouve(){
        return titreCoursTrouve;
    }
    
    public static int getIDCoursTrouve(){
        return IDCoursTrouve;
    }
     @Override
    public boolean ajouterEtudiantDansCours(int idCours, int idEtudiant) {
        boolean insere = false;
        EntityTransaction transaction = null;
        EntityManager em = EntityManagerSingleton.getEntityManager();
        transaction = em.getTransaction();
        try {
            Etudiants etudiant = em.find(Etudiants.class, idEtudiant);
            Cours cours = em.find(Cours.class, idCours);
            transaction.begin();
            cours.inscrireEtudiant(etudiant);

            transaction.commit();
            insere = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
           
        }
 return insere;
    }
    
}
