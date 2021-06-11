/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.entites;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "Cours")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cours.findAll", 
            query = "SELECT c FROM Cours c"),
    @NamedQuery(name = "Cours.findByIDCours", 
            query = "SELECT c FROM Cours c WHERE c.iDCours = :iDCours"),
    @NamedQuery(name = "Cours.findByCleCours", 
            query = "SELECT c FROM Cours c WHERE c.cleCours = :cleCours"),
    @NamedQuery(name = "Cours.findBySessionCours", 
            query = "SELECT c FROM Cours c WHERE c.sessionCours = :sessionCours"),
    @NamedQuery(name = "Cours.findByAnneeCours", 
            query = "SELECT c FROM Cours c WHERE c.anneeCours = :anneeCours"),
    @NamedQuery(name = "Cours.findByTitre", 
            query = "SELECT c FROM Cours c WHERE c.titre = :titre"),
    @NamedQuery(name = "Cours.findByAPropos", 
            query = "SELECT c FROM Cours c WHERE c.aPropos = :aPropos"),
        @NamedQuery(name = "Cours.findByIdProf", 
            query = "SELECT c FROM Cours c WHERE c.enseignants.iDProf = :idProf"),/* ,
    @NamedQuery(name = "EtudiantCours.verifierInscription", 
            query = "SELECT EXISTS(SELECT ec FROM EtudiantCours ec WHERE ec.ID_Etudiant = :ID_Etudiant AND ec.ID_Cours = :ID_Cours)") */ })
public class Cours implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Cours")
    private Integer iDCours;
    @Basic(optional = false)
    @Column(name = "cleCours")
    private String cleCours;
    @Basic(optional = false)
    @Column(name = "sessionCours")
    private String sessionCours;
    @Basic(optional = false)
    @Column(name = "anneeCours")
    private int anneeCours;
    @Basic(optional = false)
    @Column(name = "titre")
    private String titre;
    @Column(name = "aPropos")
    private String aPropos;
    @JoinTable(name = "EtudiantCours", joinColumns = {
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours"),
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours"),
        @JoinColumn(name = "ID_Cours", referencedColumnName = "ID_Cours")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant"),
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant"),
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant")})
    @ManyToMany
    private Collection<Etudiants> etudiantsCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cours")
    private Equipes equipes;
    @JoinColumns({
        @JoinColumn(name = "ID_Prof", referencedColumnName = "ID_Prof"),
        @JoinColumn(name = "ID_Prof", referencedColumnName = "ID_Prof"),
        @JoinColumn(name = "ID_Prof", referencedColumnName = "ID_Prof")})
    @ManyToOne(optional = false)
    private Enseignants enseignants;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cours")
    private Projets projets;

    public Cours() {
    }

    public Cours(Integer iDCours) {
        this.iDCours = iDCours;
    }

    public Cours(Integer iDCours, String cleCours, String sessionCours, int anneeCours, String titre) {
        this.iDCours = iDCours;
        this.cleCours = cleCours;
        this.sessionCours = sessionCours;
        this.anneeCours = anneeCours;
        this.titre = titre;
    }
    
     public Cours(String cleCours,String aPropos, int anneeCours,  String titre,String sessionCours) {
        this.cleCours = cleCours;
        this.anneeCours = anneeCours;
        this.titre = titre;
        this.aPropos = aPropos;
        this.sessionCours = sessionCours;
    }

    public Integer getIDCours() {
        return iDCours;
    }

    public void setIDCours(Integer iDCours) {
        this.iDCours = iDCours;
    }

    public String getCleCours() {
        return cleCours;
    }

    public void setCleCours(String cleCours) {
        this.cleCours = cleCours;
    }

    public String getSessionCours() {
        return sessionCours;
    }

    public void setSessionCours(String sessionCours) {
        this.sessionCours = sessionCours;
    }

    public int getAnneeCours() {
        return anneeCours;
    }

    public void setAnneeCours(int anneeCours) {
        this.anneeCours = anneeCours;
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

    @XmlTransient
    public Collection<Etudiants> getEtudiantsCollection() {
        return etudiantsCollection;
    }

    public void setEtudiantsCollection(Collection<Etudiants> etudiantsCollection) {
        this.etudiantsCollection = etudiantsCollection;
    }
    
    public void inscrireEtudiant(Etudiants e){
        etudiantsCollection.add(e);
        e.getCoursCollection().add(this);
    }
    

   

    
      public void retirerEtudiant(Etudiants e){
        etudiantsCollection.remove(e);
        e.getCoursCollection().remove(this);
    }
    
    public Equipes getEquipes() {
        return equipes;
    }

    public void setEquipes(Equipes equipes) {
        this.equipes = equipes;
    }

    public Enseignants getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Enseignants enseignants) {
        this.enseignants = enseignants;
    }

    public Projets getProjets() {
        return projets;
    }

    public void setProjets(Projets projets) {
        this.projets = projets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCours != null ? iDCours.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cours)) {
            return false;
        }
        Cours other = (Cours) object;
        if ((this.iDCours == null && other.iDCours != null) || (this.iDCours != null && !this.iDCours.equals(other.iDCours))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.hajofa.orm.Cours[ iDCours=" + iDCours + " ]";
    }
    
}
