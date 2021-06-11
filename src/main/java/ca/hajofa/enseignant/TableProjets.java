/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.enseignant;

import ca.hajofa.entites.Etudiants;
import ca.hajofa.entites.Projets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fatim
 */
public class TableProjets {

    /**
     *Cette methode sert a remplir la Jtable projet avec les informations necessaires.
     * @param liste
     * @param tableProjets
     */
    public static void setValeursTableProjets(List<Projets> liste, JTable tableProjets) {
        DefaultTableModel modele = (DefaultTableModel) tableProjets.getModel();
        Object data[] = new Object[4];
        for (int i = 0; i < liste.size(); i++) {
           data[0] = liste.get(i).getProjetsPK().getIDProjet();
            data[1] = liste.get(i).getTitre();
            data[2] = liste.get(i).getAPropos();
            data[3] = new SimpleDateFormat("yyyy/MM/dd").format(liste.get(i).getDateRemise());

            modele.addRow(data);
        }

    }
    
    /**
     *
     * @param projet
     * @param tableProjets
     */
    public static void setValeursTableProjet(Projets projet, JTable tableProjets) {
        DefaultTableModel modele = (DefaultTableModel) tableProjets.getModel();
        Object data[] = new Object[4];
            data[0] = projet.getProjetsPK().getIDProjet();
            data[1] = projet.getTitre();
            data[2] = projet.getAPropos();
            data[3] = projet.getDateRemise();
            modele.addRow(data);
        

    }

    /**
     *Cette methode set a valider la date
     * @param date
     * @return
     */
    public static boolean validerdate(String date) {
        boolean valide = false;
        DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        try{
        formater.parse(date);
        valide = true;
        }catch(Exception e){
        
        valide = false;
        }
        return valide;
    }

    /**
     *Cette methode sert a valider la champs
     * @param titre
     * @param description
     * @param dateRemise
     * @return
     */
    public static boolean validerDataProjet(JTextField titre, JTextField description, 
                                            JTextField dateRemise){
    boolean flag = true;
        
        if (titre.getText().isEmpty() || description.getText().isEmpty()
                || dateRemise.getText().isEmpty() ) {
            flag = false;
            JOptionPane.showMessageDialog(null, "Le siasie des champs est obligatoire!",
                    "Saisie de valeurs",
                    JOptionPane.ERROR_MESSAGE);
        }else if(!validerdate(dateRemise.getText())){
            flag = false;
            JOptionPane.showMessageDialog(null, "la saiaie doit etre sous "
                + "forme de date (jj/mm/aaa)",
                    "Date valide",
                    JOptionPane.ERROR_MESSAGE);
        }

        return flag;
    }
}
