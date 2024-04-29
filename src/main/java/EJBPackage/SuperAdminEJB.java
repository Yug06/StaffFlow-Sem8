/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Designationtb;
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
public class SuperAdminEJB {
@PersistenceContext(unitName = "my_persistence_unit")
EntityManager em;

    //Designation
    //Display
    public Collection<Designationtb> displayDesignation(){
        return em.createNamedQuery("Designationtb.findAll").getResultList();
    }

    //Insert
    public void addDesignation(String type){
        Designationtb dt = new Designationtb();
        dt.setType(type);
        em.persist(dt);
    }
    
    //GetDataById
    public Collection<Designationtb> getDataByIdforUpdate(Integer designationID){
        return em.createNamedQuery("Designationtb.findByDesignationID").setParameter("designationID", designationID).getResultList();
    }
    
    //Update
    public void updateDesignation(Integer designationID, String type){
        Designationtb dt = em.find(Designationtb.class, designationID);
        dt.setType(type);
        em.merge(dt);
    }
    
    //Delete
    public void deleteDesignation(Integer designationID){
        Designationtb dt = em.find(Designationtb.class, designationID);
        em.remove(dt);
    }
    
    
    //Add HR
    public void addHR(String name, String email, String password, Integer contactNo, Date joinDate, String address, Date DOB){
        Designationtb dt = em.find(Designationtb.class, 2);
        Usertb u = new Usertb();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setContactNo(contactNo);
        u.setJoinDate(joinDate);
        u.setAddress(address);
        u.setDob(DOB);
        u.setDesignationID(dt);
        em.persist(u);
    } 
    
    //Delete HR
    public void deleteHR(Integer userID){
        Usertb ut = em.find(Usertb.class, userID);
        em.remove(ut);
    }
    
    //Display HR
    public Collection<Usertb> displayHR(){
        return em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID = :designationID", Usertb.class).setParameter("designationID", 2).getResultList();
    }
    
    //GetDataById
      public Collection<Usertb> getHRByIdforUpdate(Integer userID){
        return em.createNamedQuery("Usertb.findByUserID").setParameter("userID", userID).getResultList();
    }
    
    //Update HR
      public void updateHR(Integer userID,String name, String email, String password, Integer contactNo, Date joinDate, String address, Date DOB){
        Usertb u = em.find(Usertb.class, userID);
        u.setName(name);
        u.setEmail(email);
        u.setPassword(password);
        u.setContactNo(contactNo);
        u.setJoinDate(joinDate);
        u.setAddress(address);
        u.setDob(DOB);
        em.merge(u);
    } 
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
