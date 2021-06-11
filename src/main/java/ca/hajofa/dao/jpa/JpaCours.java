/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.CoursDao;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Enseignants;
import ca.hajofa.entites.Equipes;
import ca.hajofa.jpa.EntityManagerSingleton;
import ca.hajofa.services.jpa.EnseignantsServices;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jonat
 */
public class JpaCours implements CoursDao{

    @Override
    public List<Cours> findAll() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
       Query requete = em.createNamedQuery("Cours.findAll",Cours.class);
       List<Cours> listeCours = requete.getResultList();
       return listeCours;
    }

    @Override
    public boolean create(Cours cours, String courriel) {
     Enseignants prof = EnseignantsServices.getProfEmail(courriel);
        
        
        
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            cours.setEnseignants(prof);
            em.persist(cours);
            transaction.commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }   
    }

    @Override
    public boolean delete(Cours cours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cours cours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cours> findByEmail(String courrielProf) {
        List<Cours> listeCours = new ArrayList<>();
        
        Enseignants prof = EnseignantsServices.getProfEmail(courrielProf);
        System.out.println(prof);
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query query = em.createNamedQuery("Cours.findByIdProf");
        query.setParameter("idProf", prof.getIDProf());
        
        try {
            listeCours = (List<Cours>)query.getResultList();
        } catch (NoResultException nre){
            System.out.println("Ce prof n'a aucun cours " );
        } 
        for(Cours tmp : listeCours){
            System.out.println(tmp.getAPropos());
        }
        return listeCours;
    }

    @Override
    public int findIdCours(List<Cours> liste, String cours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Cours findByTitre(String titre){
         EntityManager em = EntityManagerSingleton.getEntityManager();
         Cours cours = null;
         Query query;
         try {
             query = em.createNamedQuery("Cours.findByTitre");
             query.setParameter("titre", titre);
             cours = (Cours)query.getSingleResult();
         } catch (NoResultException nor){
             System.out.println("aucun cours trouvé avec ce titre : " + titre);
         }
         return cours;
    }

    @Override
    public int findIdCoursByTitre(String titre) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        int idCours = -1;
        Query requete = em.createNamedQuery("Cours.findByTitre");
        requete.setParameter("titre", titre);
        try {
            Cours cours = (Cours)requete.getSingleResult();
            idCours = cours.getIDCours();
        } catch (NoResultException nor){
            System.out.println("Aucun cours n'a été trouvé avec ce titre de cours.");
        }
        return idCours;
    }

    @Override
    public String findTitreCoursByNomEquipe(String nomEquipe) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        String titreCours = "???";
        Query requete = em.createNamedQuery("Equipes.findByNomEquipe");
        requete.setParameter("nomEquipe", nomEquipe);
        List<Equipes> listeEquipes = null;
        try {
            listeEquipes = (List<Equipes>)requete.getResultList();
            for (Equipes eq : listeEquipes){
                if (eq.getEquipesPK().getNomEquipe().equals(nomEquipe)){
                    titreCours = eq.getCours().getTitre();
                }
            }
        } catch (NoResultException nor){
            System.out.println("Aucune équipes trouvés avec ce nom d'équipe. "
                    + "Impossible de trouver le titre du cours.");
        }
        return titreCours;
    }

    @Override
    public Cours findByCle(String cleCours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cours findByApropos(String description) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
         Cours cours = null;
         Query query;
         try {
             query = em.createNamedQuery("Cours.findByAPropos");
             query.setParameter("aPropos", description);
             cours = (Cours)query.getSingleResult();
         } catch (NoResultException nor){
             System.out.println("aucun cours trouvé avec ce titre : " + description);
         }
         return cours;
    }

    
    
}
