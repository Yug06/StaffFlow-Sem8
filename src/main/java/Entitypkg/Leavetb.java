/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Yug
 */
@Entity
@Table(name = "leavetb")
@NamedQueries({
    @NamedQuery(name = "Leavetb.findAll", query = "SELECT l FROM Leavetb l"),
    @NamedQuery(name = "Leavetb.findByLeaveID", query = "SELECT l FROM Leavetb l WHERE l.leaveID = :leaveID"),
    @NamedQuery(name = "Leavetb.findByStartDate", query = "SELECT l FROM Leavetb l WHERE l.startDate = :startDate"),
    @NamedQuery(name = "Leavetb.findByEndDate", query = "SELECT l FROM Leavetb l WHERE l.endDate = :endDate"),
    @NamedQuery(name = "Leavetb.findByStatus", query = "SELECT l FROM Leavetb l WHERE l.status = :status"),
    @NamedQuery(name = "Leavetb.findBySubject", query = "SELECT l FROM Leavetb l WHERE l.subject = :subject")})
public class Leavetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leaveID")
    private Integer leaveID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "subject")
    private String subject;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Leavetb() {
    }

    public Leavetb(Integer leaveID) {
        this.leaveID = leaveID;
    }

    public Leavetb(Integer leaveID, Date startDate, Date endDate, boolean status, String subject) {
        this.leaveID = leaveID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.subject = subject;
    }

    public Integer getLeaveID() {
        return leaveID;
    }

    public void setLeaveID(Integer leaveID) {
        this.leaveID = leaveID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        hash += (leaveID != null ? leaveID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leavetb)) {
            return false;
        }
        Leavetb other = (Leavetb) object;
        if ((this.leaveID == null && other.leaveID != null) || (this.leaveID != null && !this.leaveID.equals(other.leaveID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Leavetb[ leaveID=" + leaveID + " ]";
    }
    
}
