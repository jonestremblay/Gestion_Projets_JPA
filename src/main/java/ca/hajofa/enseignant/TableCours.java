/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.enseignant;

import ca.hajofa.entites.Cours;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Cette classe offre les methodes servant a manipuler la table cours, pour un
 * enseignant.
 *
 * 
 */
public abstract class TableCours {

    final static int ANNEE_MIN = 2021;
    final static int ANNEE_MAX = 2026;

    /**
     * Cette methode setValeursTableCours sert a remplir la JTable 
     * des cours d'un enseignant
     * @param liste
     * @param tableCours
     */
    public static void setValeursTableCours(List<Cours> liste, JTable tableCours) {
        DefaultTableModel modele = (DefaultTableModel) tableCours.getModel();
        Object data[] = new Object[4];
        for (int i = 0; i < liste.size(); i++) {
            data[0] = liste.get(i).getTitre();
            data[1] = liste.get(i).getSessionCours() + " " + liste.get(i).getAnneeCours();
            data[2] = liste.get(i).getAPropos();
            data[3] = liste.get(i).getCleCours();
            modele.addRow(data);
        }

    }

    /**
     *Cette methode setValeurs sert a remplir le combo box des cours avec la liste
     * des cours de l'enseignant
     * @param liste
     * @param comboBoxCours
     */
    public static void setValeurs(List<Cours> liste, JComboBox comboBoxCours) {

        for (Cours cours : liste) {
            String des = cours.getAPropos();
            comboBoxCours.addItem(des);
        }

    }

    /**
     *Cette methode set a valider les donnees d'un cours
     * @param titre
     * @param description
     * @param annee
     * @param cle
     * @return
     */
    public static boolean validerDonneesCours(JTextField titre, JTextField description,
            JTextField annee,  JTextField cle) {
        boolean flag = true;
        
        if (titre.getText().isEmpty() || description.getText().isEmpty()
                || annee.getText().isEmpty() ||  cle.getText().isEmpty()) {
            flag = false;
            JOptionPane.showMessageDialog(null, "Le saisie des champs est obligatoire!",
                    "Saisie de valeurs",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                if (Integer.parseInt(annee.getText()) < ANNEE_MIN
                        || Integer.parseInt(annee.getText()) > ANNEE_MAX) {
                    JOptionPane.showMessageDialog(null, "L'année doit etre entre "
                            + ANNEE_MIN + "et " + ANNEE_MAX,
                            "Saisie de valeurs",
                            JOptionPane.ERROR_MESSAGE);
                    flag = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "L'année doit etre "
                        + "numerique ",
                        "Saisie de valeurs",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return flag;
    }
}
