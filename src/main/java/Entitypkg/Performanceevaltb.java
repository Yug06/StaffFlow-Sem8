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
@Table(name = "performanceevaltb")
@NamedQueries({
    @NamedQuery(name = "Performanceevaltb.findAll", query = "SELECT p FROM Performanceevaltb p"),
    @NamedQuery(name = "Performanceevaltb.findByEvaluationID", query = "SELECT p FROM Performanceevaltb p WHERE p.evaluationID = :evaluationID"),
    @NamedQuery(name = "Performanceevaltb.findByEvaluationDate", query = "SELECT p FROM Performanceevaltb p WHERE p.evaluationDate = :evaluationDate"),
    @NamedQuery(name = "Performanceevaltb.findByPerformanceRating", query = "SELECT p FROM Performanceevaltb p WHERE p.performanceRating = :performanceRating"),
    @NamedQuery(name = "Performanceevaltb.findByFeedback", query = "SELECT p FROM Performanceevaltb p WHERE p.feedback = :feedback")})
public class Performanceevaltb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "evaluationID")
    private Integer evaluationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "evaluationDate")
    @Temporal(TemporalType.DATE)
    private Date evaluationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "performanceRating")
    private double performanceRating;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "feedback")
    private String feedback;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;
    @JoinColumn(name = "evaluatorID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb evaluatorID;

    public Performanceevaltb() {
    }

    public Performanceevaltb(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Performanceevaltb(Integer evaluationID, Date evaluationDate, double performanceRating, String feedback) {
        this.evaluationID = evaluationID;
        this.evaluationDate = evaluationDate;
        this.performanceRating = performanceRating;
        this.feedback = feedback;
    }

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public double getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(double performanceRating) {
        this.performanceRating = performanceRating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    public Usertb getEvaluatorID() {
        return evaluatorID;
    }

    public void setEvaluatorID(Usertb evaluatorID) {
        this.evaluatorID = evaluatorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationID != null ? evaluationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Performanceevaltb)) {
            return false;
        }
        Performanceevaltb other = (Performanceevaltb) object;
        if ((this.evaluationID == null && other.evaluationID != null) || (this.evaluationID != null && !this.evaluationID.equals(other.evaluationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Performanceevaltb[ evaluationID=" + evaluationID + " ]";
    }
    
}
