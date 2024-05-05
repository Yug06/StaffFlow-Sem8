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
    
    Response rs;
    /**
     * Creates a new instance of superadminCDI
     */
    public superadminCDI() {
        rc = new RestClient();
        userCollection = new ArrayList<>();
        gusers = new GenericType<Collection<Usertb>>(){};
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
    
}
