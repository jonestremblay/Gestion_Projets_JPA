package ca.hajofa.services.jpa;

import ca.hajofa.entites.Cours;
import ca.hajofa.dao.jpa.*;
import ca.hajofa.entites.Notifications;
import java.util.List;

/**
 * Cette classe de services permet de faciliter l'appel des methodes CoursDao
 * @author Hassna , JonathanTremblay, Fatima
 */
public class NotificationsServices{

    public static boolean creerNotification(String notif, int idEtudiant){
        return new JpaNotifications().creerNotification(notif, idEtudiant);
    }
    
    public static List<Notifications> lireNotifications(int idEtudiant){
          return new JpaNotifications().lireNotifications(idEtudiant);
    }
     
    public static int supprimerLesNotifications(int idEtudiant) {
          return new JpaNotifications().supprimerLesNotifications(idEtudiant);
    }
    
    public static long getNbrNotifications(int idEtudiant){
        return new JpaNotifications().getNbrNotifications(idEtudiant);
    }
}
