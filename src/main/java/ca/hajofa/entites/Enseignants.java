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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "Enseignants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enseignants.findAll", query = "SELECT e FROM Enseignants e"),
    @NamedQuery(name = "Enseignants.findByIDProf", query = "SELECT e FROM Enseignants e WHERE e.iDProf = :iDProf"),
    @NamedQuery(name = "Enseignants.findByNom", query = "SELECT e FROM Enseignants e WHERE e.nom = :nom"),
    @NamedQuery(name = "Enseignants.findByPrenom", query = "SELECT e FROM Enseignants e WHERE e.prenom = :prenom"),
    @NamedQuery(name = "Enseignants.findByEmail", query = "SELECT e FROM Enseignants e WHERE e.email = :email"),
    @NamedQuery(name = "Enseignants.findByPasswd", query = "SELECT e FROM Enseignants e WHERE e.passwd = :passwd")})
public class Enseignants implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Prof")
    private Integer iDProf;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "passwd")
    private String passwd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enseignants")
    private Collection<Cours> coursCollection;

    public Enseignants() {
    }

    public Enseignants(Integer iDProf) {
        this.iDProf = iDProf;
    }
    
    public Enseignants(String nom, String prenom, String email, String passwd) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwd = passwd;
    }
    
    public Enseignants(Integer iDProf, String nom, String prenom, String email, String passwd) {
        this.iDProf = iDProf;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwd = passwd;
    }

    public Integer getIDProf() {
        return iDProf;
    }

    public void setIDProf(Integer iDProf) {
        this.iDProf = iDProf;
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

    @XmlTransient
    public Collection<Cours> getCoursCollection() {
        return coursCollection;
    }

    public void setCoursCollection(Collection<Cours> coursCollection) {
        this.coursCollection = coursCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProf != null ? iDProf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enseignants)) {
            return false;
        }
        Enseignants other = (Enseignants) object;
        if ((this.iDProf == null && other.iDProf != null) || (this.iDProf != null && !this.iDProf.equals(other.iDProf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ca.hajofa.orm.Enseignants[ iDProf=" + iDProf + " ]";
    }
    
}
