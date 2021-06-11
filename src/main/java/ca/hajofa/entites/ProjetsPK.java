/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.entites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jonat
 */
@Embeddable
public class ProjetsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_Projet")
    private int iDProjet;
    @Basic(optional = false)
    @Column(name = "ID_Cours")
    private int iDCours;
    @Basic(optional = false)
    @Column(name = "ID_Equipe")
    private int iDEquipe;

    public ProjetsPK() {
    }

    public ProjetsPK(int iDCours, int iDEquipe) {
        this.iDCours = iDCours;
        this.iDEquipe = iDEquipe;
    }
    
    public ProjetsPK(int iDProjet, int iDCours, int iDEquipe) {
        this.iDProjet = iDProjet;
        this.iDCours = iDCours;
        this.iDEquipe = iDEquipe;
    }

    public ProjetsPK(int iDProjet) {
        this.iDProjet = iDProjet;
    }
    
    
    public int getIDProjet() {
        return iDProjet;
    }

    public void setIDProjet(int iDProjet) {
        this.iDProjet = iDProjet;
    }

    public int getIDCours() {
        return iDCours;
    }

    public void setIDCours(int iDCours) {
        this.iDCours = iDCours;
    }

    public int getIDEquipe() {
        return iDEquipe;
    }

    public void setIDEquipe(int iDEquipe) {
        this.iDEquipe = iDEquipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDProjet;
        hash += (int) iDCours;
        hash += (int) iDEquipe;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjetsPK)) {
            return false;
        }
        ProjetsPK other = (ProjetsPK) object;
        if (this.iDProjet != other.iDProjet) {
            return false;
        }
        if (this.iDCours != other.iDCours) {
            return false;
        }
        if (this.iDEquipe != other.iDEquipe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.hajofa.orm.ProjetsPK[ iDProjet=" + iDProjet + ", iDCours=" + iDCours + ", iDEquipe=" + iDEquipe + " ]";
    }
    
}
