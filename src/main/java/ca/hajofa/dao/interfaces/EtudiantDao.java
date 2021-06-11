package ca.hajofa.dao.interfaces;

import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Etudiants;
import java.util.List;

/**
 * @author JonathanTremblay, Hassna, Fatima
 */
public interface EtudiantDao {
    public List<Etudiants> findByEquipeDescEtudiants(String nomEquipe,String cours);
    public List<Etudiants> findAll();
    public boolean create(Etudiants etudiant,String cours);
    public boolean createEtudiant(Etudiants etudiant);
    public List<Etudiants> findByCours(String cours);
    public List<Etudiants> findByIDCours(int idCours);
    public Etudiants findByEmail(String email);
    public int find_last_idEquipe_genere();
    public Etudiants findByID(int idEtudiant);
    public boolean modifierMotPasse(int idEtudiant, String nouveauMotPasse);
    public List<Etudiants> findByEquipe(String nomEquipe,String cours);
    public Etudiants getEtudiantCompletById(int idEtudiant);
    public void deleteEtudiant(Etudiants etudiant,Cours cours);
    public boolean create(Etudiants etudiant,String cours,String nomEquipe);
    public List<Etudiants> findByIdCours(String cours,int idEtudiant);
    
    
}
