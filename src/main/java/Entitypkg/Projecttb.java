/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitypkg;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "projecttb")
@NamedQueries({
    @NamedQuery(name = "Projecttb.findAll", query = "SELECT p FROM Projecttb p"),
    @NamedQuery(name = "Projecttb.findByProjectID", query = "SELECT p FROM Projecttb p WHERE p.projectID = :projectID"),
    @NamedQuery(name = "Projecttb.findByTitle", query = "SELECT p FROM Projecttb p WHERE p.title = :title"),
    @NamedQuery(name = "Projecttb.findByDescription", query = "SELECT p FROM Projecttb p WHERE p.description = :description"),
    @NamedQuery(name = "Projecttb.findByStartDate", query = "SELECT p FROM Projecttb p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "Projecttb.findByEndDate", query = "SELECT p FROM Projecttb p WHERE p.endDate = :endDate")})
public class Projecttb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectID")
    private Integer projectID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
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
    @JoinTable(name = "userprojecttb", joinColumns = {
        @JoinColumn(name = "projectID", referencedColumnName = "projectID")}, inverseJoinColumns = {
        @JoinColumn(name = "userID", referencedColumnName = "userID")})
    @ManyToMany
    private Collection<Usertb> usertbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectID")
    private Collection<Tasktb> tasktbCollection;
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Projecttb() {
    }

    public Projecttb(Integer projectID) {
        this.projectID = projectID;
    }

    public Projecttb(Integer projectID, String title, String description, Date startDate, Date endDate) {
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Collection<Usertb> getUsertbCollection() {
        return usertbCollection;
    }

    public void setUsertbCollection(Collection<Usertb> usertbCollection) {
        this.usertbCollection = usertbCollection;
    }

    public Collection<Tasktb> getTasktbCollection() {
        return tasktbCollection;
    }

    public void setTasktbCollection(Collection<Tasktb> tasktbCollection) {
        this.tasktbCollection = tasktbCollection;
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
        hash += (projectID != null ? projectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projecttb)) {
            return false;
        }
        Projecttb other = (Projecttb) object;
        if ((this.projectID == null && other.projectID != null) || (this.projectID != null && !this.projectID.equals(other.projectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitypkg.Projecttb[ projectID=" + projectID + " ]";
    }
    
}
