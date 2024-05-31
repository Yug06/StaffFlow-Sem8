/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.staffflow.resources;

import EJBPackage.EmployeeEJB;
import EJBPackage.HREJB;
import EJBPackage.ProjectEJB;
import EJBPackage.SuperAdminEJB;
import Entitypkg.Projecttb;
import Entitypkg.Tasktb;
import Entitypkg.Userprojecttb;
import Entitypkg.Usertb;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Yug
 */
@Path("Pm")
@RequestScoped
public class PmResource {

    @EJB
    ProjectEJB pejb;
    @EJB
    EmployeeEJB eejb;
    @EJB
    SuperAdminEJB saejb;
    @EJB
    HREJB hrejb;

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjects")
    public Collection<Projecttb> displayProjects() {
        return pejb.displayProjects();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjbymanager/{userID}")
    public Collection<Projecttb> getProjectbyPM(@PathParam("userID") Integer userID) {
        return pejb.displayProjecttoPM(userID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showEmp")
    public Collection<Usertb> displayEmp() {
        return pejb.displayEmp();
    }
    
    @POST
    @Path("addProject/{userID}/{title}/{description}/{startDate}/{endDate}")
    public void addProject(@PathParam("userID") Integer userID, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date sdate = sdf.parse(startDate);
            Date edate = sdf.parse(endDate);
            pejb.addProject(userID, title, description, sdate, edate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjbyid/{projectID}")
    public Collection<Projecttb> getProjectID(@PathParam("projectID") Integer projectID) {
        return pejb.getProjectID(projectID);
    }

    @POST
    @Path("updateProject/{userID}/{title}/{description}/{startDate}/{endDate}/{projectID}")
    public void updateProject(@PathParam("projectID") Integer projectID, @PathParam("userID") Integer userID, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date sdate = sdf.parse(startDate);
            Date edate = sdf.parse(endDate);
            pejb.updateProject(projectID, userID, title, description, sdate, edate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DELETE
    @Path("deleteProject/{projectID}")
    public void deleteProject(@PathParam("projectID") Integer projectID) {
        pejb.deleteProject(projectID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjbytitle/{title}")
    public Collection<Projecttb> getProjectTitle(@PathParam("title") String title) {
        return pejb.getProjectTitle(title);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showEmpbyProj/{projectID}")
    public Collection<Userprojecttb> getEmpbyProj(@PathParam("projectID") Integer projectID) {
        return pejb.getEmpbyProj(projectID);
    }
    
      @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showEmpNotinProj/{projectID}")
    public Collection<Usertb> getEmpNotInProj(@PathParam("projectID") Integer projectID) {
        return pejb.getEmployeesNotInProject(projectID);
    }

    @POST
    @Path("adduserproj/{ProjectID}/{userID}")
    public void addEmptoProj(@PathParam("ProjectID") Integer ProjectID, @PathParam("userID") Integer userID) {
        pejb.addEmployeetoProject(ProjectID, userID);
    }
    
    @POST
    @Path("addtasktoemp/{assignedBy}/{assignedTo}/{subject}/{description}/{dueDate}/{projectId}")
    public void addTasktoProj(@PathParam("assignedBy") Integer assignedBy,@PathParam("assignedTo") Integer assignedTo,@PathParam("subject") String subject,@PathParam("description") String description,@PathParam("dueDate") String dueDate,@PathParam("projectId") Integer projectId){
         try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date duedate = sdf.parse(dueDate);

            pejb.addTaskToProject(assignedBy, assignedTo, subject, description, duedate, projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @GET
    @Path("getTaskbyEmpProj/{ProjectID}/{assignedTo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Tasktb> getTaskByEmpProj(@PathParam("ProjectID") Integer ProjectID,@PathParam("assignedTo") Integer assignedTo){
        return pejb.getTaskByEmpProj(ProjectID, assignedTo);
    }
}
