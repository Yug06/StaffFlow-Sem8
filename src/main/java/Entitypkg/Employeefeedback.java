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
@Table(name = "employeefeedback")
@NamedQueries({
    @NamedQuery(name = "Employeefeedback.findAll", query = "SELECT e FROM Employeefeedback e"),
    @NamedQuery(name = "Employeefeedback.findByFeedbackID", query = "SELECT e FROM Employeefeedback e WHERE e.feedbackID = :feedbackID"),
    @NamedQuery(name = "Employeefeedback.findByDescription", query = "SELECT e FROM Employeefeedback e WHERE e.description = :description"),
    @NamedQuery(name = "Employeefeedback.findByDate", query = "SELECT e FROM Employeefeedback e WHERE e.date = :date"),
    @NamedQuery(name = "Employeefeedback.findByOverallExperience", query = "SELECT e FROM Employeefeedback e WHERE e.overallExperience = :overallExperience"),
    @NamedQuery(name = "Employeefeedback.findByJobSatisfaction", query = "SELECT e FROM Employeefeedback e WHERE e.jobSatisfaction = :jobSatisfaction")})
public class Employeefeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedbackID")
    private Integer feedbackID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "overallExperience")
    private String overallExperience;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "jobSatisfaction")
    private String jobSatisfaction;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Employeefeedback() {
    }

    public Employeefeedback(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Employeefeedback(Integer feedbackID, String description, Date date, String overallExperience, String jobSatisfaction) {
        this.feedbackID = feedbackID;
        this.description = description;
        this.date = date;
        this.overallExperience = overallExperience;
        this.jobSatisfaction = jobSatisfaction;
    }

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOverallExperience() {
        return overallExperience;
    }

    public void setOverallExperience(String overallExperience) {
        this.overallExperience = overallExperience;
    }

    public String getJobSatisfaction() {
        return jobSatisfaction;
    }

    public void setJobSatisfaction(String jobSatisfaction) {
        this.jobSatisfaction = jobSatisfaction;
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
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeefeedback)) {
            return false;
        }
        Employeefeedback other = (Employeefeedback) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Employeefeedback[ feedbackID=" + feedbackID + " ]";
    }
    
}
