/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.EquipeEtudianteDao;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.EquipesEtudiant;
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
public class JpaEquipesEtudiante implements EquipeEtudianteDao{

    @Override
    public List<EquipesEtudiant> findByIdCours(int idCours, int idEtudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(int idEquipe, int idEtudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ajouterEtudiantDansEquipe(int idEquipe, int idEtudiant_toAdd) {
        /* methode incomplete : doit gerer exception et commit a la DB */
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        boolean ajoute = false;
        try {
            Query requeteEquipe = em.createNamedQuery("Equipes.findByIDEquipe");
            Query requeteEtudiant = em.createNamedQuery("Etudiants.findByIDEtudiant");
            requeteEquipe.setParameter("iDEquipe", idEquipe);
            requeteEtudiant.setParameter("iDEtudiant", idEtudiant_toAdd);
            Etudiants etudiant = (Etudiants)requeteEtudiant.getSingleResult();
            Equipes eq = (Equipes)requeteEquipe.getSingleResult();
            eq.ajouterMembreEquipe(etudiant);
            etudiant.ajouterEquipe(eq);
            transaction.begin();
            em.merge(eq);
            em.merge(etudiant);
            transaction.commit();
            ajoute = true;
            
        } catch(NoResultException nor){
            System.out.println("Aucun étudiant / équipe n'a été trouvé avec "
                    + "ce idEquipe et ce idEtudiant : " + idEquipe + " | " + idEtudiant_toAdd);
        } catch(Exception e){
            if (e.getMessage().contains("Duplicate entry")){
                System.out.println("Cet étudiant fais déjà parti de cette équipe !");
            }
        } finally {
            em.close();
        }
        return ajoute;
    }
    
}
