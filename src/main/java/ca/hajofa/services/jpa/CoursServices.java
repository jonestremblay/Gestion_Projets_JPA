package ca.hajofa.services.jpa;

import ca.hajofa.entites.Cours;
import ca.hajofa.dao.jpa.*;
import java.util.List;

/**
 * Cette classe de services permet de faciliter l'appel des methodes CoursDao
 * @author Hassna , JonathanTremblay, Fatima
 */
public class CoursServices {

    public static List<Cours> getAllCours() {
        return new JpaCours().findAll();
    }

    public static boolean createCours(Cours cours, String courriel) {
        return new JpaCours().create(cours, courriel);
    }
    
     public static boolean deleteCours(Cours cours){
        return new JpaCours().delete(cours);
    }
    
    public static List<Cours> findByEmailCours(String courrielProf){
        return new JpaCours().findByEmail(courrielProf);
    }
    public static int findIdCours(List<Cours>  liste,String cours){
        return new JpaCours().findIdCours(liste,cours);
    }
    public static Cours findByTitre(String titreCours){
        return new JpaCours().findByTitre(titreCours);
    }
    public static Cours findByCleCours(String cleCours){
        return new JpaCours().findByCle(cleCours);
    }
    
    public static int findIdCoursByTitre(String titre){
        return new JpaCours().findIdCoursByTitre(titre);
    }
    
    public static String findTitreCoursByNomEquipe(String nomEquipe){
        return new JpaCours().findTitreCoursByNomEquipe(nomEquipe);
    }

     public static boolean ajouterEtudiantDansCours(int idCours, int idEtudiant){
        return new JpaEtudiantsCours().ajouterEtudiantDansCours(idCours,idEtudiant);
    }
     public static Cours findByApropos(String description){
        return new JpaCours().findByApropos(description);
    }
}
