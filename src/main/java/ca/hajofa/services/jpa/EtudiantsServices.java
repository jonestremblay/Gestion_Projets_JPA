package ca.hajofa.services.jpa;


import ca.hajofa.dao.jpa.JpaEquipes;
import ca.hajofa.dao.jpa.JpaEtudiants;
import ca.hajofa.dao.jpa.JpaEtudiantsCours;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.entites.EtudiantsCours;
import ca.hajofa.etudiant.EtudiantConnecte;
import ca.hajofa.jpa.EntityManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Cette classe de services permet de faciliter l'appel des methodes EtudiantDao
 * @author Fatima, JonathanTremblay, Hassna
 */
public class EtudiantsServices {

    public static List<Etudiants> GetAllEtudiants() {
       return new JpaEtudiants().findAll();
    }
    
    public static Etudiants getEtudiantByEmail (String email) {
        return new JpaEtudiants().findByEmail(email);
    }
    
    public static Etudiants findByID(int idEtudiant){
        return new JpaEtudiants().findByID(idEtudiant);
    }
     
    public static boolean createEtudiant(Etudiants etudiant){
         return new JpaEtudiants().createEtudiant(etudiant);
    }

    public static boolean createEtudiant(Etudiants etudiant,String cours){
        return new JpaEtudiants().create(etudiant, cours);
    }
    
    public static int rejoindreCours(String cleCours, int idEtudiant){
        return new JpaEtudiantsCours().rejoindreCours(cleCours, idEtudiant);
    }
    
    public static List<Etudiants> findByCoursEtudiant(String cours){
        return new JpaEtudiants().findByCours(cours);
    }
    
     public static List<Etudiants> findByIdCours(String cours,int idEtudiant){
        return new JpaEtudiants().findByIDCours(cours,idEtudiant);
    }

    public static Etudiants findByEmailEtudiant(String email){
        return new JpaEtudiants().findByEmail(email);
    }
    
     public static void deleteEtudiant(Etudiants etudiant,Cours cours){
        new JpaEtudiants().deleteEtudiant(etudiant, cours);
    }
    public static Etudiants findByEmail(String email){
        return new JpaEtudiants().findByEmail(email);
    }
    
    public static List<Etudiants> findByIDCours(int idCours){
        return new JpaEtudiants().findByIDCours(idCours);
    }
    
    
    public static Etudiants getEtudiantEmail (String email) {
            return new JpaEtudiants().findByEmail(email);
    }
    
    public static int find_last_idEquipe_genere(){
        return new JpaEtudiants().find_last_idEquipe_genere();
    }
    
    public static List<Etudiants> findByEquipeEtudiants(String nomEquipe,String cours){
        return new JpaEtudiants().findByEquipe(nomEquipe,cours);
    }
    public static List<Etudiants> findByEquipeDescEtudiants(String nomEquipe,String cours){
        return new JpaEtudiants().findByEquipeDescEtudiants(nomEquipe,cours);
    }
    
    public static boolean verifierInscriptionCours(int idCours, int idEtudiant){
        return new JpaEtudiantsCours().verifierInscriptionCours(idCours, idEtudiant);
    }
    
    /**
     * Verifie si l'etudiant donne en parametre fais deja parti d'une equipe 
     * dans le cours donne en parametres.
     * @param idEtudiant
     * @param titreCoursSelectionne
     * @return estDejaEnEquipeDansCours{true, false}
     */
    public static boolean verifierSiEtudiantDejaEnEquipe(int idEtudiant, String titreCoursSelectionne){
        boolean estDejaEnEquipeDansCours = false;
        /* Verifie s'il a deja une equipe dans ce cours */
        EntityManager em = EntityManagerSingleton.getEntityManager();
        Etudiants etudiant = (Etudiants)em.find(Etudiants.class, idEtudiant);
        List<Equipes> listeEquipesEtudiant = null;
        try {
            listeEquipesEtudiant = (List<Equipes>)etudiant.getEquipesCollection();
            for (Equipes eq : listeEquipesEtudiant){
            if (eq.getEtudiantsCollection().contains(etudiant)
                    && eq.getCours().getTitre().equals(titreCoursSelectionne)){
                estDejaEnEquipeDansCours = true;
            }
        }
        } catch (NullPointerException npe){}
        return estDejaEnEquipeDansCours;
    }
    
    public static Etudiants getEtudiantCompletById(int idEtudiant){
        return new JpaEtudiants().findByID(idEtudiant);
    }
    
    public static boolean modifierMotPasse(int idEtudiant, String nouveauMotPasse){
        return new JpaEtudiants().modifierMotPasse(idEtudiant, nouveauMotPasse);
    }
    public List<Etudiants> findEtudiantsByDescription(String cours, int idEtudiant){
        return new JpaEtudiants().findEtudiantsByDescription(cours, idEtudiant);
    }
    
}

