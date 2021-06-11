package ca.hajofa.dao.interfaces;


import ca.hajofa.entites.Projets;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Hassna, JonathanTremblay, Fatima
 */
public interface ProjetDao {
    public List<Projets> findAllProjects(int idEtudiant);
    public List<Projets> findByDes(String cours);
    public Projets findByIDProjet(int idProjet);
    public boolean create(Projets projet);
    public boolean delete(Projets projet);
    public boolean update(Projets projet);
    public List<Projets> findAllProjectsByIdEquipe(int idEquipe, int idCours);
    public Projets findByIDCours(int idCours);
    public List<Projets> findByCours(String cours);
    public boolean modifierProjet(Projets projet, LocalDate dateRemise);
    public LocalDate getDateRemiseProjet(int idProjet);
    public Projets findByIDEquipe(int idEquipe);
}
