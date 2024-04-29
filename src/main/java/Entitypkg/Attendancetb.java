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

/**
 *
 * @author Yug
 */
@Entity
@Table(name = "attendancetb")
@NamedQueries({
    @NamedQuery(name = "Attendancetb.findAll", query = "SELECT a FROM Attendancetb a"),
    @NamedQuery(name = "Attendancetb.findByAttendanceID", query = "SELECT a FROM Attendancetb a WHERE a.attendanceID = :attendanceID"),
    @NamedQuery(name = "Attendancetb.findByDate", query = "SELECT a FROM Attendancetb a WHERE a.date = :date"),
    @NamedQuery(name = "Attendancetb.findByAttendance", query = "SELECT a FROM Attendancetb a WHERE a.attendance = :attendance")})
public class Attendancetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "attendanceID")
    private Integer attendanceID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attendance")
    private boolean attendance;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Attendancetb() {
    }

    public Attendancetb(Integer attendanceID) {
        this.attendanceID = attendanceID;
    }

    public Attendancetb(Integer attendanceID, Date date, boolean attendance) {
        this.attendanceID = attendanceID;
        this.date = date;
        this.attendance = attendance;
    }

    public Integer getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(Integer attendanceID) {
        this.attendanceID = attendanceID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
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
        hash += (attendanceID != null ? attendanceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendancetb)) {
            return false;
        }
        Attendancetb other = (Attendancetb) object;
        if ((this.attendanceID == null && other.attendanceID != null) || (this.attendanceID != null && !this.attendanceID.equals(other.attendanceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Attendancetb[ attendanceID=" + attendanceID + " ]";
    }
    
}
