/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.entites;

import ca.hajofa.entites.Cours;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "Projets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projets.findAll", query = "SELECT p FROM Projets p"),
    @NamedQuery(name = "Projets.findByIDProjet", query = "SELECT p FROM Projets p WHERE p.projetsPK.iDProjet = :iDProjet"),
    @NamedQuery(name = "Projets.findByTitre", query = "SELECT p FROM Projets p WHERE p.titre = :titre"),
    @NamedQuery(name = "Projets.findByAPropos", query = "SELECT p FROM Projets p WHERE p.aPropos = :aPropos"),
    @NamedQuery(name = "Projets.findByDateRemise", query = "SELECT p FROM Projets p WHERE p.dateRemise = :dateRemise"),
    @NamedQuery(name = "Projets.findByIDCours", query = "SELECT p FROM Projets p WHERE p.projetsPK.iDCours = :iDCours"),
    @NamedQuery(name = "Projets.findByIDEquipe", query = "SELECT p FROM Projets p WHERE p.projetsPK.iDEquipe = :iDEquipe")})
public class Projets implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProjetsPK projetsPK;
    @Basic(optional = false)
    @Column(name = "titre")
    private String titre;
    @Column(name = "aPropos")
    private String aPropos;
    @Column(name = "dateRemise")
    @Temporal(TemporalType.DATE)
    private Date dateRemise;
    @JoinColumns({
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours", insertable = false, updatable = false),
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours", insertable = false, updatable = false),
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Cours cours;
    @JoinColumns({
        @JoinColumn(name = "ID_Equipe", referencedColumnName = "ID_Equipe", insertable = false, updatable = false),
        @JoinColumn(name = "ID_Equipe", referencedColumnName = "ID_Equipe", insertable = false, updatable = false),
        @JoinColumn(name = "ID_Equipe", referencedColumnName = "ID_Equipe", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Equipes equipe;
    
    
    public Projets() {
    }

    public Projets(ProjetsPK projetsPK) {
        this.projetsPK = projetsPK;
    }

    public Projets(ProjetsPK projetsPK, String titre) {
        this.projetsPK = projetsPK;
        this.titre = titre;
    }

    public Projets(int iDProjet, int iDCours, int iDEquipe) {
        this.projetsPK = new ProjetsPK(iDProjet, iDCours, iDEquipe);
    }

    public ProjetsPK getProjetsPK() {
        return projetsPK;
    }

    public void setProjetsPK(ProjetsPK projetsPK) {
        this.projetsPK = projetsPK;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAPropos() {
        return aPropos;
    }

    public void setAPropos(String aPropos) {
        this.aPropos = aPropos;
    }

    public Date getDateRemise() {
        return dateRemise;
    }

    public void setDateRemise(Date dateRemise) {
        this.dateRemise = dateRemise;
    }

    
    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Equipes getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipes equipe) {
        this.equipe = equipe;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projetsPK != null ? projetsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projets)) {
            return false;
        }
        Projets other = (Projets) object;
        if ((this.projetsPK == null && other.projetsPK != null) || (this.projetsPK != null && !this.projetsPK.equals(other.projetsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Projets[ titre=" + titre + " | dateRemise=" + dateRemise
                + " | aPropos=" + aPropos + "| projetsPK=" + projetsPK + " ]";
    }
    
}
