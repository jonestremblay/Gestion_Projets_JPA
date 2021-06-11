package ca.hajofa.services.jpa;

import ca.hajofa.dao.jpa.JpaProjets;
import ca.hajofa.entites.Projets;
import ca.hajofa.etudiant.EtudiantConnecte;
import java.time.LocalDate;
import java.util.List;

/**
 * Cette classe de services permet de faciliter l'appel des methodes ProjetDao
 * @author JonathanTremblay 
 */
public  class ProjetServices {
    public static List<Projets> findAllByIDEtudiant(int idEtudiant){
        return new JpaProjets().findAllProjects(idEtudiant);
    }
    
    public static Projets findByIdCoursProjet(int idCours){
        return new JpaProjets().findByIDCours(idCours);
    }
    
    public static List<Projets> findAllProjectsByIdEquipe(int idEquipe, int idCours){
         return new JpaProjets().findAllProjectsByIdEquipe(idEquipe, idCours);
     }
    
    public static Projets findByIDProjet(int idProjet){
         return new JpaProjets().findByIDProjet(idProjet);
     }
    
    public static Projets findByIDCours(int idCours){
        return new JpaProjets().findByIDCours(idCours);
    }
     
    public static boolean ajouterNouveauProjet(Projets projet){
         return new JpaProjets().create(projet);
     }
     
    public static boolean modifierProjet(Projets projet, LocalDate dateRemise){
         return new JpaProjets().modifierProjet(projet, dateRemise);
     }
     
    public static LocalDate getDateRemiseProjet(int idProjet){
        return new JpaProjets().getDateRemiseProjet(idProjet);
    }
    
     public static boolean creerNouveauProjet(Projets projet, String cours, String nomEquipe){
         return new JpaProjets().create(projet, cours, nomEquipe);
     }
    
    public static boolean createProjet(Projets projet) {
        return new JpaProjets().create(projet);
    }
    public static List<Projets> findByCoursProjets(String cours){
        return new JpaProjets().findByCours(cours);
    }
    public static List<Projets> findByDes(String cours){
        return new JpaProjets().findByDes(cours);
    }
    public static boolean deleteProjet(Projets projet){
        return new JpaProjets().delete(projet);
    }
    
    public static boolean updateProjet(Projets projet){
        return new JpaProjets().update(projet);
    }
    
    public static Projets findByIDEquipe(int idEquipe){
        return new JpaProjets().findByIDEquipe(idEquipe);
    }
}
