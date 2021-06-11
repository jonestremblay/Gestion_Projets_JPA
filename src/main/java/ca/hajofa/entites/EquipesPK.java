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
public class EquipesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "ID_Equipe")
    private int iDEquipe;
    @Basic(optional = false)
    @Column(name = "ID_Cours")
    private int iDCours;
    @Basic(optional = false)
    @Column(name = "nomEquipe")
    private String nomEquipe;

    public EquipesPK() {
    }
    
    public EquipesPK(int iDCours, String nomEquipe) {
        this.iDCours = iDCours;
        this.nomEquipe = nomEquipe;
    }
    
    public EquipesPK(int iDEquipe, int iDCours, String nomEquipe) {
        this.iDEquipe = iDEquipe;
        this.iDCours = iDCours;
        this.nomEquipe = nomEquipe;
    }

    public int getIDEquipe() {
        return iDEquipe;
    }

    public void setIDEquipe(int iDEquipe) {
        this.iDEquipe = iDEquipe;
    }

    public int getIDCours() {
        return iDCours;
    }

    public void setIDCours(int iDCours) {
        this.iDCours = iDCours;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDEquipe;
        hash += (int) iDCours;
        hash += (nomEquipe != null ? nomEquipe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipesPK)) {
            return false;
        }
        EquipesPK other = (EquipesPK) object;
        if (this.iDEquipe != other.iDEquipe) {
            return false;
        }
        if (this.iDCours != other.iDCours) {
            return false;
        }
        if ((this.nomEquipe == null && other.nomEquipe != null) || (this.nomEquipe != null && !this.nomEquipe.equals(other.nomEquipe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.hajofa.orm.EquipesPK[ iDEquipe=" + iDEquipe + ", iDCours=" + iDCours + ", nomEquipe=" + nomEquipe + " ]";
    }
    
}
