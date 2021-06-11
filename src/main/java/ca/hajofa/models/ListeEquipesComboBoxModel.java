package ca.hajofa.models;

import ca.hajofa.dao.jpa.JpaEquipes;
import ca.hajofa.entites.Cours;
import ca.hajofa.entites.Equipes;
import ca.hajofa.entites.EquipesPK;
import ca.hajofa.etudiant.EtudiantConnecte;
import ca.hajofa.services.jpa.EquipeServices;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * Cette classe definit le model d'un combobox contenant des equipes etudiantes.
 * @author JonathanTremblay
 */
public class ListeEquipesComboBoxModel extends AbstractListModel implements ComboBoxModel{
    
    private static List<Equipes> listeEquipes = new ArrayList<>();
    private final static Equipes fausseEquipeDepart = new Equipes();
    private static int idCours;
    
    public ListeEquipesComboBoxModel(){
        /* Ajoute la fausse equipe pour inviter a faire un choix d'equipe */
        fausseEquipeDepart.setEquipesPK(new EquipesPK(-1, "== Choisissez une équipe =="));
        listeEquipes.add(fausseEquipeDepart);
        /* Rmepli la liste des equipes selon le contenu de la base de donnes */
        List<Equipes> liste = EquipeServices.findAllByIdEtudiant(idCours);
        for (Equipes eq : liste){
            listeEquipes.add(eq);
        }
    }
    
    public ListeEquipesComboBoxModel(int idCours){
        this.idCours = idCours;
         /* Ajoute la fausse equipe pour inviter a faire un choix d'equipe */
        fausseEquipeDepart.setEquipesPK(new EquipesPK(-1, "== Choisissez une équipe =="));
        listeEquipes.add(fausseEquipeDepart);
        /* Rmepli la liste des equipes selon le contenu de la base de donnes */
        List<Equipes> liste = EquipeServices.findAllByIdCours(idCours);
        for (Equipes eq : liste){
            listeEquipes.add(eq);
        }
    }
    
    String selection = null ;
    @Override
    public int getSize() {
        return listeEquipes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return listeEquipes.get(index).getEquipesPK().getNomEquipe();
    }
    
    public static void actualiserListeEquipes(int idCours){
        listeEquipes = EquipeServices.findAllByIdCours(idCours);
    }
    
    public static void actualiserLesEquipes(int idCours){
        List<Equipes> liste = EquipeServices.findAllByIdCours(idCours);
        List<Equipes> listeAvecFausseEquipe = new ArrayList<>();
        listeAvecFausseEquipe.add(fausseEquipeDepart);
        for (Equipes e : liste){
            listeAvecFausseEquipe.add(e);
        }
        listeEquipes = listeAvecFausseEquipe;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    public static List<Equipes> getListeCours(){
        return listeEquipes;
    }
}