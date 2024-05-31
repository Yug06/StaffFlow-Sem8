/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
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
@Table(name = "statustb")
@NamedQueries({
    @NamedQuery(name = "Statustb.findAll", query = "SELECT s FROM Statustb s"),
    @NamedQuery(name = "Statustb.findByStatusid", query = "SELECT s FROM Statustb s WHERE s.statusid = :statusid"),
    @NamedQuery(name = "Statustb.findByStatus", query = "SELECT s FROM Statustb s WHERE s.status = :status")})
public class Statustb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "statusid")
    private Integer statusid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusid")
    private Collection<Leavetb> leavetbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusid")
    private Collection<Tasktb> tasktbCollection;

    public Statustb() {
    }

    public Statustb(Integer statusid) {
        this.statusid = statusid;
    }

    public Statustb(Integer statusid, String status) {
        this.statusid = statusid;
        this.status = status;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonbTransient
    public Collection<Leavetb> getLeavetbCollection() {
        return leavetbCollection;
    }

    @JsonbTransient
    public void setLeavetbCollection(Collection<Leavetb> leavetbCollection) {
        this.leavetbCollection = leavetbCollection;
    }

    @JsonbTransient
    public Collection<Tasktb> getTasktbCollection() {
        return tasktbCollection;
    }

    @JsonbTransient
    public void setTasktbCollection(Collection<Tasktb> tasktbCollection) {
        this.tasktbCollection = tasktbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusid != null ? statusid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statustb)) {
            return false;
        }
        Statustb other = (Statustb) object;
        if ((this.statusid == null && other.statusid != null) || (this.statusid != null && !this.statusid.equals(other.statusid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Statustb[ statusid=" + statusid + " ]";
    }

}
