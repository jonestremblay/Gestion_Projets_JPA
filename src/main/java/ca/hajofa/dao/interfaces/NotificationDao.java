/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.interfaces;

import ca.hajofa.entites.Notifications;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonat
 */
public interface NotificationDao {
    /* Inutile d'avoir une methode de modification de notification */
    public boolean creerNotification(String notif, int idEtudiant);
    public List<Notifications> lireNotifications(int idEtudiant);
    public int supprimerLesNotifications(int idEtudiant);
    public long getNbrNotifications(int idEtudiant);
}
