/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.dao.jpa;

import ca.hajofa.dao.interfaces.NotificationDao;
import ca.hajofa.entites.Etudiants;
import ca.hajofa.entites.Notifications;
import ca.hajofa.jpa.EntityManagerSingleton;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jonat
 */
public class JpaNotifications implements NotificationDao{

    @Override
    public boolean creerNotification(String notif, int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Etudiants etudiant = null;
        boolean cree = false;
        try {
            etudiant = em.find(Etudiants.class, idEtudiant);
            Notifications notification = new Notifications(etudiant, notif);
            etudiant.ajouterNotification(notification);
            try {
                transaction.begin();
                em.persist(etudiant);
                transaction.commit();
                cree = true;
                em.close();
            } catch (EntityExistsException eee){
                transaction.rollback();
            }
        } catch (NoResultException nor){
            System.out.println("ID etudiant invalide : " + idEtudiant);
        } catch (NullPointerException npe) {/* ignore */}
        return cree;
    }

    @Override
    public List<Notifications> lireNotifications(int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        List<Notifications> listeNotifs = null;
        try {
              Query requete = em.createNamedQuery("Notifications.findAll");
              requete.setParameter("idEtudiant", idEtudiant);
              listeNotifs = (List<Notifications>)requete.getResultList();
              em.close(); 
        } catch (NoResultException nor){
           System.out.println("Aucun notifications trouvées avec ce id Etudiant ( " + idEtudiant + " )");
        } catch (NullPointerException npe) {/* ignore */}
        return listeNotifs;
    }

    @Override
    public int supprimerLesNotifications(int idEtudiant) {
        EntityManager em = EntityManagerSingleton.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        int notifsSupprimees = 0;
        try {
              Query requete = em.createNamedQuery("Notifications.deleteAll");
              requete.setParameter("idEtudiant", idEtudiant);
              transaction.begin();
              notifsSupprimees = requete.executeUpdate();
              transaction.commit();
              em.close(); 
        } catch (NoResultException nor){
           System.out.println("Aucune notifications trouvées avec ce id Etudiant ( " + idEtudiant + " )");
        } catch (NullPointerException npe) {/* ignore */}
        return notifsSupprimees;
    }
    
    public long getNbrNotifications(int idEtudiant){
        EntityManager em = EntityManagerSingleton.getEntityManager();
        long nbrNotifs = 0;
        try {
            Query requete = em.createNamedQuery("Notifications.findCount");
            requete.setParameter("idEtudiant", idEtudiant);
            nbrNotifs = (long)requete.getSingleResult();
        } catch (NoResultException nor){
           System.out.println("Aucune notifications trouvées avec ce id Etudiant ( " + idEtudiant + " )");
        } catch (NullPointerException npe) {/* ignore */}
        return nbrNotifs;
    }
    
}
