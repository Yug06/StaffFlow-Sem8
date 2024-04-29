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
@Table(name = "tasktb")
@NamedQueries({
    @NamedQuery(name = "Tasktb.findAll", query = "SELECT t FROM Tasktb t"),
    @NamedQuery(name = "Tasktb.findByTaskID", query = "SELECT t FROM Tasktb t WHERE t.taskID = :taskID"),
    @NamedQuery(name = "Tasktb.findBySubject", query = "SELECT t FROM Tasktb t WHERE t.subject = :subject"),
    @NamedQuery(name = "Tasktb.findByAssignDate", query = "SELECT t FROM Tasktb t WHERE t.assignDate = :assignDate"),
    @NamedQuery(name = "Tasktb.findByDueDate", query = "SELECT t FROM Tasktb t WHERE t.dueDate = :dueDate")})
public class Tasktb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskID")
    private Integer taskID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Column(name = "assignDate")
    @Temporal(TemporalType.DATE)
    private Date assignDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Projecttb projectID;
    @JoinColumn(name = "assignedBy", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb assignedBy;
    @JoinColumn(name = "assignedTo", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb assignedTo;

    public Tasktb() {
    }

    public Tasktb(Integer taskID) {
        this.taskID = taskID;
    }

    public Tasktb(Integer taskID, String subject, Date assignDate, Date dueDate) {
        this.taskID = taskID;
        this.subject = subject;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Projecttb getProjectID() {
        return projectID;
    }

    public void setProjectID(Projecttb projectID) {
        this.projectID = projectID;
    }

    public Usertb getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Usertb assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Usertb getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Usertb assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskID != null ? taskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasktb)) {
            return false;
        }
        Tasktb other = (Tasktb) object;
        if ((this.taskID == null && other.taskID != null) || (this.taskID != null && !this.taskID.equals(other.taskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Tasktb[ taskID=" + taskID + " ]";
    }
    
}
