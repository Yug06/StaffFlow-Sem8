/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Designationtb;
import Entitypkg.Salarytb;
import Entitypkg.Usertb;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Yug
 */
@Stateless
public class HREJB {
@PersistenceContext(unitName = "my_persistence_unit")
EntityManager em;

  Pbkdf2PasswordHashImpl pb;
  //Designation
    //Display
    public Collection<Designationtb> showDesignation(){
        return em.createNamedQuery("Designationtb.findAll").getResultList();
    }

    //Display except HR and Super Admin
    public Collection<Designationtb> getDesignationsforHR(){
        return em.createQuery("SELECT d FROM Designationtb d WHERE d.designationID NOT IN (1,2)",Designationtb.class).getResultList();
    }
    
    public Collection<Usertb> displayUserList(){
        return em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1,2)",Usertb.class).getResultList();
    }
    
    //Add User
     public void addUser(String name, String email, String password, Integer contactNo, Date joinDate, String address, Date DOB, Integer designationID) {
        // Check if the selected designation is either Project Manager or Employee
       
          pb = new Pbkdf2PasswordHashImpl();
         String enc = pb.generate(password.toCharArray());
         
        if (designationID.equals(3) || designationID.equals(4)) { // Assuming 3 is Project Manager and 4 is Employee
            Designationtb designation = em.find(Designationtb.class, designationID);
            if (designation != null) {
                Usertb user = new Usertb();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(enc);
                user.setContactNo(contactNo);
                user.setJoinDate(joinDate);
                user.setAddress(address);
                user.setDob(DOB);
                user.setDesignationID(designation);
                em.persist(user);
            } else {
                // Handle case where designation is not found
                System.out.println("Designation not found");
            }
        } else {
            // Handle case where designation is not allowed
            System.out.println("Designation not allowed");
        }
    }
     
     //Delete User
      public void deleteUser(Integer userID) {
        Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            em.remove(user);
        } else {
            // Handle case where user is not found
            System.out.println("User not found");
        }
    }
      
      
      //GetUserById
       public Collection<Usertb> getUserByIdforUpdate(Integer userID){
        return em.createNamedQuery("Usertb.findByUserID").setParameter("userID", userID).getResultList();
    }
      
      //Update User
        public void updateUser(Integer userID, String name, String email, String password, Integer contactNo, Date joinDate, String address, Date DOB) {
         pb = new Pbkdf2PasswordHashImpl();
         String enc = pb.generate(password.toCharArray());
         Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(enc);
            user.setContactNo(contactNo);
            user.setJoinDate(joinDate);
            user.setAddress(address);
            user.setDob(DOB);
            em.merge(user);
        } else {
            // Handle case where user is not found
            System.out.println("User not found");
        }
    }
        
        //Add Salary
         public void addSalary(Integer userID, double amount, Date effectiveDate) {
        Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            Salarytb salary = new Salarytb();
            salary.setUserID(user);
            salary.setAmount(amount);
            salary.setEffectiveDate(effectiveDate);
            em.persist(salary);
        } else {
            // Handle case where user is not found
            System.out.println("User not found");
        }
    }
         
          // Update Salary for a User
    public void updateSalary(Integer salaryID, double amount, Date effectiveDate) {
        Salarytb salary = em.find(Salarytb.class, salaryID);
        if (salary != null) {
            salary.setAmount(amount);
            salary.setEffectiveDate(effectiveDate);
            em.merge(salary);
        } else {
            // Handle case where salary is not found
            System.out.println("Salary not found");
        }
    }
    
     // Delete Salary for a User
    public void deleteSalary(Integer salaryID) {
        Salarytb salary = em.find(Salarytb.class, salaryID);
        if (salary != null) {
            em.remove(salary);
        } else {
            // Handle case where salary is not found
            System.out.println("Salary not found");
        }
    }

    // Display Salary for a User
    public Collection<Salarytb> displaySalary(){
        return em.createNamedQuery("Salarytb.findAll").getResultList();
    }
        
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
