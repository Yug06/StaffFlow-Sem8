/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import EJBPackage.HREJB;
import EJBPackage.SuperAdminEJB;
import EJBPackage.userforattendance;
import EJBPackage.userforpayroll;
import Entitypkg.Attendancetb;
import Entitypkg.Designationtb;
import Entitypkg.Payrolltb;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yug
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    @EJB
    SuperAdminEJB saejb;
    @EJB
    HREJB hrejb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");

//            int month = 4; // For example, May
//            int year = 2024;
//
////
//            Collection<Payrolltb> payrollRecords = hrejb.getPayrollRecordsForMonth(month, year);
//
//
//            for (Payrolltb payrollRecord : payrollRecords) {
//                // Process each payroll record
//                // Example: Print the details of each payroll record
//                out.println("Payroll ID: " + payrollRecord.getId());
//                out.println("User ID: " + payrollRecord.getUserId().getName());
//                // Add more details as needed
//     // Initialize the current date
        Date currentDate = new Date();
     
//        hrejb.recordAttendance(3, currentDate, Boolean.TRUE);
        // Create an instance of your service clas

        // Call the method and get the result
//        Collection<userforpayroll> userList = hrejb.displayUserListforPayroll(currentDate);
//        Collection<userforattendance> userList = hrejb.displayUserListforAttendance(currentDate);
            Collection<Attendancetb> aList = hrejb.getAttendancetbsByDate(currentDate);

        // Set the response content type
        response.setContentType("text/html");
        
        // Get the response writer
//        PrintWriter out = response.getWriter();

        // Generate the HTML response
        out.println("<html>");
        out.println("<head><title>User List for Payroll</title></head>");
        out.println("<body>");
        out.println("<h1>User List for Payroll</h1>");
        out.println("<table border='1'>");
//        out.println("<tr><th>User ID</th><th>Name</th><th>Email</th><th>Exists in Payroll</th></tr>");
//
        for (Attendancetb user : aList) {
            out.println("<tr>");
            out.println("<td>" + user.getUserID().getName() + "</td>");
            out.println("<td>" + user.getDate() + "</td>");
                        out.println("<td>" + user.getAttendance() + "</td>");

           out.println("<td>" + user.getAttendanceID() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
//         Collection<Payrolltb> pt = hrejb.getPayrollRecordsForMonth();
//            
//            for(Payrolltb d: pt){
//                out.println(d.getUserId().getName() + " " + d.getFinalAmount());
//            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
