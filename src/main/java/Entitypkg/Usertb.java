/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "usertb")
@NamedQueries({
    @NamedQuery(name = "Usertb.findAll", query = "SELECT u FROM Usertb u"),
    @NamedQuery(name = "Usertb.findByUserID", query = "SELECT u FROM Usertb u WHERE u.userID = :userID"),
    @NamedQuery(name = "Usertb.findByName", query = "SELECT u FROM Usertb u WHERE u.name = :name"),
    @NamedQuery(name = "Usertb.findByEmail", query = "SELECT u FROM Usertb u WHERE u.email = :email"),
    @NamedQuery(name = "Usertb.findByPassword", query = "SELECT u FROM Usertb u WHERE u.password = :password"),
    @NamedQuery(name = "Usertb.findByContactNo", query = "SELECT u FROM Usertb u WHERE u.contactNo = :contactNo"),
    @NamedQuery(name = "Usertb.findByJoinDate", query = "SELECT u FROM Usertb u WHERE u.joinDate = :joinDate"),
    @NamedQuery(name = "Usertb.findByAddress", query = "SELECT u FROM Usertb u WHERE u.address = :address"),
    @NamedQuery(name = "Usertb.findByDob", query = "SELECT u FROM Usertb u WHERE u.dob = :dob")})
public class Usertb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userID")
    private Integer userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contactNo")
    private long contactNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "joinDate")
    @Temporal(TemporalType.DATE)
    private Date joinDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Performanceevaltb> performanceevaltbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluatorID")
    private Collection<Performanceevaltb> performanceevaltbCollection1;
    @JoinColumn(name = "designationID", referencedColumnName = "designationID")
    @ManyToOne(optional = false)
    private Designationtb designationID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Salarytb> salarytbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Userprojecttb> userprojecttbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Employeefeedback> employeefeedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Payrolltb> payrolltbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Attendancetb> attendancetbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Leavetb> leavetbCollection;
    @OneToMany(mappedBy = "actionBy")
    private Collection<Leavetb> leavetbCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedBy")
    private Collection<Tasktb> tasktbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignedTo")
    private Collection<Tasktb> tasktbCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Projecttb> projecttbCollection;

    public Usertb() {
    }

    public Usertb(Integer userID) {
        this.userID = userID;
    }

    public Usertb(Integer userID, String name, String email, String password, long contactNo, Date joinDate, String address, Date dob) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.joinDate = joinDate;
        this.address = address;
        this.dob = dob;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @JsonbTransient
    public Collection<Performanceevaltb> getPerformanceevaltbCollection() {
        return performanceevaltbCollection;
    }

    @JsonbTransient
    public void setPerformanceevaltbCollection(Collection<Performanceevaltb> performanceevaltbCollection) {
        this.performanceevaltbCollection = performanceevaltbCollection;
    }

    @JsonbTransient
    public Collection<Performanceevaltb> getPerformanceevaltbCollection1() {
        return performanceevaltbCollection1;
    }

    @JsonbTransient
    public void setPerformanceevaltbCollection1(Collection<Performanceevaltb> performanceevaltbCollection1) {
        this.performanceevaltbCollection1 = performanceevaltbCollection1;
    }

    public Designationtb getDesignationID() {
        return designationID;
    }

    public void setDesignationID(Designationtb designationID) {
        this.designationID = designationID;
    }

    @JsonbTransient
    public Collection<Salarytb> getSalarytbCollection() {
        return salarytbCollection;
    }

    @JsonbTransient
    public void setSalarytbCollection(Collection<Salarytb> salarytbCollection) {
        this.salarytbCollection = salarytbCollection;
    }

    @JsonbTransient
    public Collection<Userprojecttb> getUserprojecttbCollection() {
        return userprojecttbCollection;
    }

    @JsonbTransient
    public void setUserprojecttbCollection(Collection<Userprojecttb> userprojecttbCollection) {
        this.userprojecttbCollection = userprojecttbCollection;
    }

    @JsonbTransient
    public Collection<Employeefeedback> getEmployeefeedbackCollection() {
        return employeefeedbackCollection;
    }

    @JsonbTransient
    public void setEmployeefeedbackCollection(Collection<Employeefeedback> employeefeedbackCollection) {
        this.employeefeedbackCollection = employeefeedbackCollection;
    }

    @JsonbTransient
    public Collection<Payrolltb> getPayrolltbCollection() {
        return payrolltbCollection;
    }

    @JsonbTransient
    public void setPayrolltbCollection(Collection<Payrolltb> payrolltbCollection) {
        this.payrolltbCollection = payrolltbCollection;
    }

    @JsonbTransient
    public Collection<Attendancetb> getAttendancetbCollection() {
        return attendancetbCollection;
    }

    @JsonbTransient
    public void setAttendancetbCollection(Collection<Attendancetb> attendancetbCollection) {
        this.attendancetbCollection = attendancetbCollection;
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
    public Collection<Leavetb> getLeavetbCollection1() {
        return leavetbCollection1;
    }

    @JsonbTransient
    public void setLeavetbCollection1(Collection<Leavetb> leavetbCollection1) {
        this.leavetbCollection1 = leavetbCollection1;
    }

    @JsonbTransient
    public Collection<Tasktb> getTasktbCollection() {
        return tasktbCollection;
    }

    @JsonbTransient
    public void setTasktbCollection(Collection<Tasktb> tasktbCollection) {
        this.tasktbCollection = tasktbCollection;
    }

    @JsonbTransient
    public Collection<Tasktb> getTasktbCollection1() {
        return tasktbCollection1;
    }

    @JsonbTransient
    public void setTasktbCollection1(Collection<Tasktb> tasktbCollection1) {
        this.tasktbCollection1 = tasktbCollection1;
    }

    @JsonbTransient
    public Collection<Projecttb> getProjecttbCollection() {
        return projecttbCollection;
    }

    @JsonbTransient
    public void setProjecttbCollection(Collection<Projecttb> projecttbCollection) {
        this.projecttbCollection = projecttbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertb)) {
            return false;
        }
        Usertb other = (Usertb) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Usertb[ userID=" + userID + " ]";
    }
    
}
