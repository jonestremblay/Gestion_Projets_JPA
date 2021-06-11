/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.ProjetDao;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Equipes;
//import static ca.hajofa.entites.EquipesPK_.nomEquipe;

import ca.hajofa.entites.Etudiants;
import ca.hajofa.entites.Projets;
import ca.hajofa.entites.ProjetsPK;
import ca.hajofa.jpa.EntityManagerSingleton;
import ca.hajofa.services.jpa.CoursServices;
import ca.hajofa.services.jpa.EquipeServices;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author jonat
 */
public class JpaProjets implements ProjetDao{

    @Override
    public List<Projets> findAllProjects(int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<Projets> listeProjet = new ArrayList<Projets>();
        Etudiants etudiant = null;
        try {
           etudiant = em.find(Etudiants.class, idEtudiant);
           for (Equipes eq : etudiant.getEquipesCollection()){
               for (Etudiants etu : eq.getEtudiantsCollection()){
                   if (etu.getIDEtudiant().equals(idEtudiant)){
                       /* Puisque letudiant peut apparaitre plusieurs fois
                          dans cette liste, doit verifier qu'il ne soit pas
                          deja ajoute dans la liste.*/
                        if (!listeProjet.contains(eq.getProjet()) && eq.getProjet() != null){
                            listeProjet.add(eq.getProjet());
                          
                        }
                   }
               }
           }
           return listeProjet;
        } catch(NoResultException nor){
            System.out.println("Aucun projet trouvé avec ce id projet !");
        }
        em.close();

        if (!listeProjet.isEmpty()){
            return listeProjet;
        } else {
            return new ArrayList<Projets>();}
    }
    
    @Override
    public Projets findByIDProjet(int idProjet) {
         EntityManager em = EntityManagerSingleton.getEntityManager();
         Query query = em.createNamedQuery("Projets.findByIDProjet");
         query.setParameter("iDProjet", idProjet);
         Projets projet = null;
         try {
             projet = (Projets)query.getSingleResult();
         } catch(NoResultException nor){
             System.out.println("Aucun projet trouvé avec ce id projet !");
         }
         return projet;
    }
    
    @Override
    public boolean create(Projets projet) {
           EntityManager em = EntityManagerSingleton.getEntityManager();
       EntityTransaction transaction = em.getTransaction();
       try {
           transaction.begin();
           em.persist(projet);
           transaction.commit();
           return true;
       } catch(Exception e){
            if (e.getMessage().toLowerCase()
                    .contains("a foreign key constraint fails")){
                System.out.println("Ce cours ou cette équipe n'existe pas.");
            }  else {System.out.println(e.getMessage());}
           return false;
       }
    
    }

    @Override
    public boolean delete(Projets projet) {
       EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
              transaction.begin();
              if(!em.contains(projet)){
                  projet = em.merge(projet);
              }
              em.remove(projet);
              transaction.commit();
              em.close();
              return true;
        } catch(Exception e){
            if (e.getMessage().toLowerCase()
                    .contains("a foreign key constraint fails")){
                System.out.println("Ce projet n'existe .");
            }  else {System.out.println(e.getMessage());}
           
       }
        return false;
    }

    @Override
    public boolean update(Projets projet) {
EntityManager em = EntityManagerSingleton.getEntityManager();
       EntityTransaction transaction = em.getTransaction();
       try {
           
           transaction.begin();
           em.merge(projet);
           transaction.commit();
           return true;
       } catch(Exception e){
            System.out.println("Une erreur est survenue lors de la création"
                    + " du projet.");
           if( transaction != null){
                transaction.rollback();
            }
        } 
           return false;
    }

    @Override
    public List<Projets> findAllProjectsByIdEquipe(int idEquipe, int idCours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Projets findByIDCours(int idCours) {
       EntityManager em = EntityManagerSingleton.getEntityManager();
       Query requete = em.createNamedQuery("Projets.findByIDCours");
       requete.setParameter("iDCours", idCours);
       Projets projet = null;
       try {
           projet = (Projets)requete.getSingleResult();
       } catch (NoResultException nor){
           System.out.println("Aucun projet n'a été trouvé avec ce id de cours : " + idCours);
       }
       return projet;
    }

    @Override
    public List<Projets> findByCours(String cours) {
       List<Projets> listeProjet = null;
      Cours coursE = CoursServices.findByTitre(cours);
      int idCours= coursE.getIDCours();
      EntityManager em = EntityManagerSingleton.getEntityManager();
      Query requete = em.createNamedQuery("Projets.findByIDCours");
      requete.setParameter("iDCours", idCours);
      
      try{
          listeProjet = requete.getResultList();
      }catch (NoResultException nor){
           System.out.println("Aucun projet n'a été trouvé avec ce id de cours : " + idCours);
       }
       return listeProjet;
    }

    @Override
    public boolean modifierProjet(Projets projet, LocalDate dateRemise) {
       EntityManager em = EntityManagerSingleton.getEntityManager();
       EntityTransaction transaction = em.getTransaction();
       Equipes equipe1 = null;
       //Equipes equipe = projet.getEquipe();
       Query query = em.createNamedQuery("Equipes.findByIDEquipe");
       query.setParameter("iDEquipe", projet.getEquipe().getEquipesPK().getIDEquipe());
       try {
           /* doit recuperer l'Equipe avec le id pour pas recuprer objet equipe
           qui contient deja le projet modifier */
           equipe1 = (Equipes)query.getSingleResult();
           //equipe.setProjet(projet);
           transaction.begin();
           em.merge(equipe1);
           em.flush();
           transaction.commit();
           return true;
       } catch(Exception e){
            if (e.getMessage().toLowerCase()
                    .contains("a foreign key constraint fails")){
                System.out.println("Ce cours ou cette équipe n'existe pas.");
            }  else {
                System.out.println(e.getMessage());}
           return false;
       }
    }

    @Override
    public LocalDate getDateRemiseProjet(int idProjet) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Projets projet = null;
        LocalDate dateRemiseLocale = null;
        try {
            projet = em.find(Projets.class, idProjet);
            dateRemiseLocale = projet.getDateRemise().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch(NoResultException nor){
            System.out.println("Aucun projet n'a été trouvé avec ce idProjet : " + idProjet);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return dateRemiseLocale;
    }

    @Override
    public Projets findByIDEquipe(int idEquipe) {
       EntityManager em = EntityManagerSingleton.getEntityManager();
       Query requete = em.createNamedQuery("Projets.findByIDEquipe");
       requete.setParameter("iDEquipe", idEquipe);
       Projets projet = null;
       try {
           projet = (Projets)requete.getSingleResult();
       } catch (NoResultException nor){
           System.out.println("Aucun projet n'a été trouvé avec ce id d'équipe : " + idEquipe);
       }
       return projet;
    }
  
    public boolean create(Projets projet,String cours,String nomEquipe) {
        boolean cree = false; 
        Cours coursE = CoursServices.findByTitre(cours);
        
        int idCours= coursE.getIDCours();
        int idEquipe  = EquipeServices.find_ID_Equipe(nomEquipe,idCours);
        projet.setProjetsPK(new ProjetsPK(idCours,idEquipe));

        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
       try {
           transaction.begin();
           em.persist(projet);
           transaction.commit();
           em.flush();
           em.clear();
           cree= true;
       } catch(Exception e){
            if (e.getMessage().toLowerCase()
                    .contains("a foreign key constraint fails")){
                 JOptionPane.showMessageDialog(null, "Cette equipe a déjà un projet",
                    "Projet",
                    JOptionPane.ERROR_MESSAGE);
            }  else {System.out.println(e.getMessage());}
           
       }
    return false;
    }
   @Override
    public List<Projets> findByDes(String cours) {
       List<Projets> listeProjet = null;
      Cours coursE = CoursServices.findByApropos(cours);
      int idCours= coursE.getIDCours();
      EntityManager em = EntityManagerSingleton.getEntityManager();
      Query requete = em.createNamedQuery("Projets.findByIDCours");
      requete.setParameter("iDCours", idCours);
      
      try{
          listeProjet = requete.getResultList();
      }catch (NoResultException nor){
           System.out.println("Aucun projet n'a été trouvé avec ce id de cours : " + idCours);
       }
       return listeProjet;
    }
    
}
