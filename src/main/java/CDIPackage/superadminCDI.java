/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import ClientPackage.RestClient;
import Entitypkg.Usertb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author Yug
 */
@Named(value = "superadminCDI")
@RequestScoped
public class superadminCDI {

    RestClient rc;
    
    Usertb u = new Usertb();
    
    Collection<Usertb> userCollection;
    GenericType<Collection<Usertb>> gusers;
    
    Collection<Usertb> pmCollection;
    GenericType<Collection<Usertb>> gpms;
    
    Collection<Usertb> empCollection;
    GenericType<Collection<Usertb>> gemps;
    
    Response rs;
    
    Integer HRCount;
    Integer PMCount;
    Integer EmployeeCount;
    /**
     * Creates a new instance of superadminCDI
     */
    public superadminCDI() {
        rc = new RestClient();
        
        userCollection = new ArrayList<>();
        gusers = new GenericType<Collection<Usertb>>(){};
        
        pmCollection = new ArrayList<>();
        gpms = new GenericType<Collection<Usertb>>(){};
        
        empCollection = new ArrayList<>();
        gemps = new GenericType<Collection<Usertb>>(){};
    }

    public Usertb getU() {
        return u;
    }

    public void setU(Usertb u) {
        this.u = u;
    }

    public Collection<Usertb> getUserCollection() {
        rs = rc.displayHR(Response.class);
        userCollection = rs.readEntity(gusers);
        return userCollection;
    }

    public void setUserCollection(Collection<Usertb> userCollection) {
        this.userCollection = userCollection;
    }

     public Collection<Usertb> getPmCollection() {
         rs = rc.displayPM(Response.class);
         pmCollection = rs.readEntity(gpms);
        return pmCollection;
    }

    public void setPmCollection(Collection<Usertb> pmCollection) {
        this.pmCollection = pmCollection;
    }

    public Collection<Usertb> getEmpCollection() {
        rs = rc.displayEmployee(Response.class);
        empCollection = rs.readEntity(gemps);
        return empCollection;
    }

    public void setEmpCollection(Collection<Usertb> empCollection) {
        this.empCollection = empCollection;
    }

    
    public String addHR(){
        
        String contactNo = String.valueOf(u.getContactNo());
         
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String joinDate = dateFormat.format(u.getJoinDate());
        String dob = dateFormat.format(u.getDob());
        rc.addHR(u.getName(), u.getEmail(), u.getPassword(), contactNo, joinDate, u.getAddress(), dob);
        
        return "ShowHR.jsf";
    }
    
    public GenericType<Collection<Usertb>> getGusers() {
        return gusers;
    }

    public void setGusers(GenericType<Collection<Usertb>> gusers) {
        this.gusers = gusers;
    }
    
 public String deleteHR(Integer uid){
        rc.deleteHR(uid);
        return "ShowHR.jsf";
    }

    public Integer getHRCount() {
        rs = rc.getHRCount(Response.class);
        HRCount = rs.readEntity(Integer.class);
        return HRCount;
    }

    public void setHRCount(Integer HRCount) {
        this.HRCount = HRCount;
    }

    public Integer getPMCount() {
        rs = rc.getPMcount(Response.class);
        PMCount = rs.readEntity(Integer.class);
        return PMCount;
    }

    public void setPMCount(Integer PMCount) {
        this.PMCount = PMCount;
    }

    public Integer getEmployeeCount() {
        rs = rc.getEmployeecount(Response.class);
        EmployeeCount = rs.readEntity(Integer.class);
        return EmployeeCount;
    }

    public void setEmployeeCount(Integer EmployeeCount) {
        this.EmployeeCount = EmployeeCount;
    }
    
}
