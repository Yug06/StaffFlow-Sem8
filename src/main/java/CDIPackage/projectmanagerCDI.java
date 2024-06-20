/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import ClientPackage.empClient;
import ClientPackage.pmClient;
import Entitypkg.Leavetb;
import Entitypkg.Projecttb;
import Entitypkg.Tasktb;
import Entitypkg.Userprojecttb;
import Entitypkg.Usertb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
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
empClient ec;
    pmClient pc;
    
    Projecttb p = new Projecttb();
    
    Collection<Projecttb> projcol;
    GenericType<Collection<Projecttb>> gproj;
    
    Collection<Projecttb> projcoltopm;
    GenericType<Collection<Projecttb>> gprojtopm;
    
    Collection<Userprojecttb> userprojcol;
    GenericType<Collection<Userprojecttb>> guserprojcol;
    
    Collection<Usertb> empcol;
    GenericType<Collection<Usertb>> gempcol;
    
    Collection<Usertb> empinprojcol;
    GenericType<Collection<Usertb>> gempinprojcol;
    
    Collection<Tasktb> taskbyempcol;
    GenericType<Collection<Tasktb>> gtaskbyempcol;
    
    Response rs;
    
    Usertb u = new Usertb();
    Userprojecttb up = new Userprojecttb();
    Tasktb t = new Tasktb();
    String userID;
    String selectedProjID;
    String selectedProjName;
  String unm;
    /**
     * Creates a new instance of projectmanagerCDI
     */
    public projectmanagerCDI() {
        pc = new pmClient();
        ec = new empClient();
        projcol = new ArrayList<>();
        gproj = new GenericType<Collection<Projecttb>>(){};
        
        projcoltopm = new ArrayList<>();
        gprojtopm = new GenericType<Collection<Projecttb>>(){};
        
        userprojcol = new ArrayList<>();
        guserprojcol = new GenericType<Collection<Userprojecttb>>(){};
        
        empcol = new ArrayList<>();
        gempcol = new GenericType<Collection<Usertb>>(){};
        
        empinprojcol = new ArrayList<>();
        gempinprojcol = new GenericType<Collection<Usertb>>(){};
        
        taskbyempcol = new ArrayList<>();
        gtaskbyempcol = new GenericType<Collection<Tasktb>>(){};
    }

    public Projecttb getP() {
        return p;
    }

    public void setP(Projecttb p) {
        this.p = p;
    }

    public Usertb getU() {
        return u;
    }

    public void setU(Usertb u) {
        this.u = u;
    }

    public Userprojecttb getUp() {
        return up;
    }

    public void setUp(Userprojecttb up) {
        this.up = up;
    }    

    public Collection<Usertb> getEmpcol() {
        rs = pc.displayEmp(Response.class);
        empcol = rs.readEntity(gempcol);
        return empcol;
    }

    public void setEmpcol(Collection<Usertb> empcol) {
        this.empcol = empcol;
    }

    public Collection<Usertb> getEmpinprojcol() {
         rs = pc.getEmpNotInProj(Response.class, TempData.projID.toString());
        empinprojcol = rs.readEntity(gempcol);
        return empinprojcol;
    }

    public void setEmpinprojcol(Collection<Usertb> empinprojcol) {
        this.empinprojcol = empinprojcol;
    }
    
    

    public Collection<Projecttb> getProjcol() {
        rs = pc.displayProjects(Response.class);
        projcol = rs.readEntity(gproj);
        return projcol;
    }

    public void setProjcol(Collection<Projecttb> projcol) {
        this.projcol = projcol;
    }

           public String getProject(Projecttb p){
        this.p = p;
        return "projectManage.jsf";
    }
    
    public Collection<Projecttb> getProjcoltopm() {
           FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        
        rs = pc.getProjectbyPM(Response.class, userID.toString());
        projcoltopm = rs.readEntity(gprojtopm);
        return projcoltopm;
    }

    public void setProjcoltopm(Collection<Projecttb> projcoltopm) {
        this.projcoltopm = projcoltopm;
    }

    public String getSelectedProjID() {
        return selectedProjID;
    }

    public void setSelectedProjID(String selectedProjID) {
        this.selectedProjID = selectedProjID;
    }

    public String getSelectedProjName() {
        return selectedProjName;
    }

    public void setSelectedProjName(String selectedProjName) {
        this.selectedProjName = selectedProjName;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public Collection<Userprojecttb> getUserprojcol() {
        rs = pc.getEmpbyProj(Response.class, TempData.projID.toString());
        userprojcol = rs.readEntity(guserprojcol);
        return userprojcol;
    }

    public void setUserprojcol(Collection<Userprojecttb> userprojcol) {
        this.userprojcol = userprojcol;
    }

    
        public String getProj(Projecttb p){
        this.p = p;
       // this.selectedProjID = p.getProjectID().toString();
       TempData.projID = p.getProjectID();
        this.selectedProjName = p.getTitle();
//        this.cid = p.getCid().getCid().toString();
        return "showProjEmp.jsf";
    }
        
        public String getUsr(Integer id,String name){
            TempData.uID = id;
            TempData.name = name;
            return "showTaskforUser.jsf";
        }
        
        public String redirectToaddUsertoProj(){
            
            return "AddUsertoProj.jsf";
        }
    
    public String addUsertoProj(){
        pc.addEmptoProj(TempData.projID.toString(), userID);
        return "showProject.jsf";
    }
    
    //show task by user

    public Collection<Tasktb> getTaskbyempcol() {
         rs = pc.getTaskByEmpProj(Response.class, TempData.projID.toString(), TempData.uID.toString());
        taskbyempcol = rs.readEntity(gtaskbyempcol);
        return taskbyempcol;
    }

    public void setTaskbyempcol(Collection<Tasktb> taskbyempcol) {
        this.taskbyempcol = taskbyempcol;
    }
    
     public String redirectToaddTasktoUser(){
            
            return "addTasktoUser.jsf";
        }

    public Tasktb getT() {
        return t;
    }

    public void setT(Tasktb t) {
        this.t = t;
    }

    public String getUnm() {
        return TempData.name;
    }
    
     
     
     public String addTasktoUser(){
         
           
         FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        
        // Check if userID is not null
        if (userID != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ddate = dateFormat.format(t.getDueDate());
            
         pc.addTasktoProj(userID.toString(), TempData.uID.toString(), t.getSubject(), t.getDescription(), ddate, TempData.projID.toString());
         return "showTaskforUser.jsf";
        }else {
            // Handle the case where userID is not found in the session
            // Maybe redirect to login page or show an error message
            return "login.jsf?faces-redirect=true";
        }
     }
     
     public String redirectToindividualTask(Tasktb t){
         this.t = t;
         return "showIndividualTaskforUser.jsf";
     }
     
       public String changePassword()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Integer userID = (Integer) session.getAttribute("Uid");
        ec.changePassword(u.getPassword(), userID.toString());
        return "homePM.jsf";
    }
}
