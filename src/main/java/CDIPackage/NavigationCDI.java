/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

/**
 *
 * @author Dell
 */
@Named(value = "navigationCDI")
@RequestScoped
public class NavigationCDI {

    String SAdmin;
    String HR;
    String PM;

    
    public NavigationCDI() {
    }
    
    public String checkAdmin()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

         SAdmin = (String) session.getAttribute("Super Admin");
        if (SAdmin == null) {
            // Redirect to the login page
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/plogin.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/plogin.jsf?faces-redirect=true";
        }
         return "successOutcome";
    }
    
    public String checkHR()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

         HR = (String) session.getAttribute("User");
        if (HR == null) {
            // Redirect to the login page
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/plogin.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/plogin.jsf?faces-redirect=true";
        }
         return "successOutcome";
    }
    
      public String checkPM()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

         PM = (String) session.getAttribute("User");
        if (PM == null) {
            // Redirect to the login page
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/plogin.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/plogin.jsf?faces-redirect=true";
        }
         return "successOutcome";
    }
    
}
