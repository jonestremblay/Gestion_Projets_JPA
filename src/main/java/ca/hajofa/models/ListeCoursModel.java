package ca.hajofa.models;

import ca.hajofa.dao.jpa.JpaEtudiantsCours;
import ca.hajofa.entites.Cours;
import ca.hajofa.etudiant.EtudiantConnecte;
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
 * Cette classe definit le model d'une JList contenant des cours.
 * @author JonathanTremblay
 */
public class ListeCoursModel extends AbstractListModel {
    
    private static List<Cours> listeCours = new JpaEtudiantsCours().findAllCoursByEtudiant
                                        (EtudiantConnecte.idEtudiant);
    
    @Override
    public int getSize() {
        return listeCours.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listeCours.get(index).getTitre();
    }
    
    public static String getAProposCoursAt(int index){
        return listeCours.get(index).getAPropos();
    }
    
    public static void actualiserListeCours(){
        listeCours = new JpaEtudiantsCours().findAllCoursByEtudiant
                                        (EtudiantConnecte.idEtudiant);
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
