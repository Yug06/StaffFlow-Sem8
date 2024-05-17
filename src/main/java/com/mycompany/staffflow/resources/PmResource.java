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

    @POST
    @Path("addProject/{userID}/{title}/{description}/{startDate}/{endDate}")
    public void addProject(@PathParam("userID") Integer userID, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
         try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
              Date sdate = sdf.parse(startDate);
              Date edate = sdf.parse(endDate);
         pejb.addProject(userID, title, description, sdate, edate);
      
       }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjbyid/{projectID}")
    public Collection<Projecttb> getProjectID(@PathParam("projectID") Integer projectID) {
        return pejb.getProjectID(projectID);
    }

    @PUT
    @Path("updateProject/{userID}/{title}/{description}/{startDate}/{endDate}/{projectID}")
    public void updateProject(@PathParam("projectID") Integer projectID, @PathParam("userID") Integer userID, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
         try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
              Date sdate = sdf.parse(startDate);
              Date edate = sdf.parse(endDate);
           pejb.updateProject(projectID, userID, title, description, sdate, edate);
      
       }catch (Exception e) {
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

   
}
