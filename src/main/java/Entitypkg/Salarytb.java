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
@Table(name = "salarytb")
@NamedQueries({
    @NamedQuery(name = "Salarytb.findAll", query = "SELECT s FROM Salarytb s"),
    @NamedQuery(name = "Salarytb.findBySalaryID", query = "SELECT s FROM Salarytb s WHERE s.salaryID = :salaryID"),
    @NamedQuery(name = "Salarytb.findByAmount", query = "SELECT s FROM Salarytb s WHERE s.amount = :amount"),
    @NamedQuery(name = "Salarytb.findByEffectiveDate", query = "SELECT s FROM Salarytb s WHERE s.effectiveDate = :effectiveDate")})
public class Salarytb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "salaryID")
    private Integer salaryID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "effectiveDate")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Salarytb() {
    }

    public Salarytb(Integer salaryID) {
        this.salaryID = salaryID;
    }

    public Salarytb(Integer salaryID, double amount, Date effectiveDate) {
        this.salaryID = salaryID;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
    }

    public Integer getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(Integer salaryID) {
        this.salaryID = salaryID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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
        hash += (salaryID != null ? salaryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salarytb)) {
            return false;
        }
        Salarytb other = (Salarytb) object;
        if ((this.salaryID == null && other.salaryID != null) || (this.salaryID != null && !this.salaryID.equals(other.salaryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Salarytb[ salaryID=" + salaryID + " ]";
    }
    
}
