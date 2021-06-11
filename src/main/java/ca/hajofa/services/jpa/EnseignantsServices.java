package ca.hajofa.services.jpa;

import ca.hajofa.dao.jpa.JpaEnseignants;
import ca.hajofa.entites.Enseignants;
import java.util.List;

/**
 * Cette classe de services permet de faciliter l'appel des methodes EnseignantDao
 * @author Fatima, Hassna
 */
public class EnseignantsServices {
    
    public static List<Enseignants> GetAllProfs() {
       return new JpaEnseignants().findAll();
    }
    
    public static Enseignants getProfEmail (String email) {
        return new JpaEnseignants().findByEmail(email);
    }
    
    public static boolean createEnseignant (Enseignants prof){
        return new JpaEnseignants().createEnseignant(prof);
    }

//    public static List<Enseignant>findByIdProf(int id_prof){
//        return new JpaEnseignant().findById(id_prof);
//    }
    
    public static boolean updateProf(String courriel,String motpass){
        return new JpaEnseignants().update(courriel,motpass);
    }
}
