package ca.hajofa.services.jpa;

import ca.hajofa.entites.EquipesEtudiant;
import ca.hajofa.dao.jpa.JpaEquipesEtudiante;
import java.util.List;

/**
 *Cette classe permet de recuperer toutes les methodes de la classe JdbcEquipeEtudiantDao
 * @author Hasna
 */
public class EquipeEtudiantServices {
    
    public static List<EquipesEtudiant> findByIdCours(int idCours,int idEtudiant) {
        return new JpaEquipesEtudiante().findByIdCours(idCours,idEtudiant);
    }
    public static boolean create(int idEquipe,int idEtudiant){
        return new JpaEquipesEtudiante().create(idEquipe, idEtudiant);
    }
}
