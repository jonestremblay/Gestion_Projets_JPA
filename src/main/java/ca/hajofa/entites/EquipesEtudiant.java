package ca.hajofa.entites;

/**
 *
 * @author Hassna, Fatima
 */
public class EquipesEtudiant {
   private int idEquipe,idEtudiant;

    public EquipesEtudiant(int idEquipe, int idEtudiant) {
        this.idEquipe = idEquipe;
        this.idEtudiant = idEtudiant;
    }

    public EquipesEtudiant() {
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.idEquipe;
        hash = 73 * hash + this.idEtudiant;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EquipesEtudiant other = (EquipesEtudiant) obj;
        if (this.idEquipe != other.idEquipe) {
            return false;
        }
        if (this.idEtudiant != other.idEtudiant) {
            return false;
        }
        return true;
    }
}
