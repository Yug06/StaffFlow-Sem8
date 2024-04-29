/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "payrolltb")
@NamedQueries({
    @NamedQuery(name = "Payrolltb.findAll", query = "SELECT p FROM Payrolltb p"),
    @NamedQuery(name = "Payrolltb.findById", query = "SELECT p FROM Payrolltb p WHERE p.id = :id"),
    @NamedQuery(name = "Payrolltb.findByBasicSalary", query = "SELECT p FROM Payrolltb p WHERE p.basicSalary = :basicSalary"),
    @NamedQuery(name = "Payrolltb.findByBonus", query = "SELECT p FROM Payrolltb p WHERE p.bonus = :bonus"),
    @NamedQuery(name = "Payrolltb.findByDeductions", query = "SELECT p FROM Payrolltb p WHERE p.deductions = :deductions"),
    @NamedQuery(name = "Payrolltb.findByFinalAmount", query = "SELECT p FROM Payrolltb p WHERE p.finalAmount = :finalAmount"),
    @NamedQuery(name = "Payrolltb.findByEffectiveDate", query = "SELECT p FROM Payrolltb p WHERE p.effectiveDate = :effectiveDate")})
public class Payrolltb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "basic_salary")
    private BigDecimal basicSalary;
    @Column(name = "bonus")
    private BigDecimal bonus;
    @Column(name = "deductions")
    private BigDecimal deductions;
    @Column(name = "final_amount")
    private BigDecimal finalAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "effective_date")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;
    @JoinColumn(name = "userId", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userId;

    public Payrolltb() {
    }

    public Payrolltb(Integer id) {
        this.id = id;
    }

    public Payrolltb(Integer id, BigDecimal basicSalary, Date effectiveDate) {
        this.id = id;
        this.basicSalary = basicSalary;
        this.effectiveDate = effectiveDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Usertb getUserId() {
        return userId;
    }

    public void setUserId(Usertb userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payrolltb)) {
            return false;
        }
        Payrolltb other = (Payrolltb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Payrolltb[ id=" + id + " ]";
    }
    
}
