/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import keeprecord.KeepRecord;

/**
 *
 * @author Yug
 */
@Named(value = "loginBean")
@RequestScoped
public class loginBean {

    @Inject
    SecurityContext ctx;

    private String email;
    private String password;
    private String role;
    private Set<String> roles;
    private String error;
    private AuthenticationStatus status;

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String login() {
        Credential c = new UsernamePasswordCredential(email, new Password(password));

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        AuthenticationStatus auth = ctx.authenticate(request, response, AuthenticationParameters.withParams().credential(c));

        System.out.println(email + password + auth);
        if (auth.equals(auth.SUCCESS)) {
            KeepRecord.setEmail(email);
            KeepRecord.setPassword(password);
            if (ctx.isCallerInRole("Super Admin")) {
                role = "Super Admin";
                return "super.jsf";
            } else if (ctx.isCallerInRole("HR")) {
                role = "HR";
               
                return "hr.jsf";
            }
        }
        return "login.jsf";
    }

    public String logout() throws ServletException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        request.logout();
        KeepRecord.reset();
        return "login.jsf";
    }
    
}
