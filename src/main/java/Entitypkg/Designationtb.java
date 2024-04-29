/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Yug
 */
@Entity
@Table(name = "designationtb")
@NamedQueries({
    @NamedQuery(name = "Designationtb.findAll", query = "SELECT d FROM Designationtb d"),
    @NamedQuery(name = "Designationtb.findByDesignationID", query = "SELECT d FROM Designationtb d WHERE d.designationID = :designationID"),
    @NamedQuery(name = "Designationtb.findByType", query = "SELECT d FROM Designationtb d WHERE d.type = :type")})
public class Designationtb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "designationID")
    private Integer designationID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "designationID")
    private Collection<Usertb> usertbCollection;

    public Designationtb() {
    }

    public Designationtb(Integer designationID) {
        this.designationID = designationID;
    }

    public Designationtb(Integer designationID, String type) {
        this.designationID = designationID;
        this.type = type;
    }

    public Integer getDesignationID() {
        return designationID;
    }

    public void setDesignationID(Integer designationID) {
        this.designationID = designationID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Usertb> getUsertbCollection() {
        return usertbCollection;
    }

    public void setUsertbCollection(Collection<Usertb> usertbCollection) {
        this.usertbCollection = usertbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (designationID != null ? designationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Designationtb)) {
            return false;
        }
        Designationtb other = (Designationtb) object;
        if ((this.designationID == null && other.designationID != null) || (this.designationID != null && !this.designationID.equals(other.designationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Designationtb[ designationID=" + designationID + " ]";
    }
    
}
