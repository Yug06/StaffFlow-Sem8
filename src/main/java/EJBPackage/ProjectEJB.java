/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Projecttb;
import Entitypkg.Usertb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Foram
 */
@Stateless
public class ProjectEJB {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
        //display all project
    public Collection<Projecttb> displayProjects()
    {
        return em.createNamedQuery("Projecttb.findAll").getResultList();
    }
    
    //insert
    public void addProject(Integer userID, String title, String description, Date startDate, Date endDate)
    {
        Usertb u=(Usertb) em.find(Usertb.class, userID);
        Projecttb proj = new Projecttb();
        proj.setUserID(u);
        proj.setTitle(title);
        proj.setDescription(description);
        proj.setStartDate(startDate);
        proj.setEndDate(endDate);
        em.persist(proj);
    }
    
    
    public Collection<Projecttb> getProjectID(Integer projectID)
    {
        return em.createNamedQuery("Projecttb.findByProjectID").setParameter("projectID", projectID).getResultList();
    }
    
    //update
    public void updateProject(Integer projectID, Integer userID, String title, String description, Date startDate, Date endDate)
    {
        Projecttb p=(Projecttb) em.find(Projecttb.class, projectID);
        Usertb u=(Usertb) em.find(Usertb.class, userID);
        p.setUserID(u);
        p.setTitle(title);
        p.setDescription(description);
        p.setStartDate(startDate);
        p.setEndDate(endDate);
        em.merge(p);
    }
    
    //delete
    public void deleteProject(Integer projectID)
    {
        Projecttb p=(Projecttb) em.find(Projecttb.class, projectID);
        em.remove(p);
    }
    
    //search by title
    public Collection<Projecttb> getProjectTitle(String title)
    {
        return em.createNamedQuery("Projecttb.findByTitle").setParameter("title", title).getResultList();
    }
    
    
    //Hello
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
