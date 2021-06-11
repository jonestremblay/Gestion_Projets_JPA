package ca.hajofa.dao.interfaces;

import ca.hajofa.entites.EquipesEtudiant;
import ca.hajofa.entites.*;
import java.util.List;

/**
 *
 * @author JonathanTremblay, Hassna, Fatima
 */
public interface EquipeEtudianteDao {
    public List<EquipesEtudiant> findByIdCours(int idCours,int idEtudiant);
    public boolean create(int idEquipe, int idEtudiant);
    public boolean ajouterEtudiantDansEquipe(int idEquipe, int idEtudiant_toAdd);
}
