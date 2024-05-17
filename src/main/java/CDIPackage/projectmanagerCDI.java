/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import ClientPackage.pmClient;
import Entitypkg.Projecttb;
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
@Named(value = "projectmanagerCDI")
@RequestScoped
public class projectmanagerCDI {

    pmClient pc;
    
    Projecttb p = new Projecttb();
    
    Collection<Projecttb> projcol;
    GenericType<Collection<Projecttb>> gproj;
    
    Response rs;
    
    
    /**
     * Creates a new instance of projectmanagerCDI
     */
    public projectmanagerCDI() {
        pc = new pmClient();
        
        projcol = new ArrayList<>();
        gproj = new GenericType<Collection<Projecttb>>(){};
    }

    public Projecttb getP() {
        return p;
    }

    public void setP(Projecttb p) {
        this.p = p;
    }

    public Collection<Projecttb> getProjcol() {
        rs = pc.displayProjects(Response.class);
        projcol = rs.readEntity(gproj);
        return projcol;
    }

    public void setProjcol(Collection<Projecttb> projcol) {
        this.projcol = projcol;
    }
    
    public String addProject(){
        
        
         FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        
        // Check if userID is not null
        if (userID != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sdate = dateFormat.format(p.getStartDate());
            String edate = dateFormat.format(p.getEndDate());

            pc.addProject(String.valueOf(userID), p.getTitle(), p.getDescription(), sdate, edate);

            return "showProject.jsf";
        } else {
            // Handle the case where userID is not found in the session
            // Maybe redirect to login page or show an error message
            return "login.jsf?faces-redirect=true";
        }
    }
}
