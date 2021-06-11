/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.hajofa.entites;

import ca.hajofa.services.jpa.EtudiantsServices;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "Notifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n WHERE n.etudiants.iDEtudiant = :idEtudiant"),
    @NamedQuery(name = "Notifications.deleteAll", query = "DELETE FROM Notifications n WHERE n.etudiants.iDEtudiant = :idEtudiant"),
    @NamedQuery(name = "Notifications.findByIDNotif", query = "SELECT n FROM Notifications n WHERE n.iDNotif = :iDNotif"),
    @NamedQuery(name = "Notifications.findByNotif", query = "SELECT n FROM Notifications n WHERE n.notif = :notif"),
    @NamedQuery(name = "Notifications.findCount", 
            query = "SELECT COUNT(n) FROM Notifications n WHERE n.etudiants.iDEtudiant = :idEtudiant")})
public class Notifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Notif")
    private Integer iDNotif;
    @Basic(optional = false)
    @Column(name = "notif")
    private String notif;
    @JoinColumns({
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant"),
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant"),
        @JoinColumn(name = "ID_Etudiant", referencedColumnName = "ID_Etudiant")})
    @ManyToOne(optional = false)
    private Etudiants etudiants;

    public Notifications() {
    }

    public Notifications(Integer iDNotif) {
        this.iDNotif = iDNotif;
    }
    
    public Notifications(Etudiants etudiant, String notif){
        this.etudiants = etudiant;
        this.notif = notif;
    }

    public Notifications(Integer iDNotif, String notif) {
        this.iDNotif = iDNotif;
        this.notif = notif;
    }

    public Integer getIDNotif() {
        return iDNotif;
    }

    public void setIDNotif(Integer iDNotif) {
        this.iDNotif = iDNotif;
    }

    public String getNotif() {
        return notif;
    }

    public void setNotif(String notif) {
        this.notif = notif;
    }

    public Etudiants getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Etudiants etudiants) {
        this.etudiants = etudiants;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDNotif != null ? iDNotif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        if ((this.iDNotif == null && other.iDNotif != null) || (this.iDNotif != null && !this.iDNotif.equals(other.iDNotif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.notif;
    }
    
}
