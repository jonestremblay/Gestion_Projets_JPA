/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.entites;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "Equipes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipes.findAll", query = "SELECT e FROM Equipes e"),
    @NamedQuery(name = "Equipes.findByIDEquipe", query = "SELECT e FROM Equipes e WHERE e.equipesPK.iDEquipe = :iDEquipe"),
    @NamedQuery(name = "Equipes.findByIDCours", query = "SELECT e FROM Equipes e WHERE e.equipesPK.iDCours = :idCours"),
    @NamedQuery(name = "Equipes.findByNomEquipe", 
            query = "SELECT e FROM Equipes e WHERE e.equipesPK.nomEquipe = :nomEquipe"),
    @NamedQuery(name = "Equipes.findByNomEquipeAndCours", 
            query = "SELECT e FROM Equipes e WHERE e.equipesPK.nomEquipe = :nomEquipe AND e.equipesPK.iDCours = :idCours")})
public class Equipes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EquipesPK equipesPK;
    @JoinTable(name = "EquipeEtudiante", joinColumns = {
        @JoinColumn(name = "ID_Equipe", referencedColumnName = "ID_Equipe"),
        @JoinColumn(name = "ID_Equipe", referencedColumnName = "ID_Equipe"),
        @JoinColumn(name = "ID_Equipe", referencedColumnName = "ID_Equipe")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant"),
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant"),
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant")})
    @ManyToMany
    private Collection<Etudiants> etudiantsCollection;
    @JoinColumns({
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours", insertable = false, updatable = false),
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours", insertable = false, updatable = false),
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Cours cours;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "equipe")
    private Projets projet;

    public Equipes() {
    }
    
    public Equipes(int iDCours, String nomEquipe){
        this.equipesPK = new EquipesPK(iDCours, nomEquipe);
    }
    
    public Equipes(EquipesPK equipesPK) {
        this.equipesPK = equipesPK;
    }

    public Equipes(int iDEquipe, int iDCours, String nomEquipe) {
        this.equipesPK = new EquipesPK(iDEquipe, iDCours, nomEquipe);
    }

    public EquipesPK getEquipesPK() {
        return equipesPK;
    }

    public void setEquipesPK(EquipesPK equipesPK) {
        this.equipesPK = equipesPK;
    }

    @XmlTransient
    public Collection<Etudiants> getEtudiantsCollection() {
        return etudiantsCollection;
    }

    public void setEtudiantsCollection(Collection<Etudiants> etudiantsCollection) {
        this.etudiantsCollection = etudiantsCollection;
    }
    
    public void ajouterMembreEquipe(Etudiants e){
        this.etudiantsCollection.add(e);
    }
    
    public void retirerMembreEquipe(Etudiants e){
        this.etudiantsCollection.remove(e);
    }
    
    
    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Projets getProjet() {
        return projet;
    }

    public void setProjet(Projets projet) {
        this.projet = projet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipesPK != null ? equipesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipes)) {
            return false;
        }
        Equipes other = (Equipes) object;
        if ((this.equipesPK == null && other.equipesPK != null) || (this.equipesPK != null && !this.equipesPK.equals(other.equipesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.hajofa.orm.Equipes[ equipesPK=" + equipesPK + " ]";
    }
    
}
