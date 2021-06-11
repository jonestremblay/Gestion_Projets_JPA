package ca.hajofa.dao.interfaces;

import ca.hajofa.entites.EtudiantsCours;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.EquipesPK;
import ca.hajofa.entites.Etudiants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonathanTremblay, Hassna, Fatima
 */
public interface EquipeDao {
    public boolean create(Equipes equipe);
    public boolean create(String cours, String nomEquipe);
    public List<Equipes> findAllByIdEtudiant(int idEtudiant);
    public EtudiantsCours findMembreEnEquipeByCours(int idEtudiant, int idCours);
    public boolean findMembreEnEquipeByTitreCours(int idEtudiant, String titreCours);
    public int find_ID_Equipe_ByNomEquipeAndCours(String nomEquipe, int idCours);
    public String find_NomEquipe_ByIdCoursEtudiant(int idCours, int idEtudiant);
    public ArrayList<Etudiants> findTousLesMembresEquipe(int idEquipe);
    public List<Equipes> findAllByIdCours(int idCours);
    public Equipes findByIdNom(int idCours, String nomEquipe);
    public List<Equipes> findByIdCours(int idCours);
    public List<Equipes> findByIdCoursEquipes(String cours);
    public List<Equipes> findListeEquipe(String cours);
    public boolean create(Equipes equipe, int idEtudiant);
    public List<Equipes> findByDescCoursEquipes(String description);
    public boolean createEquipeByDesc(String cours, String nomEquipe);
    public List<Equipes> findListeEquipeByDesc(String cours);
}
