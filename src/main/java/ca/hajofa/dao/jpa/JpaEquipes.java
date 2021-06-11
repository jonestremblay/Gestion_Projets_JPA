/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.EquipeDao;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.EquipesPK;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.entites.EtudiantsCours;
import ca.hajofa.jpa.EntityManagerSingleton;
import ca.hajofa.services.jpa.CoursServices;
import ca.hajofa.services.jpa.EtudiantsServices;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author jonat
 */
public class JpaEquipes implements EquipeDao{

    @Override
    public boolean create(Equipes equipe) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(equipe);
            transaction.commit();
            return true;
        } catch (Exception e){
            if (e.getMessage().contains("Duplicate entry")){
                System.out.println("Impossible de créer l'équipe "
                        + "puisque ce nom d'équipe est déjà pris pour ce cours.");
            } else if (e.getMessage().contains("a foreign key constraint fails")){
                System.out.println("Impossible de créer l'équipe "
                        + "puisque ce cours n'existe pas");
            }else {System.out.println(e.getMessage());}
        }
            return false;
    }

    @Override
    public boolean create(String cours, String nomEquipe) {
         Cours coursTrouve = CoursServices.findByTitre(cours);
        Equipes equipe = new Equipes(coursTrouve.getIDCours(),nomEquipe);
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
           
           
            em.persist(equipe);
            transaction.commit();
            return true;
       
            } catch (Exception e){
            if (e.getMessage().contains("Duplicate entry")){
                System.out.println("Impossible de créer l'équipe "
                        + "Ce nom d'équipe existe déjà.");
            }else{
            System.out.println(e.getMessage());
            transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public List<Equipes> findAllByIdEtudiant(int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Equipes.findAll");
        List<Equipes> listeTrouvee = null;
        List<Equipes> listeEquipesDeEtudiant = new ArrayList<Equipes>();

        try {
            listeTrouvee = (List<Equipes>)query.getResultList();
            for (Equipes eq : listeTrouvee){
                for (Etudiants e : eq.getEtudiantsCollection()){
                    if (e.getIDEtudiant().equals(idEtudiant)){
                        listeEquipesDeEtudiant.add(eq);
                    }
                }
            }
            return listeEquipesDeEtudiant;
        } catch (NoResultException nor){
            System.out.println("Aucune équipe trouvée avec ce idEtudiant : " + idEtudiant);
        } 
        em.close();
        return listeEquipesDeEtudiant;
    }

    @Override
    public EtudiantsCours findMembreEnEquipeByCours(int idEtudiant, int idCours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean findMembreEnEquipeByTitreCours(int idEtudiant, String titreCours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int find_ID_Equipe_ByNomEquipeAndCours(String nomEquipe, int idCours) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Equipes.findByNomEquipeAndCours");
        query.setParameter("nomEquipe", nomEquipe);
        query.setParameter("idCours", idCours);
        Equipes eq = null;
        try {
            eq = (Equipes)query.getSingleResult();
        } catch (NoResultException nor){
            System.out.println("Aucune équipe trouvée avec ce idCours et ce nom d'équipe : "
                    + idCours + " | " + nomEquipe);
        } 
        if (eq != null){
            return eq.getEquipesPK().getIDEquipe();
        } else {return -1;}
    }

    @Override
    public String find_NomEquipe_ByIdCoursEtudiant(int idCours, int idEtudiant) {
        List<Equipes> listeEq = findByIdCours(idCours);
        String nomEquipe = "[NOT FOUND]";
        /* L'etudiant peut faire partie de seulement une equipe dans un cours*/
        if (listeEq != null){
            for (Equipes eq : listeEq){
                for (Etudiants et : eq.getEtudiantsCollection()){
                    if (et.getIDEtudiant().equals(idEtudiant)){
                        /* Equipe que l'etudiant fais partie trouvé*/
                        nomEquipe = eq.getEquipesPK().getNomEquipe();
                    }
                }
            }
        }
        return nomEquipe;
    }

    @Override
    public ArrayList<Etudiants> findTousLesMembresEquipe(int idEquipe) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Equipes.findByIDEquipe");
        query.setParameter("iDEquipe", idEquipe);
        List<Etudiants> listeEtudiants = new ArrayList<Etudiants>();
        Etudiants etudiant = null;
        Equipes equipe = null;

        try {
            equipe = (Equipes)query.getSingleResult();
            for (Etudiants e : equipe.getEtudiantsCollection()){
                listeEtudiants.add(e);
            }
        } catch (NoResultException nor){
            System.out.println("Aucune équipe trouvée avec ce idEquipe : " + idEquipe);
        } 
        return (ArrayList<Etudiants>)listeEtudiants;
    }

    @Override
    public List<Equipes> findAllByIdCours(int idCours) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Equipes.findByIDEtudiant");
        query.setParameter("idCours", idCours);
        List<Equipes> listeEq = null;
        try {
            listeEq = (List<Equipes>)query.getResultList();
        } catch (NoResultException nor){
            System.out.println("Aucune équipe trouvée avec ce idCours : " + idCours);
        } 
        return listeEq;
    }

    @Override
    public Equipes findByIdNom(int idCours, String nomEquipe) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Equipes.findByNomEquipeAndCours");
        query.setParameter("nomEquipe", nomEquipe);
        query.setParameter("idCours", idCours);
        Equipes equipe = (Equipes)query.getSingleResult();
        return equipe ; 
    }

    @Override
    public List<Equipes> findByIdCours(int idCours) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Equipes.findByIDCours");
        query.setParameter("idCours", idCours);
        List<Equipes> listeEq = null;
        try {
            listeEq = (List<Equipes>)query.getResultList();
        } catch (NoResultException nor){
            System.out.println("Aucune équipe trouvée avec ce idCours : " + idCours);
        } 
        return listeEq;
    }
    
    @Override
    public List<Equipes> findByIdCoursEquipes(String cours) {
        Cours coursTrouve= CoursServices.findByTitre(cours);
        
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query requete = em.createNamedQuery("Equipes.findByIDCours");
        requete.setParameter("idCours", coursTrouve.getIDCours());
        List<Equipes> listeEquipe = requete.getResultList();
        return listeEquipe;
    }
    @Override
    public List<Equipes> findListeEquipe(String cours) {
        Cours coursTrouve= CoursServices.findByTitre(cours);
        System.out.println(coursTrouve);
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query requete = em.createNamedQuery("Equipes.findByIDCours");
        requete.setParameter("idCours", coursTrouve.getIDCours());
        List<Equipes> listeEquipe = requete.getResultList();
        return listeEquipe;    }

    @Override
    public boolean create(Equipes equipe, int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Etudiants etud = EtudiantsServices.findByID(idEtudiant);
        List<Etudiants> liste = new ArrayList<>();
        liste.add(etud);
        equipe.setEtudiantsCollection(liste);
        try {
            transaction.begin();
            
            em.persist(equipe);
            transaction.commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Equipes> findByDescCoursEquipes(String description) {
        Cours coursTrouve= CoursServices.findByApropos(description);
        
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query requete = em.createNamedQuery("Equipes.findByIDCours");
        requete.setParameter("idCours", coursTrouve.getIDCours());
        List<Equipes> listeEquipe = requete.getResultList();
        return listeEquipe;
    }
   @Override
    public boolean createEquipeByDesc(String cours, String nomEquipe) {
         Cours coursTrouve = CoursServices.findByApropos(cours);
        Equipes equipe = new Equipes(coursTrouve.getIDCours(),nomEquipe);
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
           
           
            em.persist(equipe);
            transaction.commit();
            return true;
       
            } catch (Exception e){
            if (e.getMessage().contains("Duplicate entry")){
                System.out.println("Impossible de créer l'équipe "
                        + "Ce nom d'équipe existe déjà.");
            }else{
            System.out.println(e.getMessage());
            transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public List<Equipes> findListeEquipeByDesc(String cours) {
        Cours coursTrouve= CoursServices.findByApropos(cours);
        System.out.println(coursTrouve);
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query requete = em.createNamedQuery("Equipes.findByIDCours");
        requete.setParameter("idCours", coursTrouve.getIDCours());
        List<Equipes> listeEquipe = requete.getResultList();
        return listeEquipe; 
    }
  
    
}
