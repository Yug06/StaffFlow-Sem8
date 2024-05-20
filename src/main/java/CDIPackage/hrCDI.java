package CDIPackage;

import ClientPackage.hrClient;
import EJBPackage.HREJB;
import EJBPackage.userforpayroll;
import Entitypkg.Designationtb;
import Entitypkg.Payrolltb;
import Entitypkg.Usertb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

@Named(value = "hrCDI")
@RequestScoped
public class hrCDI {

    @EJB
    HREJB hrEJB;

    hrClient hc;
    Response rs;

    Usertb u = new Usertb();
    Payrolltb p = new Payrolltb();

    Collection<Usertb> users;
    GenericType<Collection<Usertb>> gusers;

    Collection<Designationtb> deshr;
    GenericType<Collection<Designationtb>> gdeshr;

    Collection<userforpayroll> userForPayrollCollection;
    GenericType<Collection<userforpayroll>> gUserForPayrolls;

    Collection<Payrolltb> payrollForUserCollection;
    GenericType<Collection<Payrolltb>> gpayrollForUser;

    Collection<Payrolltb> payrollCollection;
    GenericType<Collection<Payrolltb>> gpayroll;

    
    String designationID;
    Integer selectedUserID;
    String selectedName;

    public hrCDI() {
        hc = new hrClient();
        users = new ArrayList<>();
        gusers = new GenericType<Collection<Usertb>>() {
        };

        deshr = new ArrayList<>();
        gdeshr = new GenericType<Collection<Designationtb>>() {
        };

        userForPayrollCollection = new ArrayList<>();
        gUserForPayrolls = new GenericType<Collection<userforpayroll>>() {
        };

        payrollForUserCollection = new ArrayList<>();
        gpayrollForUser = new GenericType<Collection<Payrolltb>>() {
        };
        
        payrollCollection = new ArrayList<>();
        gpayroll = new GenericType<Collection<Payrolltb>>() {
        };

    }

    public Usertb getU() {
        return u;
    }

    public void setU(Usertb u) {
        this.u = u;
    }

    public Payrolltb getP() {
        return p;
    }

    public void setP(Payrolltb p) {
        this.p = p;
    }

    public Collection<Usertb> getUsers() {
        rs = hc.displayUserList(Response.class);
        users = rs.readEntity(gusers);
        return users;
    }

    public void setUsers(Collection<Usertb> users) {
        this.users = users;
    }

    public Collection<Designationtb> getDeshr() {
        rs = hc.getDesignationsforHR(Response.class);
        deshr = rs.readEntity(gdeshr);
        return deshr;
    }

    public void setDeshr(Collection<Designationtb> deshr) {
        this.deshr = deshr;
    }

    public String getDesignationID() {
        return designationID;
    }

    public void setDesignationID(String designationID) {
        this.designationID = designationID;
    }

    public Integer getSelectedUserID() {
        return selectedUserID;
    }

    public void setSelectedUserID(Integer selectedUserID) {
        this.selectedUserID = selectedUserID;
    }

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

    public String addUser() {
        String contactNo = String.valueOf(u.getContactNo());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String joinDate = dateFormat.format(u.getJoinDate());
        String dob = dateFormat.format(u.getDob());

        hc.addUser(u.getName(), u.getEmail(), u.getPassword(), contactNo, joinDate, u.getAddress(), dob, designationID);

        return "showUser.jsf";
    }

    public String deleteUser(Integer uid) {
        hc.deleteUser(uid);
        return "showUser.jsf";
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public Collection<userforpayroll> getUserForPayrollCollection() {
        rs = hc.getUsersWithPayrollStatus(Response.class, getCurrentDate());
        userForPayrollCollection = rs.readEntity(gUserForPayrolls);
        return userForPayrollCollection;
    }

    public void setUserForPayrollCollection(Collection<userforpayroll> userForPayrollCollection) {
        this.userForPayrollCollection = userForPayrollCollection;
    }

    public String redirectToAddPayroll(Integer userID, String name) {
        this.selectedUserID = userID;
        this.selectedName = name;
        return "addPayroll.jsf";
    }

    public String addPayroll() {
        hc.addPayroll(selectedUserID.toString(), p.getBasicSalary().toString(), p.getBonus().toString(), p.getDeductions().toString(), getCurrentDate());
        return "showUserforPayroll.jsf?faces-redirect=true";
    }

    public String redirectToUpdatePayroll(Integer userID) {

        p = hc.getMostRecentPayrollRecordForUser(Payrolltb.class, userID.toString());
        if (p == null) {
            // Handle the case where no payroll record is found
            System.out.println("No payroll record found");
        }
        return "updatePayroll.jsf"; // Make sure you have an updatePayroll.jsf page

    }

    public String updatePayroll() {
        hc.updatePayrollRecord(p.getId().toString(), p.getBasicSalary().toString(), p.getBonus().toString(), p.getDeductions().toString(), getCurrentDate());
        return "showUserforPayroll.jsf?faces-redirect=true";
    }

    public String redirectToViewPayroll(Integer userID,String name) {
        this.selectedUserID = userID;
this.selectedName = name;
        return "PayrollforUser.jsf";
    }

//     public String redirectToUpdatePayroll(Integer userID) {
//        this.selectedUserID = userID;
//        fetchPayrollDataForUser();
//        return "updatePayroll.jsf"; // Make sure you have an updatePayroll.jsf page
//    }

    public Collection<Payrolltb> getPayrollForUserCollection() {
        rs = hc.getPayrollRecordsForUser(Response.class, selectedUserID.toString());
        payrollForUserCollection = rs.readEntity(gpayrollForUser);
        return payrollForUserCollection;
    }

    public void setPayrollForUserCollection(Collection<Payrolltb> payrollForUserCollection) {
        this.payrollForUserCollection = payrollForUserCollection;
    }

    public Collection<Payrolltb> getPayrollCollection() {
        rs = hc.getAllPayrollRecords(Response.class);
        payrollCollection = rs.readEntity(gpayroll);
        return payrollCollection;
    }

    public void setPayrollCollection(Collection<Payrolltb> payrollCollection) {
        this.payrollCollection = payrollCollection;
    }
    
    
}
