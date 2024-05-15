package com.mycompany.staffflow.resources;

import EJBPackage.EmployeeEJB;
import EJBPackage.HREJB;
import EJBPackage.ProjectEJB;
import EJBPackage.SuperAdminEJB;
import Entitypkg.Designationtb;
import Entitypkg.Employeefeedback;
import Entitypkg.Projecttb;
import Entitypkg.Salarytb;
import Entitypkg.Usertb;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE8Resource {
    
   
    
    @EJB
    ProjectEJB pejb;
    @EJB
    EmployeeEJB eejb;
    @EJB
    SuperAdminEJB saejb;
    @EJB
    HREJB hrejb;

    //---------------------------------- project -------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjects")
    public Collection<Projecttb> displayProjects() {
        return pejb.displayProjects();
    }

    @POST
    @Path("addProject/{userID}/{title}/{description}/{startDate}/{endDate}")
    public void addProject(@PathParam("userID") Integer userID, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") Date startDate, @PathParam("endDate") Date endDate) {
        pejb.addProject(userID, title, description, startDate, endDate);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showProjbyid/{projectID}")
    public Collection<Projecttb> getProjectID(@PathParam("projectID") Integer projectID) {
        return pejb.getProjectID(projectID);
    }

    @PUT
    @Path("updateProject/{userID}/{title}/{description}/{startDate}/{endDate}/{projectID}")
    public void updateProject(@PathParam("projectID") Integer projectID, @PathParam("userID") Integer userID, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") Date startDate, @PathParam("endDate") Date endDate) {
        pejb.updateProject(projectID, userID, title, description, startDate, endDate);
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

    //----------------------------------- employee -------------------------------------------------------
    @POST
    @Path("giveFeedback/{description}/{date}/{userID}")
    public void giveFeedback(@PathParam("userID") Integer userID, @PathParam("description") String description, @PathParam("date") Date date) {
        eejb.giveFeedback(userID, description, date);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showFeedback")
    public Collection<Employeefeedback> DisplayFeedback() {
        return eejb.DisplayFeedback();
    }

    //------------------------------------- super admin ------------------------------------------------------
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayDesignation")
    public Collection<Designationtb> displayDesignation() {
        return saejb.displayDesignation();
    }

    @POST
    @Path("addDesignation/{type}")
    public void addDesignation(@PathParam("type") String type) {
        saejb.addDesignation(type);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showidforupdate/{designationID}")
    public Collection<Designationtb> getDataByIdforUpdate(@PathParam("designationID") Integer designationID) {
        return saejb.getDataByIdforUpdate(designationID);
    }

    
    @PUT
    @Path("updateDestination/{designationID}/{type}")
    public void updateDesignation(@PathParam("designationID") Integer designationID,@PathParam("type")  String type) {
        saejb.updateDesignation(designationID, type);
    }

    
    
    @DELETE
    @Path("deleteDesignation/{designationID}")
    public void deleteDesignation(@PathParam("designationID") Integer designationID) {
        saejb.deleteDesignation(designationID);
    }

    
    
//    @Path("addHR/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}")
//    public Response addHR(
//        @PathParam("name") String name,
//        @PathParam("email") String email,
//        @PathParam("password") String password,
//        @PathParam("contactNo") Integer contactNo,
//        @PathParam("joinDate") Date joinDate,
//        @PathParam("address") String address,
//        @PathParam("DOB") Date DOB
//    ) {
//        try {
//            // Call your EJB method to add HR
//            saejb.addHR(name, email, password, contactNo, joinDate, address, DOB);
//            
//            // Return a success response
//            return Response.status(Response.Status.CREATED).entity("HR user added successfully").build();
//        } catch (Exception e) {
//            // Return an error response if something goes wrong
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add HR user").build();
//        }
//    }
    @POST
    @Path("addHR/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}")
    public void addHR(@PathParam("name") String name,@PathParam("email") String email,@PathParam("password") String password, @PathParam("contactNo") Integer contactNo, @PathParam("joinDate") String joinDate,@PathParam("address") String address,@PathParam("DOB") String DOB) {
       try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
              Date jdate = sdf.parse(joinDate);
              Date dob = sdf.parse(DOB);

        saejb.addHR(name, email, password, contactNo, jdate, address, dob);
       }catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    @DELETE
    @Path("deleteHR/{userID}")
    public void deleteHR(@PathParam("userID") Integer userID) {
        saejb.deleteHR(userID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showHR")
    public Collection<Usertb> displayHR() {
        return saejb.displayHR();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showHRidforUpdate/{userID}")
    public Collection<Usertb> getHRByIdforUpdate(@PathParam("userID") Integer userID) {
        return saejb.getHRByIdforUpdate(userID);
    }

    
    @PUT
    @Path("updateHR/{userID}/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}")
    public void updateHR(@PathParam("userID") Integer userID,@PathParam("name")  String name,@PathParam("email")  String email,@PathParam("password")  String password,@PathParam("contactNo")  Integer contactNo,@PathParam("joinDate")  Date joinDate,@PathParam("address")  String address,@PathParam("DOB")  Date DOB) {
        saejb.updateHR(userID, name, email, password, contactNo, joinDate, address, DOB);
    }
    
    //getHRCount
   @GET
    @Path("/getHRcount")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getHRCount() {
        return saejb.getHRcount();
    }
    
    //showPM
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showPM")
    public Collection<Usertb> displayPM() {
        return saejb.displayPM();
    }
    
    //showEMP
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showEmp")
    public Collection<Usertb> displayEmployee() {
        return saejb.displayEmployee();
    }
    //getPMCount
   @GET
    @Path("/getPMcount")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getPMcount() {
        return saejb.getPMcount();
    }
    
     //getEmployeeCount
   @GET
    @Path("/getEmployeecount")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getEmployeecount() {
        return saejb.getEmployeecount();
    }
    
    //-------------------------------------------- HR ------------------------------------------------------
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showDestination")
    public Collection<Designationtb> showDesignation() {
        return hrejb.showDesignation();
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showDestinationforHR")
    public Collection<Designationtb> getDesignationsforHR() {
        return hrejb.getDesignationsforHR();
    }

    
    @POST
    @Path("addUser/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}/{designationID}")
    public void addUser(@PathParam("name") String name,@PathParam("email")  String email,@PathParam("password") String password,@PathParam("contactNo") Integer contactNo,@PathParam("joinDate")  Date joinDate,@PathParam("address")  String address,@PathParam("DOB")  Date DOB,@PathParam("designationID")  Integer designationID) {
        hrejb.addUser(name, email, password, contactNo, joinDate, address, DOB, designationID);
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
    public void updateUser(@PathParam("userID") Integer userID,@PathParam("name")  String name,@PathParam("email")  String email,@PathParam("password")  String password,@PathParam("contactNo")  Integer contactNo,@PathParam("joinDate")  Date joinDate,@PathParam("address")  String address,@PathParam("DOB")  Date DOB) {
        hrejb.updateUser(userID, name, email, password, contactNo, joinDate, address, DOB);
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("showSalary")
    public Collection<Salarytb> displaySalary() {
        return hrejb.displaySalary();
    }


}
