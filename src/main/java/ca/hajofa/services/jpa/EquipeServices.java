package ca.hajofa.services.jpa;

import ca.hajofa.dao.jpa.*;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.EquipesPK;
import ca.hajofa.entites.Etudiants;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe de services permet de faciliter l'appel des methodes EquipesDao
 * @author JonathanTremblay, Hassna, Fatima
 */
public class EquipeServices {
    
    public static boolean creerEquipe(Equipes equipe){
        return new JpaEquipes().create(equipe);
    }
    
    public static boolean createEquipe(String cours, String nomEquipe) {
        return new JpaEquipes().create(cours, nomEquipe);
    }

    
    public static boolean ajouterEtudiantDansEquipe(
            int idEquipe, int idEtudiant_toAdd){
        return new JpaEquipesEtudiante().ajouterEtudiantDansEquipe(
                idEquipe, idEtudiant_toAdd);
    }
    
    public static int find_ID_Equipe(String nomEquipe, int idCours){
        return new JpaEquipes().find_ID_Equipe_ByNomEquipeAndCours(nomEquipe, idCours);
    }
    
    public static String findNomEquipe(int idCours, int idEtudiant){
        return new JpaEquipes().find_NomEquipe_ByIdCoursEtudiant(idCours, idEtudiant);
    }
    
    public static List<Equipes> findAllEquipesByIdCours(int idCours){
        return new JpaEquipes().findAllByIdCours(idCours);
    }
    
    public static Equipes findEquipeByIdNOm(int idCours, String nomEquipe) {
        return new JpaEquipes().findByIdNom(idCours, nomEquipe);
    }
    public static List<Equipes> findAllByIdCours(int idCours) {
        return new JpaEquipes().findByIdCours(idCours);
    }
    
    public static ArrayList<Etudiants> findTousLesMembresEquipe(int idEquipe){
        return new JpaEquipes().findTousLesMembresEquipe(idEquipe);
    }
    
     public static List<Equipes> findByIdCoursEquipe(String cours) {
        return new JpaEquipes().findByIdCoursEquipes(cours);
    }
    
    public static List<Equipes> findAllByIdEtudiant(int idEtudiant){
        return new JpaEquipes().findAllByIdEtudiant(idEtudiant);
    }
    
     public static List<Equipes> findByIdCours(String cours) {
    return new JpaEquipes().findListeEquipe(cours);
    }
     
    public static boolean create(Equipes equipe, int idEtudiant){
    return new JpaEquipes().create(equipe,idEtudiant);
    }
    public static List<Equipes> findByDescCoursEquipes(String description){
    return new JpaEquipes().findByDescCoursEquipes(description);
    }
    public static boolean createEquipeByDesc(String cours, String nomEquipe){
    return new JpaEquipes().createEquipeByDesc(cours,nomEquipe);
    }
}
