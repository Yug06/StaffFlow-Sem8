/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import Entitypkg.Attendancetb;
import Entitypkg.Designationtb;
import Entitypkg.Payrolltb;
import Entitypkg.Salarytb;
import Entitypkg.Usertb;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
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
public class HREJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    Pbkdf2PasswordHashImpl pb;
    //Designation
    //Display

    public Collection<Designationtb> showDesignation() {
        return em.createNamedQuery("Designationtb.findAll").getResultList();
    }

    //Display except HR and Super Admin
    public Collection<Designationtb> getDesignationsforHR() {
        return em.createQuery("SELECT d FROM Designationtb d WHERE d.designationID NOT IN (1,2)", Designationtb.class).getResultList();
    }

    public Collection<Usertb> displayUserList() {
        return em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1,2)", Usertb.class).getResultList();
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
    public Collection<Usertb> getUserByIdforUpdate(Integer userID) {
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

    //Add Payroll
    public void addPayroll(Integer userID, BigDecimal basic_salary, BigDecimal bonus, BigDecimal deductions, Date effectiDate) {
        Payrolltb pr = new Payrolltb();
        Usertb user = em.find(Usertb.class, userID);
        if (user != null) {
            pr.setUserId(user);
            pr.setBasicSalary(basic_salary);
            pr.setBonus(bonus);
            pr.setDeductions(deductions);
            pr.setEffectiveDate(effectiDate);

            // Calculate final amount
            BigDecimal finalAmount = basic_salary.add(bonus).subtract(deductions);
            pr.setFinalAmount(finalAmount);

            em.persist(pr);
        } else {
            System.out.println("User not found");
        }
    }

    public boolean isPayrollRecordExistsForMonth(Integer userID, Date effectiveDate) {
        // Get the start and end dates of the month for the specified effectiveDate
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(effectiveDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = calendar.getTime();

        // Query the database to check if there's a payroll record for the user within the specified month
        Long count = (Long) em.createQuery(
                "SELECT COUNT(p.id) FROM Payrolltb p "
                + "WHERE p.userId.userID = :userID "
                + "AND p.effectiveDate BETWEEN :startDate AND :endDate")
                .setParameter("userID", userID)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getSingleResult();

        return count > 0;
    }

    public Collection<Payrolltb> getPayrollRecordsForUser(Integer userID) {
        return em.createQuery(
                "SELECT p FROM Payrolltb p "
                + "WHERE p.userId.userID = :userID", Payrolltb.class)
                .setParameter("userID", userID)
                .getResultList();
    }

    public Collection<userforpayroll> displayUserListforPayroll(Date dt) {
        Collection<userforpayroll> cu = new ArrayList<userforpayroll>();
        Collection<Usertb> u = em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1,2)", Usertb.class).getResultList();
//        return em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1,2)", Usertb.class).getResultList();
        u.forEach((element) -> {
            userforpayroll up = new userforpayroll();
            up.userID = element.getUserID();
            up.name = element.getName();
            up.email = element.getEmail();
            up.designation = element.getDesignationID().getType();
            up.isExist = isPayrollRecordExistsForMonth(up.userID, dt);
            cu.add(up);
        });
        return cu;
    }

    public Collection<userforattendance> displayUserListforAttendance(Date dt) {
        Collection<userforattendance> cu = new ArrayList<userforattendance>();
        Collection<Usertb> u = em.createQuery("SELECT u FROM Usertb u WHERE u.designationID.designationID NOT IN (1,2)", Usertb.class).getResultList();
        Collection<Attendancetb> a = em.createQuery("SELECT a FROM Attendancetb a WHERE a.date = :date", Attendancetb.class)
                .setParameter("date", dt)
                .getResultList();
        u.forEach((element) -> {
            userforattendance ua = new userforattendance();
            ua.userID = element.getUserID();
            ua.name = element.getName();
            ua.email = element.getEmail();
            ua.designation = element.getDesignationID().getType();
           
            ua.isPresent = false;

            for (Attendancetb attendance : a) {
                if (attendance.getUserID().getUserID().equals(ua.userID)) {
                    ua.isPresent = attendance.getAttendance();
                }
            }

            cu.add(ua);
        });
        return cu;
    }

// Update Payroll Record
    public void updatePayrollRecord(Integer payrollID, BigDecimal basic_salary, BigDecimal bonus, BigDecimal deductions, Date effectiDate) {
        Payrolltb pr = em.find(Payrolltb.class, payrollID);
        if (pr != null) {
            pr.setBasicSalary(basic_salary);
            pr.setBonus(bonus);
            pr.setDeductions(deductions);
            pr.setEffectiveDate(effectiDate);

            // Calculate final amount
            BigDecimal finalAmount = basic_salary.add(bonus).subtract(deductions);
            pr.setFinalAmount(finalAmount);

            em.merge(pr);
        } else {
            System.out.println("Payroll record not found");
        }
    }

// Delete Payroll Record
    public void deletePayrollRecord(Integer payrollID) {
        Payrolltb pr = em.find(Payrolltb.class, payrollID);
        if (pr != null) {
            em.remove(pr);
        } else {
            System.out.println("Payroll record not found");
        }
    }

// Retrieve All Payroll Records
    public Collection<Payrolltb> getAllPayrollRecords() {
        return em.createQuery(
                "SELECT p FROM Payrolltb p", Payrolltb.class)
                .getResultList();
    }

    // Retrieve Payroll Records for a Specific Month
    public Collection<Payrolltb> getPayrollRecordsForMonth(int month, int year) {
        return em.createQuery(
                "SELECT p FROM Payrolltb p WHERE FUNCTION('MONTH', p.effectiveDate) = :month AND FUNCTION('YEAR', p.effectiveDate) = :year", Payrolltb.class)
                .setParameter("month", month)
                .setParameter("year", year)
                .getResultList();
    }

    public Payrolltb getMostRecentPayrollRecordForUser(Integer userID) {
        TypedQuery<Payrolltb> query = em.createQuery(
                "SELECT p FROM Payrolltb p WHERE p.userId.userID = :userID ORDER BY p.effectiveDate DESC", Payrolltb.class);
        query.setParameter("userID", userID);
        query.setMaxResults(1); // Limit to the most recent record

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            // Handle case where no result is found
            return null;
        }
    }

    //Attendance

     public Collection<Attendancetb> getAllAttendancetbsRecords() {
        return em.createQuery(
                "SELECT a FROM Attendancetb a", Attendancetb.class)
                .getResultList();
    }
    
    public void recordAttendance(Integer userId, Date date, Boolean status) {
        Usertb user = em.find(Usertb.class, userId);
        if (user != null) {
            Attendancetb existingAttendance = em.createQuery("SELECT a FROM Attendancetb a WHERE a.userID = :user AND a.date = :date", Attendancetb.class)
                    .setParameter("user", user)
                    .setParameter("date", date)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (existingAttendance != null) {
                // Update existing attendance record
                if(existingAttendance.getAttendance()!=status){
                existingAttendance.setAttendance(status);
                em.merge(existingAttendance);
                }
            } else {
                // Add new attendance record
                Attendancetb newAttendance = new Attendancetb();
                newAttendance.setUserID(user);
                newAttendance.setDate(date);
                newAttendance.setAttendance(status);
                em.persist(newAttendance);
            }
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
