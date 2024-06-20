/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Employeefeedback;
import Entitypkg.Leavetb;
import Entitypkg.Projecttb;
import Entitypkg.Statustb;
import Entitypkg.Tasktb;
import Entitypkg.Usertb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Yug
 */
@Stateless
public class EmployeeEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    Pbkdf2PasswordHashImpl pb;

    public void giveFeedback(Integer userID, String description, String overallExperience, String jobSatisfaction) {
        Usertb u = (Usertb) em.find(Usertb.class, userID);
        Employeefeedback ef = new Employeefeedback();
        ef.setUserID(u);
        ef.setDescription(description);
        ef.setDate(new Date());
        ef.setOverallExperience(overallExperience);
        ef.setJobSatisfaction(jobSatisfaction);
        em.persist(ef);
    }

    public Collection<Employeefeedback> DisplayFeedback() {
        return em.createNamedQuery("Employeefeedback.findAll").getResultList();
    }

    //Employee Leave
    public void applyForLeave(Integer userID, Date strtDate, Date endDate, String subject) {
        Statustb s = em.find(Statustb.class, 1);
        Usertb u = (Usertb) em.find(Usertb.class, userID);
        Leavetb l = new Leavetb();
        l.setUserID(u);
        l.setStartDate(strtDate);
        l.setEndDate(endDate);
        l.setSubject(subject);
        l.setStatusid(s);
        em.persist(l);
    }

    public Collection<Leavetb> showLeaveByUser(Integer userID) {
        Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            TypedQuery<Leavetb> query = em.createQuery("SELECT l FROM Leavetb l WHERE l.userID = :uid", Leavetb.class);
            query.setParameter("uid", user);
            return query.getResultList();
        }
        return null;
    }

    public Collection<Projecttb> showProjectByEmp(Integer userID) {
        Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            TypedQuery<Projecttb> query = em.createQuery(
                    "SELECT p FROM Projecttb p WHERE p.projectID IN (SELECT up.projectID.projectID FROM Userprojecttb up WHERE up.userID.userID = :userID)",
                    Projecttb.class
            );
            query.setParameter("userID", userID);
            return query.getResultList();
        }
        return null;
    }

    public Collection<Tasktb> getTaskByEmpProj(Integer ProjectID, Integer assignedTo) {
        Projecttb project = em.find(Projecttb.class, ProjectID);
        Usertb assTo = em.find(Usertb.class, assignedTo);
        System.out.println(project);
        System.out.println(assignedTo);
        return em.createQuery("SELECT t FROM Tasktb t WHERE t.projectID = :projectID AND t.assignedTo = :assignedTo ORDER BY t.statusid", Tasktb.class)
                .setParameter("projectID", project)
                .setParameter("assignedTo", assTo)
                .getResultList();

    }

    public void completeTask(Integer taskID) {
        Tasktb t = em.find(Tasktb.class, taskID);
        Statustb s = em.find(Statustb.class, 2);
        t.setStatusid(s);
        em.merge(t);
    }

    public void rejectTask(Integer taskID) {
        Tasktb t = em.find(Tasktb.class, taskID);
        Statustb s = em.find(Statustb.class, 3);
        t.setStatusid(s);
        em.merge(t);
    }

    public Collection<Usertb> ShowUserProfile(Integer userID) {
        return em.createNamedQuery("Usertb.findByUserID").setParameter("userID", userID).getResultList();
    }

    public Usertb ShowUserforUpd(Integer userID) {
        return em.createNamedQuery("Usertb.findByUserID",Usertb.class).setParameter("userID", userID).getSingleResult();
    }

    public void changePassword(String password, Integer userID) {
        pb = new Pbkdf2PasswordHashImpl();
        String enc = pb.generate(password.toCharArray());

        Usertb u = em.find(Usertb.class, userID);
        u.setPassword(enc);
        em.merge(u);
    }

    public void updateUserProfile(Integer userID, String name, Integer contactNo, String address) {
        Usertb u = em.find(Usertb.class, userID);
        u.setName(name);
        u.setContactNo(contactNo);
        u.setAddress(address);
        em.merge(u);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
