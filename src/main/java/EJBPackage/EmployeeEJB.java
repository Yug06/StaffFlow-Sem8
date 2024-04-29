/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Employeefeedback;
import Entitypkg.Usertb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
