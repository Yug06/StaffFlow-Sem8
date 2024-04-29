/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ServletPackage;

import EJBPackage.HREJB;
import Entitypkg.Designationtb;
import Entitypkg.Salarytb;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "HRServlet", urlPatterns = {"/HRServlet"})
public class HRServlet extends HttpServlet {
@EJB
HREJB he;
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
            out.println("<title>Servlet HRServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HRServlet at " + request.getContextPath() + "</h1>");
            
            Collection<Designationtb> cd = he.getDesignationsforHR();
            
            for(Designationtb d: cd){
                out.println(d.getDesignationID() + " " + d.getType());
            }
            
             Collection<Salarytb> sd = he.displaySalary();
            
            for(Salarytb d: sd){
                out.println(d.getSalaryID() + " " + d.getUserID().getName() + " " + d.getAmount());
            }
            
//                         String dateString = "22-04-2021";
//             String dobString = "10-03-2002";
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            try{
//                Date date = dateFormat.parse(dateString);
//                Date dobdate = dateFormat.parse(dobString);
//
//                System.out.println("Converted Date: " + date);
//            
//            he.addUser("Amit", "amit@gmail.com", "amit123", 642314789, date, "Adajan", dobdate,3);
//            }catch (ParseException e) {
//               e.printStackTrace();
//        }
//            
            
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
