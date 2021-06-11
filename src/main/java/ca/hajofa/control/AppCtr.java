package ca.hajofa.control;
import ca.hajofa.dao.jpa.JpaEtudiantsCours;
import ca.hajofa.dao.jpa.JpaNotifications;
import ca.hajofa.entites.Cours;
import ca.hajofa.jpa.EntityManagerSingleton;
import ca.hajofa.entites.Enseignants;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.EquipesPK;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.entites.Notifications;
import ca.hajofa.entites.Projets;
import ca.hajofa.entites.ProjetsPK;
import ca.hajofa.etudiant.EtudiantConnecte;
import ca.hajofa.services.jpa.*;
import ca.hajofa.ui.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFrame;


public class AppCtr {
    public static void main(String[] args) {
        FenConnexion fenCnx = new FenConnexion();
        fenCnx.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenCnx.setVisible(true);

//        EtudiantConnecte.idEtudiant = 1000;
//        FenEtudiant fenEtudiant = new FenEtudiant();
//        fenEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        fenEtudiant.setVisible(true);

//        EnseignantConnecre.courriel = "hafed@bdeb.qc.ca";
//        FenEnseignant fenProf = new FenEnseignant();
//        fenProf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        fenProf.setVisible(true);
        System.out.println(ProjetServices.findAllByIDEtudiant(1000));
    }
}
