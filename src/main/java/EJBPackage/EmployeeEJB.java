/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Employeefeedback;
import Entitypkg.Leavetb;
import Entitypkg.Statustb;
import Entitypkg.Usertb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yug
 */
@Stateless
public class EmployeeEJB {
   @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public void giveFeedback(Integer userID,String description, Date date)
    {
        Usertb u=(Usertb) em.find(Usertb.class, userID);
        Employeefeedback ef=new Employeefeedback();
        ef.setUserID(u);
        ef.setDescription(description);
        ef.setDate(new Date());
        em.persist(ef);
    }
    
    public Collection<Employeefeedback> DisplayFeedback()
    {
        return em.createNamedQuery("Employeefeedback.findAll").getResultList();
    }

     //Employee Leave
    public void applyForLeave(Integer userID,Date strtDate,Date endDate,String subject)
    {
        Statustb s = em.find(Statustb.class, 1);
        Usertb u=(Usertb) em.find(Usertb.class, userID);
        Leavetb l=new Leavetb();
        l.setUserID(u);
        l.setStartDate(strtDate);
        l.setEndDate(endDate);
        l.setSubject(subject);
        l.setStatusid(s);
        em.persist(l);
    }
    
     public Collection<Leavetb> showLeaveByUser(Integer userID)
    {
        Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            TypedQuery<Leavetb> query = em.createQuery("SELECT l FROM Leavetb l WHERE l.userID = :uid", Leavetb.class);
            query.setParameter("uid", user);
            return query.getResultList();
        }
        return null;
    }
     
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
