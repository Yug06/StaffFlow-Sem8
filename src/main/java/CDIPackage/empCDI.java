/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import ClientPackage.RestClient;
import ClientPackage.empClient;
import Entitypkg.Leavetb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Yug
 */
@Named(value = "empCDI")
@RequestScoped
public class empCDI {

    /**
     * Creates a new instance of empCDI
     */
    RestClient rc;
    empClient ec;
    Leavetb l = new Leavetb();
    Collection<Leavetb> leaveForUser;
    GenericType<Collection<Leavetb>> gleaveForUser;
    Response rs;

    public empCDI() {
        rc = new RestClient();
        ec = new empClient();
        leaveForUser = new ArrayList<>();
        gleaveForUser = new GenericType<Collection<Leavetb>>() {
        };
    }

    public Leavetb getL() {
        return l;
    }

    public void setL(Leavetb l) {
        this.l = l;
    }

    public Collection<Leavetb> getLeaveForUser() {
         FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        rs=ec.showLeaveByUser(Response.class,String.valueOf(userID));
        leaveForUser=rs.readEntity(gleaveForUser);
        return leaveForUser;
    }

    public void setLeaveForUser(Collection<Leavetb> leaveForUser) {
        this.leaveForUser = leaveForUser;
    }
    
     public String applyForLeave()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        if (userID != null) 
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sdate = dateFormat.format(l.getStartDate());
            String edate = dateFormat.format(l.getEndDate());
            ec.applyForLeave(String.valueOf(userID), sdate, edate, l.getSubject());
            return "showLeave.jsf";
        } else {
            return "login.jsf?faces-redirect=true";
        }
    }

}
