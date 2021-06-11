package ca.hajofa.models;


import ca.hajofa.dao.jpa.JpaProjets;
import ca.hajofa.entites.*;
import ca.hajofa.etudiant.EtudiantConnecte;
import ca.hajofa.services.jpa.ProjetServices;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


/**
 * Cette classe definit le model d'une JList contenant des projets etudiants.
 * @author JonathanTremblay
 */
public class ListeProjetsModel extends AbstractListModel {
    
    private static List<Projets> listeProjets = 
            ProjetServices.findAllByIDEtudiant(EtudiantConnecte.idEtudiant);
    
    @Override
    public int getSize() {
        return listeProjets.size();
    }

    @Override
    public Object getElementAt(int index) {
        System.out.println(listeProjets.get(index).getTitre());
        return listeProjets.get(index).getTitre();
    }
   
    public static Object getProjectAt(int index) {
        return listeProjets.get(index);
    }
    
    public static String getAProposProjetAt(int index){
        return listeProjets.get(index).getAPropos();
    }
    
    public static void actualiserListeProjets(){
        listeProjets = ProjetServices.findAllByIDEtudiant(EtudiantConnecte.idEtudiant);
    }

    
    /* Initialise le renderer des cellules de la liste, avec une bordure noire */
    public static ListCellRenderer<? super String> getRenderer() {
    return new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(JList<?> list,
          Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel listCellRendererComponent = (JLabel) super
            .getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
        listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(2,
            2, 2, 2, Color.BLACK));
        return listCellRendererComponent;
      }
    };
  } 
}
