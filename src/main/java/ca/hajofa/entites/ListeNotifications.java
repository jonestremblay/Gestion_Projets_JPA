package ca.hajofa.entites;

import ca.hajofa.dao.jpa.JpaNotifications;
import ca.hajofa.entites.Notifications;
import ca.hajofa.etudiant.EtudiantConnecte;
import ca.hajofa.services.jpa.NotificationsServices;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe definit la liste des notifications de l'etudiant connecte.
 * @author JonathanTremblay
 */
public abstract class ListeNotifications {
    public final static int NBR_NOTIFS_VIDES = 6;
    private static List<Notifications> listeNotifs = 
            NotificationsServices.lireNotifications(EtudiantConnecte.idEtudiant);
    
    /**
     * On ajoute ici x notifications vide pour fix l'affichage
     * de la table de notifications, qui n'affichait plus s'il y avait 0 notifs
     * @return listeNotifications avec x notifs vides à la fin
     */
    public static List<Notifications> getListeNotifs() {
        if (listeNotifs.size() == 0){
             listeNotifs.add(new Notifications(EtudiantConnecte.idEtudiant, 
                "Vous n'avez pas de notifications")); 
        } 
        for (int i = 0; i < NBR_NOTIFS_VIDES; i++){
            listeNotifs.add(new Notifications(EtudiantConnecte.idEtudiant, "")); 
        }
        return listeNotifs;
    }
    
    public static List<Notifications> getListeNotifsCourante() {
        return listeNotifs;
    }
    
    public static void setListeNotifs(List<Notifications> listeNotifs) {
        ListeNotifications.listeNotifs = listeNotifs;
    }
    
    public static void ajouterNotification(Notifications notif){
        ListeNotifications.listeNotifs.add(notif);
    }
    
    
    public static String listeNotifsToString(){
        String listeNotifs = "";
        int i = 0;
        for (Notifications nf : ListeNotifications.getListeNotifs()){
            listeNotifs += nf + "(" + i + ")" + "\n";
            i++;
        }
        return listeNotifs;
    }
}
