/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Projecttb;
import Entitypkg.Statustb;
import Entitypkg.Tasktb;
import Entitypkg.Userprojecttb;
import Entitypkg.Usertb;
import java.util.Calendar;
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
    
    public Collection<Usertb> displayEmp(){
       return em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1,2,3)", Usertb.class).getResultList();

    }
    
    public Collection<Usertb> getEmployeesNotInProject(Integer projectId) {
    return em.createQuery(
        "SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1, 2, 3) AND u.userID NOT IN (SELECT up.userID.userID FROM Userprojecttb up WHERE up.projectID.projectID = :projectId)",
        Usertb.class)
        .setParameter("projectId", projectId)
        .getResultList();
}

    
    //project for pm
    public Collection<Projecttb> displayProjecttoPM(Integer userID){
        Usertb u = em.find(Usertb.class, userID);
        return em.createQuery("SELECT p FROM Projecttb p WHERE p.userID = :userID ORDER BY CASE WHEN p.endDate < CURRENT_DATE THEN 1 ELSE 0 END, p.endDate ASC", Projecttb.class)
            .setParameter("userID", u)
            .getResultList();  }
    
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
    
    //add employee
    public void addEmployeetoProject(Integer ProjectID, Integer userID){
        Projecttb p = em.find(Projecttb.class, ProjectID);
        Usertb u = em.find(Usertb.class, userID);
        
        Userprojecttb up = new Userprojecttb();
        up.setProjectID(p);
        up.setUserID(u);
        em.persist(up);
    }
    
    //get emp by proj
    public Collection<Userprojecttb> getEmpbyProj(Integer ProjectID){
        Projecttb p = em.find(Projecttb.class, ProjectID);
        return em.createQuery("SELECT u FROM Userprojecttb u WHERE u.projectID = :projectID",Userprojecttb.class).setParameter("projectID", p).getResultList();
    }
    
    public Collection<Tasktb> getTaskByEmpProj(Integer ProjectID, Integer assignedTo){
         Projecttb project = em.find(Projecttb.class, ProjectID);
        Usertb assTo = em.find(Usertb.class, assignedTo);
        System.out.println(project);
        System.out.println(assignedTo);
         return em.createQuery("SELECT t FROM Tasktb t WHERE t.projectID = :projectID AND t.assignedTo = :assignedTo", Tasktb.class)
                .setParameter("projectID", project)
                .setParameter("assignedTo", assTo)
                .getResultList();
        
    }
    
    //add task to proj
     public void addTaskToProject(Integer assignedBy, Integer assignedTo, String subject, String description, Date dueDate,Integer projectId) {
        // Find the project
         System.out.println("hiii");
          System.out.println(projectId);
         System.out.println(assignedBy);
         System.out.println(assignedTo);

        Projecttb project = em.find(Projecttb.class, projectId);
        Usertb assBy = em.find(Usertb.class, assignedBy);
        Usertb assTo = em.find(Usertb.class, assignedTo);
          Statustb s = em.find(Statustb.class, 1);
        // Check if due date is less than current date
       
         System.out.println(project);
         System.out.println(assBy);
         System.out.println(assTo);

        
           if (dueDate.before(new Date())) {
        // Set the due date to tomorrow's date
        
        dueDate = new Date();
        }

        
        // Calculate the new due date if it's after the project's end date
        Date projectEndDate = project.getEndDate();
        if (dueDate.after(projectEndDate)) {
            // Set the due date to one day before project's end date
            dueDate = new Date(projectEndDate.getTime() - (1000 * 60 * 60 * 24)); // Subtract one day in milliseconds
        }
        
        // Create the task
        Tasktb task = new Tasktb();
       
        task.setAssignedBy(assBy);
        task.setAssignedTo(assTo);
        task.setSubject(subject);
        task.setDescription(description);
        task.setAssignDate(new Date()); // Assuming current date is the assign date
        task.setDueDate(dueDate);
        task.setStatusid(s);
        task.setProjectID(project);
        // Persist the task
        em.persist(task);
    }
     
     
     
     
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
