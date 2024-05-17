/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import ClientPackage.RestClient;
import ClientPackage.hrClient;
import Entitypkg.Designationtb;
import Entitypkg.Usertb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yug
 */
@Named(value = "hrCDI")
@RequestScoped
public class hrCDI {
  hrClient hc;
    Response rs;
    
    Usertb u = new Usertb();
    
    Collection<Usertb> users;
    GenericType<Collection<Usertb>> gusers;
   
    Collection<Designationtb> deshr;
    GenericType<Collection<Designationtb>> gdeshr;

    String designationID;
    /**
     * Creates a new instance of hrCDI
     */
    public hrCDI() {
         hc=new hrClient();
        users=new ArrayList<>();
        gusers=new GenericType<Collection<Usertb>>(){};
        
        deshr = new ArrayList<>();
       gdeshr = new GenericType<Collection<Designationtb>>(){};
    }

    public Usertb getU() {
        return u;
    }

    public void setU(Usertb u) {
        this.u = u;
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
    
    public String addUser(){
         String contactNo = String.valueOf(u.getContactNo());
         
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String joinDate = dateFormat.format(u.getJoinDate());
        String dob = dateFormat.format(u.getDob());
        
        hc.addUser(u.getName(), u.getEmail(), u.getPassword(), contactNo, joinDate, u.getAddress(), dob, designationID);
        
          return "showUser.jsf";
    }
}
