/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import ClientPackage.RestClient;
import ClientPackage.empClient;
import Entitypkg.Leavetb;
import Entitypkg.Projecttb;
import Entitypkg.Tasktb;
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
    
    Collection<Projecttb> projByempcol;
    GenericType<Collection<Projecttb>> gprojByemp;
    
      Collection<Tasktb> taskbyempcol;
    GenericType<Collection<Tasktb>> gtaskbyempcol;
    
    Response rs;
    Projecttb p = new Projecttb();
    Tasktb t = new Tasktb();
    
    String selectedProjName;
    
    
    public empCDI() {
        rc = new RestClient();
        ec = new empClient();
        leaveForUser = new ArrayList<>();
        gleaveForUser = new GenericType<Collection<Leavetb>>() {
        };
        
        projByempcol = new ArrayList<>();
        gprojByemp = new GenericType<Collection<Projecttb>>(){};
        
         taskbyempcol = new ArrayList<>();
        gtaskbyempcol = new GenericType<Collection<Tasktb>>(){};
        
    }

    public Leavetb getL() {
        return l;
    }

    public void setL(Leavetb l) {
        this.l = l;
    }

    public Projecttb getP() {
        return p;
    }

    public void setP(Projecttb p) {
        this.p = p;
    }

    public Tasktb getT() {
        return t;
    }

    public void setT(Tasktb t) {
        this.t = t;
    }

    public String getSelectedProjName() {
        return selectedProjName;
    }

    public void setSelectedProjName(String selectedProjName) {
        this.selectedProjName = selectedProjName;
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

    public Collection<Projecttb> getProjByempcol() {
          FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        rs = ec.showProjectByEmp(Response.class, userID.toString());
        projByempcol = rs.readEntity(gprojByemp);
        return projByempcol;
    }

    public void setProjByempcol(Collection<Projecttb> projByempcol) {
        this.projByempcol = projByempcol;
    }
    
    
      public String getProj(Projecttb p){
        this.p = p;
       // this.selectedProjID = p.getProjectID().toString();
       TempData.projID = p.getProjectID();     
        this.selectedProjName = p.getTitle();
//        this.cid = p.getCid().getCid().toString();
        return "showTaskforUser.jsf";
    }

    public Collection<Tasktb> getTaskbyempcol() {
           FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        
         rs = ec.getTaskByEmpProj(Response.class, TempData.projID.toString(), userID.toString());
        taskbyempcol = rs.readEntity(gtaskbyempcol);
        return taskbyempcol;
    }

    public void setTaskbyempcol(Collection<Tasktb> taskbyempcol) {
        this.taskbyempcol = taskbyempcol;
    }
     
  public String redirectToindividualTask(Tasktb t){
         this.t = t;
         return "showIndividualTaskforUser.jsf";
     }
  
   public String completeTask(){
        
      ec.completeTask(t.getTaskID().toString());
        return "showTaskforUser.jsf";
    }
    
   public String rejectTask(){
        
      ec.rejectTask(t.getTaskID().toString());
        return "showTaskforUser.jsf";
    }
}
