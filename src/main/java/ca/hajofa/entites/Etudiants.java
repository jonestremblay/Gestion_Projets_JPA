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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "Etudiants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etudiants.findAll", query = "SELECT e FROM Etudiants e"),
    @NamedQuery(name = "Etudiants.findByIDEtudiant", query = "SELECT e FROM Etudiants e WHERE e.iDEtudiant = :iDEtudiant"),
    @NamedQuery(name = "Etudiants.findByNom", query = "SELECT e FROM Etudiants e WHERE e.nom = :nom"),
    @NamedQuery(name = "Etudiants.findByPrenom", query = "SELECT e FROM Etudiants e WHERE e.prenom = :prenom"),
    @NamedQuery(name = "Etudiants.findByEmail", query = "SELECT e FROM Etudiants e WHERE e.email = :email"),
    @NamedQuery(name = "Etudiants.findByPasswd", query = "SELECT e FROM Etudiants e WHERE e.passwd = :passwd")})
public class Etudiants implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Etudiant")
    private Integer iDEtudiant;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Transient
    private String nomComplet;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "passwd")
    private String passwd;
    @ManyToMany(mappedBy = "etudiantsCollection")
    private Collection<Cours> coursCollection;
    @ManyToMany(mappedBy = "etudiantsCollection")
    private Collection<Equipes> equipesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiants")
    private Collection<Notifications> notificationsCollection;

    public Etudiants() {
    }

    public Etudiants(Integer iDEtudiant) {
        this.iDEtudiant = iDEtudiant;
    }
    
    public Etudiants(String nomComplet){
        this.nomComplet = nomComplet;
    }
    
    public Etudiants(String nom, String prenom, String email, String passwd) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwd = passwd;
        this.nomComplet = prenom + " " + nom;
    }
    
    public Etudiants(Integer iDEtudiant, String nom, String prenom, String email, String passwd) {
        this.iDEtudiant = iDEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwd = passwd;
        this.nomComplet = prenom + " " + nom;
    }
    
    public Integer getIDEtudiant() {
        return iDEtudiant;
    }

    public void setIDEtudiant(Integer iDEtudiant) {
        this.iDEtudiant = iDEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String getNomComplet(){
        return this.nomComplet;
    }
    
    public void setNomComplet(String nomComplet){
        this.nomComplet = nomComplet;
    }

    @XmlTransient
    public Collection<Cours> getCoursCollection() {
        return coursCollection;
    }

    public void setCoursCollection(Collection<Cours> coursCollection) {
        this.coursCollection = coursCollection;
    }
    
    public void inscriptionA(Cours c){
        
        c.getEtudiantsCollection().add(this);
    }
    
    @XmlTransient
    public Collection<Equipes> getEquipesCollection() {
        return equipesCollection;
    }

    public void setEquipesCollection(Collection<Equipes> equipesCollection) {
        this.equipesCollection = equipesCollection;
    }
    
    public void ajouterEquipe(Equipes e){
        this.equipesCollection.add(e);
    }
    
    public void supprimerEquipe(Equipes e){
        this.equipesCollection.remove(e);
    }
    
    @XmlTransient
    public Collection<Notifications> getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Collection<Notifications> notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }
    
    public void ajouterNotification(Notifications notif){
        this.notificationsCollection.add(notif);
    }
    
    public void supprimerNotifications(){
        this.notificationsCollection.clear();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEtudiant != null ? iDEtudiant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etudiants)) {
            return false;
        }
        Etudiants other = (Etudiants) object;
        if ((this.iDEtudiant == null && other.iDEtudiant != null) || (this.iDEtudiant != null && !this.iDEtudiant.equals(other.iDEtudiant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Etudiants[ iDEtudiant=" + iDEtudiant + " ]";
    }
    
}
