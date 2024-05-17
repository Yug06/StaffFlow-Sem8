/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.staffflow.resources;

import EJBPackage.EmployeeEJB;
import EJBPackage.HREJB;
import EJBPackage.ProjectEJB;
import EJBPackage.SuperAdminEJB;
import Entitypkg.Designationtb;
import Entitypkg.Salarytb;
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
@Path("hr")
@RequestScoped
public class HrResource {
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
     * Creates a new instance of HrResource
     */
    //-------------------------------------------- HR ------------------------------------------------------
    
      @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayUserList")
    public Collection<Usertb> displayUserList(){
        return hrejb.displayUserList();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showDesignation")
    public Collection<Designationtb> showDesignation() {
        return hrejb.showDesignation();
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showDesignationforHR")
    public Collection<Designationtb> getDesignationsforHR() {
        return hrejb.getDesignationsforHR();
    }

    
    @POST
    @Path("addUser/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}/{designationID}")
    public void addUser(@PathParam("name") String name,@PathParam("email")  String email,@PathParam("password") String password,@PathParam("contactNo") Integer contactNo,@PathParam("joinDate") String joinDate,@PathParam("address")  String address,@PathParam("DOB") String DOB,@PathParam("designationID")  Integer designationID) {
       try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
              Date jdate = sdf.parse(joinDate);
              Date dob = sdf.parse(DOB);

//        saejb.addHR(name, email, password, contactNo, jdate, address, dob);
        hrejb.addUser(name, email, password, contactNo, jdate, address, dob, designationID);
       }catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    
    @DELETE
    @Path("deleteUser/{userID}")
    public void deleteUser(@PathParam("userID") Integer userID) {
        hrejb.deleteUser(userID);
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showUseridforupdate/{userID}")
    public Collection<Usertb> getUserByIdforUpdate(@PathParam("userID") Integer userID) {
        return hrejb.getUserByIdforUpdate(userID);
    }

    
    @PUT
    @Path("updateUser/{userID}/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}")
    public void updateUser(@PathParam("userID") Integer userID,@PathParam("name")  String name,@PathParam("email")  String email,@PathParam("password")  String password,@PathParam("contactNo")  Integer contactNo,@PathParam("joinDate")  String joinDate,@PathParam("address")  String address,@PathParam("DOB")  String DOB) {
         try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
              Date jdate = sdf.parse(joinDate);
              Date dob = sdf.parse(DOB);

//        saejb.addHR(name, email, password, contactNo, jdate, address, dob);
              hrejb.updateUser(userID, name, email, password, contactNo, jdate, address, dob);

       }catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showSalary")
    public Collection<Salarytb> displaySalary() {
        return hrejb.displaySalary();
    }

}
