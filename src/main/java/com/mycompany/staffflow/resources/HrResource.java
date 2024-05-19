/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.staffflow.resources;

import EJBPackage.EmployeeEJB;
import EJBPackage.HREJB;
import EJBPackage.ProjectEJB;
import EJBPackage.SuperAdminEJB;
import EJBPackage.userforpayroll;
import Entitypkg.Designationtb;
import Entitypkg.Salarytb;
import Entitypkg.Payrolltb;
import Entitypkg.Usertb;
import java.math.BigDecimal;
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
    public Collection<Usertb> displayUserList() {
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
    public void addUser(@PathParam("name") String name, @PathParam("email") String email, @PathParam("password") String password, @PathParam("contactNo") Integer contactNo, @PathParam("joinDate") String joinDate, @PathParam("address") String address, @PathParam("DOB") String DOB, @PathParam("designationID") Integer designationID) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date jdate = sdf.parse(joinDate);
            Date dob = sdf.parse(DOB);

//        saejb.addHR(name, email, password, contactNo, jdate, address, dob);
            hrejb.addUser(name, email, password, contactNo, jdate, address, dob, designationID);
        } catch (Exception e) {
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

    @POST
    @Path("updateUser/{userID}/{name}/{email}/{password}/{contactNo}/{joinDate}/{address}/{DOB}")
    public void updateUser(@PathParam("userID") Integer userID, @PathParam("name") String name, @PathParam("email") String email, @PathParam("password") String password, @PathParam("contactNo") Integer contactNo, @PathParam("joinDate") String joinDate, @PathParam("address") String address, @PathParam("DOB") String DOB) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date jdate = sdf.parse(joinDate);
            Date dob = sdf.parse(DOB);

//        saejb.addHR(name, email, password, contactNo, jdate, address, dob);
            hrejb.updateUser(userID, name, email, password, contactNo, jdate, address, dob);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @POST
    @Path("addPayroll/{userID}/{basicSalary}/{bonus}/{deductions}/{effectiveDate}")
    public void addPayroll(@PathParam("userID") Integer userID,
            @PathParam("basicSalary") BigDecimal basicSalary,
            @PathParam("bonus") BigDecimal bonus,
            @PathParam("deductions") BigDecimal deductions,
            @PathParam("effectiveDate") String effectiveDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eDate = sdf.parse(effectiveDate);

            hrejb.addPayroll(userID, basicSalary, bonus, deductions, eDate);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing exception
        }
    }

    @GET
    @Path("/checkRecord/{userID}/{effectiveDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkPayrollRecordExistsForMonth(@PathParam("userID") Integer userID,
            @PathParam("effectiveDate") String effectiveDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eDate = sdf.parse(effectiveDate);
            return hrejb.isPayrollRecordExistsForMonth(userID, eDate);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GET
    @Path("/payroll/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payrolltb> getPayrollRecordsForUser(@PathParam("userID") Integer userID) {
        return hrejb.getPayrollRecordsForUser(userID);
    }

    @POST
    @Path("/payroll/update/{payrollID}/{basicSalary}/{bonus}/{deductions}/{effectiveDate}")
    public void updatePayrollRecord(@PathParam("payrollID") Integer payrollID,
            @PathParam("basicSalary") BigDecimal basicSalary,
            @PathParam("bonus") BigDecimal bonus,
            @PathParam("deductions") BigDecimal deductions,
            @PathParam("effectiveDate") String effectiveDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eDate = sdf.parse(effectiveDate);

            hrejb.updatePayrollRecord(payrollID, basicSalary, bonus, deductions, eDate);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing exception
        }
    }

    @DELETE
    @Path("/payroll/delete/{payrollID}")
    public void deletePayrollRecord(@PathParam("payrollID") Integer payrollID) {
        hrejb.deletePayrollRecord(payrollID);
    }

    @GET
    @Path("/payroll/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payrolltb> getAllPayrollRecords() {
        return hrejb.getAllPayrollRecords();
    }

    @GET
    @Path("/payroll/month/{month}/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payrolltb> getPayrollRecordsForMonth(@PathParam("month") int month,
            @PathParam("year") int year) {
        return hrejb.getPayrollRecordsForMonth(month, year);
    }

     @GET
    @Path("/usersWithPayrollStatus/{effectiveDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<userforpayroll> getUsersWithPayrollStatus(@PathParam("effectiveDate") String effectiveDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date eDate = sdf.parse(effectiveDate);
            return hrejb.displayUserListforPayroll(eDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     @GET
    @Path("mostRecentPayroll/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Payrolltb getMostRecentPayrollRecordForUser(@PathParam("userID") Integer userID) {
        Payrolltb payroll = hrejb.getMostRecentPayrollRecordForUser(userID);
        return payroll;
    }
}
