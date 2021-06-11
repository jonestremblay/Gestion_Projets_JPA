package ca.hajofa.models;

import ca.hajofa.dao.jpa.JpaEtudiants;
import ca.hajofa.entites.*;
import ca.hajofa.etudiant.EtudiantConnecte;
import ca.hajofa.services.jpa.EtudiantsServices;
import ca.hajofa.ui.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * Cette classe definit le model d'un combobox ayant des etudiants.
 * @author jonat
 */
public class ListeEtudiantsComboBoxModel extends AbstractListModel implements ComboBoxModel{
    
    final static String NOM_FAUX_ETUDIANT_DEPART = "== Quel étudiant(e) ? ==";
    final private static Etudiants fauxEtudiantDepart = new Etudiants(NOM_FAUX_ETUDIANT_DEPART);
    private static List<Etudiants> listeEtudiants = new ArrayList<>();
    String selection = null ;

    
    public ListeEtudiantsComboBoxModel(int idCours){
        listeEtudiants.clear();
        /* Ajoute le faux cours pour inviter a faire un choix */
        fauxEtudiantDepart.setNomComplet(NOM_FAUX_ETUDIANT_DEPART);
        listeEtudiants.add(fauxEtudiantDepart);
        /* Rempli la liste selon le contenu de la base de donnees */
        List<Etudiants> liste =  EtudiantsServices.findByIDCours(idCours);
        for (Etudiants e : liste){
            listeEtudiants.add(e);
        }
    }

    public static List<Etudiants> getListeEtudiants() {
        return listeEtudiants;
    }

    public static void setListeEtudiants(List<Etudiants> listeEtudiants) {
        ListeEtudiantsComboBoxModel.listeEtudiants = listeEtudiants;
    }
   
    @Override
    public int getSize() {
        return listeEtudiants.size();
    }

    @Override
    public Object getElementAt(int index) {
        if (!listeEtudiants.isEmpty()){
            Etudiants etudiant = listeEtudiants.get(index);
            /* Retourne seulement le nomComplet si c'est le premier index 
                "== Quel étudiant(e) ? =="     */
            if (index != 0){
              etudiant.setNomComplet(etudiant.getPrenom() + " " + etudiant.getNom());
            }
            if (etudiant.getNomComplet().equals(NOM_FAUX_ETUDIANT_DEPART)){
                return etudiant.getNomComplet();
            } else {
                // etudiant.setNomComplet(etudiant.getPrenom() + " " + etudiant.getNom());
                return etudiant.getNomComplet() + " [" + etudiant.getIDEtudiant() + "]";
            }
        } else {
            /* Aucun etudiants */
            return NOM_FAUX_ETUDIANT_DEPART;
        }
        
    }
    
    public static void actualiserListeEtudiant(int idCours){
        listeEtudiants = EtudiantsServices.findByIDCours(idCours);
    }
    
    public static List<Etudiants> getListeEtudiantAJour(int idCours){
        return EtudiantsServices.findByIDCours(idCours);
    }
    
    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

     
}
