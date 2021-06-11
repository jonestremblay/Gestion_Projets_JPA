package ca.hajofa.models;


import ca.hajofa.dao.jpa.JpaEtudiantsCours;
import ca.hajofa.entites.Cours;
import ca.hajofa.etudiant.EtudiantConnecte;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * Cette classe definit le model d'un combobox contenant des cours.
 * @author JonathanTremblay
 */
public class ListeCoursComboBoxModel extends AbstractListModel implements ComboBoxModel{
    
    private static List<Cours> listeCours = new ArrayList<>();
    private final static Cours fauxCoursDepart = new Cours();
    
    
    public ListeCoursComboBoxModel(){
        listeCours.clear();
        /* Ajoute le faux cours pour inviter a faire un choix */
        fauxCoursDepart.setTitre("== Choisissez un cours ==");
        fauxCoursDepart.setIDCours(-1);
        listeCours.add(fauxCoursDepart);
        /* Rempli la liste selon le contenu de la base de donnees */
        List<Cours> liste = new JpaEtudiantsCours().findAllCoursByEtudiant(EtudiantConnecte.idEtudiant);
        for (Cours c : liste){
            listeCours.add(c);
        }
    }
    
    String selection = null ;
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

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    public static List<Cours> getListeCours(){
        return listeCours;
    }
}
