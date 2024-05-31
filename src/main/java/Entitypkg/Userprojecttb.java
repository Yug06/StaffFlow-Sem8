/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Yug
 */
@Entity
@Table(name = "userprojecttb")
@NamedQueries({
    @NamedQuery(name = "Userprojecttb.findAll", query = "SELECT u FROM Userprojecttb u"),
    @NamedQuery(name = "Userprojecttb.findByUpID", query = "SELECT u FROM Userprojecttb u WHERE u.upID = :upID")})
public class Userprojecttb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "upID")
    private Integer upID;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Projecttb projectID;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Userprojecttb() {
    }

    public Userprojecttb(Integer upID) {
        this.upID = upID;
    }

    public Integer getUpID() {
        return upID;
    }

    public void setUpID(Integer upID) {
        this.upID = upID;
    }

    public Projecttb getProjectID() {
        return projectID;
    }

    public void setProjectID(Projecttb projectID) {
        this.projectID = projectID;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (upID != null ? upID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userprojecttb)) {
            return false;
        }
        Userprojecttb other = (Userprojecttb) object;
        if ((this.upID == null && other.upID != null) || (this.upID != null && !this.upID.equals(other.upID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Userprojecttb[ upID=" + upID + " ]";
    }
    
}
