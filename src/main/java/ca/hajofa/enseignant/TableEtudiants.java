/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.enseignant;

import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Etudiants;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Cette classe offre les methodes servant a manipuler la table etudiants, pour
 * un enseignant.
 *
 * @author jonat
 */
public abstract class TableEtudiants {

    /**
     *Cette methode sert a ajouter des informations a  la Jtable etudiant
     * @param liste
     * @param tableEtudiants
     */
    public static void ajouterData(List<Etudiants> liste, JTable tableEtudiants) {

        DefaultTableModel modele = (DefaultTableModel) tableEtudiants.getModel();
        Object data[] = new Object[3];
        if (!(liste.isEmpty())) {
            for (int i = 0; i < liste.size(); i++) {
                data[0] = liste.get(i).getNom();
                data[1] = liste.get(i).getPrenom();
                data[2] = liste.get(i).getEmail();
                modele.addRow(data);

            }
        } else {
            JOptionPane.showMessageDialog(null, " Aucun etudiant n'est inscis à ce cours");
        }
    }

    /**
     *Cette methode sert a remplir le combo box avex la liste des etudiants
     * @param liste
     * @param comboBoxCours
     */
    public static void setValeurs(List<Etudiants> liste, JComboBox comboBoxCours) {

        for (Etudiants etud : liste) {
            String des = etud.getIDEtudiant()
                    + " " + etud.getNom() + " " + etud.getPrenom();
            comboBoxCours.addItem(des);
        }

    }

    /**
     *Cette methode sert mettre des valeurs dans la Jtable etudiants
     * @param liste
     * @param tableCours
     */
    public static void setValeursTableEtudiants(List<Etudiants> liste, JTable tableCours) {
        DefaultTableModel modele = (DefaultTableModel) tableCours.getModel();
        Object data[] = new Object[2];
        for (int i = 0; i < liste.size(); i++) {
            data[0] = liste.get(i).getIDEtudiant();
            data[1] = liste.get(i).getPrenom() + " "
                    + liste.get(i).getPrenom();

            modele.addRow(data);
        }

    }

    /**
     *Cette methode sert a valider le courriel d un etudiant
     * @param courriel
     * @return
     */
    public static boolean validerCourriel(String courriel) {
        boolean valide = false;
        //recuperer la position de @
        int position = courriel.indexOf('@');

        if (position == -1) {
            valide = false;
        }else{
            String[] token = courriel.split("@");
            System.out.println(token[0]);
            String chaine = token[1];
            if (chaine.equals("bdeb.qc.ca")) {
                valide = true;
            }
            
        }

        
        return valide;
    }

    /**
     *Cette methode sert a ajouter un etudiant
     * @param nom
     * @param prenom
     * @param courriel
     * @param motPasse
     * @return
     */
    public static boolean validerAjoutEtudiant(JTextField nom, JTextField prenom, 
                                            JTextField courriel, JTextField motPasse) {
        boolean flag = true;
        
        if (nom.getText().isEmpty() || prenom.getText().isEmpty()
                || courriel.getText().isEmpty() || motPasse.getText().isEmpty()) {
            flag = false;
            JOptionPane.showMessageDialog(null, "Le siasie des champs est obligatoire!",
                    "Saisie de valeurs",
                    JOptionPane.ERROR_MESSAGE);
        }else if(!(validerCourriel(courriel.getText()))){
            flag = false;
            JOptionPane.showMessageDialog(null, "Entrer un courriel valide (exemple@bdeb.qc.ca)",
                    "Saisie de valeurs",
                    JOptionPane.ERROR_MESSAGE);
            
        }

        return flag;
    }
}
