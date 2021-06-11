/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.EtudiantDao;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Enseignants;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.jpa.EntityManagerSingleton;
import ca.hajofa.services.jpa.CoursServices;
import ca.hajofa.services.jpa.EquipeServices;
import ca.hajofa.services.jpa.EtudiantsServices;
import java.util.ArrayList;
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
public class JpaEtudiants implements EtudiantDao {

    @Override
    public List<Etudiants> findAll() {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Query requete = em.createNamedQuery("Etudiants.findAll");
        List<Etudiants> listeEtudiants = requete.getResultList();
        return listeEtudiants;
    }

    @Override
    public boolean create(Etudiants etudiant, String cours) {
       EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
       Cours coursTrouve = CoursServices.findByTitre(cours);
        List<Etudiants> liste = new ArrayList<>();
        liste.add(etudiant);
        coursTrouve.setEtudiantsCollection(liste);
        try {
            transaction.begin();
            
            em.persist(coursTrouve);
            transaction.commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
        }
            return false;

    }

    @Override
    public boolean createEtudiant(Etudiants etudiant) {
        boolean created = false;
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(etudiant);
            transaction.commit();
            created = true;
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().contains("duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Ce email est déjà utilisé.");
            } else {
                System.out.println("Une erreur est survenue lors de la création"
                        + " d'un enseignant.");
            }
        }
        return created;
    }

    @Override
    public List<Etudiants> findByCours(String cours) {
        Cours coursTrouve = CoursServices.findByApropos(cours);
        List<Etudiants> listeEtudiants = (List<Etudiants>) coursTrouve.getEtudiantsCollection();
        return listeEtudiants;
    }

    @Override
    public List<Etudiants> findByIDCours(int idCours) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Cours cours = null;
        try {
            cours = em.find(Cours.class, idCours);
        } catch (NoResultException nor) {
            System.out.println("Aucun cours n'a été trouvé.");
        }
        return (List<Etudiants>) cours.getEtudiantsCollection();
    }

    @Override
    public Etudiants findByEmail(String email) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        try {
            Query query = em.createNamedQuery("Etudiants.findByEmail");
            query.setParameter("email", email);
            Etudiants etudiant = (Etudiants) query.getSingleResult();
            return etudiant;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int find_last_idEquipe_genere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Etudiants findByID(int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Etudiants etudiant = null;
        try {
            etudiant = em.find(Etudiants.class, idEtudiant);
        } catch (NoResultException nor) {
            System.out.println("Aucun étudiant n'a été trouvé.");
        }
        return etudiant;
    }

    @Override
    public boolean modifierMotPasse(int idEtudiant, String nouveauMotPasse) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Etudiants etudiant = null;
        boolean motPasseChange = false;
        try {
            etudiant = em.find(Etudiants.class, idEtudiant);
            etudiant.setPasswd(nouveauMotPasse);
            transaction.begin();
            em.merge(etudiant);
            transaction.commit();
            motPasseChange = true;
        } catch (NoResultException nor) {
            System.out.println("Aucun étudiant n'a été trouvé.");
        }
        return motPasseChange;
    }

    @Override
    public List<Etudiants> findByEquipe(String nomEquipe, String cours) {

        List<Etudiants> listeEtudiants = new ArrayList<>();
        //Recupere la liste des equipes d'un cours
        List<Equipes> listeEquipres = EquipeServices.findByIdCours(cours);
        if (listeEquipres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune equipe dans ce cours" + " Les equipes");
        } else {
            for (Equipes equipe : listeEquipres) {
                if (equipe.getEquipesPK().getNomEquipe().equals(nomEquipe)) {

                    List<Etudiants> listeEtudiant = (List<Etudiants>) equipe.getEtudiantsCollection();
                    for (Etudiants e : listeEtudiant) {
                        listeEtudiants.add(e);
                    }
                }
            }
//        }
        }
        return (List<Etudiants>) listeEtudiants;
    }

    public boolean deleteEtudiant(Etudiants etudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void verifierInsertion(Etudiants etud, String cours, List<Cours> listeCours) {

    }

    public void deleteEtudiant(Etudiants etudiant, Cours cours) {

        cours.retirerEtudiant(etudiant);
    }

    public List<Etudiants> findByIDCours(String cours, int idEtudiant) {
        List<Etudiants> listeEtudiants = new ArrayList<>();
        //Recupere la liste des equipes d'un cours
        List<Equipes> listeEquipres = EquipeServices.findByIdCours(cours);
        if (listeEquipres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune equipe dans ce cours" + " Les equipes");
        } else {
            for (Equipes equipe : listeEquipres) {

                List<Etudiants> listeEtudiant = (List<Etudiants>) equipe.getEtudiantsCollection();
                for (Etudiants e : listeEtudiant) {
                    if (e.getIDEtudiant() == idEtudiant) {
                        listeEtudiants.add(e);
                    }
                }

            }
//        }
        }
        return  listeEtudiants;
    }

    @Override
    public boolean create(Etudiants etudiant, String cours, String nomEquipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etudiants getEtudiantCompletById(int idEtudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Etudiants> findByIdCours(String cours, int idEtudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public List<Etudiants> findEtudiantsByDescription(String cours, int idEtudiant) {
        List<Etudiants> listeEtudiants = new ArrayList<>();
        //Recupere la liste des equipes d'un cours
        List<Equipes> listeEquipres = EquipeServices.findByIdCours(cours);
        if (listeEquipres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune equipe dans ce cours" + " Les equipes");
        } else {
            for (Equipes equipe : listeEquipres) {

                List<Etudiants> listeEtudiant = (List<Etudiants>) equipe.getEtudiantsCollection();
                for (Etudiants e : listeEtudiant) {
                    if (e.getIDEtudiant() == idEtudiant) {
                        listeEtudiants.add(e);
                    }
                }

            }
//        }
        }
        return  listeEtudiants;
    }

    @Override
    public List<Etudiants> findByEquipeDescEtudiants(String nomEquipe, String cours) {
        List<Etudiants> listeEtudiants = new ArrayList<>();
        //Recupere la liste des equipes d'un cours
        List<Equipes> listeEquipres = EquipeServices.findByDescCoursEquipes(cours);
        if (listeEquipres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune equipe dans ce cours" + " Les equipes");
        } else {
            for (Equipes equipe : listeEquipres) {
                if (equipe.getEquipesPK().getNomEquipe().equals(nomEquipe)) {

                    List<Etudiants> listeEtudiant = (List<Etudiants>) equipe.getEtudiantsCollection();
                    for (Etudiants e : listeEtudiant) {
                        listeEtudiants.add(e);
                    }
                }
            }
//        }
        }
        return (List<Etudiants>) listeEtudiants;
    }

    
}
