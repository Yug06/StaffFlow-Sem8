/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.staffflow.resources;

import EJBPackage.EmployeeEJB;
import EJBPackage.HREJB;
import EJBPackage.ProjectEJB;
import EJBPackage.SuperAdminEJB;
import Entitypkg.Leavetb;
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
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Yug
 */
@Path("emp")
@RequestScoped
public class EmpResource {
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

    /**
     * Creates a new instance of EmpResource
     */
    public EmpResource() {
    }

    @POST
    @Path("applyForLeave/{userID}/{startDate}/{endDate}/{subject}")
    public void applyForLeave(@PathParam("userID") Integer userID,@PathParam("startDate") String startDate,@PathParam("endDate") String endDate,@PathParam("subject") String subject) 
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sdate = sdf.parse(startDate);
            Date edate = sdf.parse(endDate);
            eejb.applyForLeave(userID, sdate, edate, subject);
            
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showLeaveByUser/{userID}")
    public Collection<Leavetb> showLeaveByUser(@PathParam("userID") Integer userID)
    {
        return eejb.showLeaveByUser(userID);
    }
    
  
}
