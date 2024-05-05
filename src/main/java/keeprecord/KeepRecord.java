/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package keeprecord;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Set;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;

/**
 *
 * @author Yug
 */
@Named(value = "keepRecord")
@SessionScoped
public class KeepRecord implements Serializable {
 private static CredentialValidationResult result;
    private static CallerPrincipal principal;
   private static Set<String> roles;
   private static String token;
    static String email;
    static String password;
      private static String errorStatus;
   private static Credential credential;
    
    /**
     * Creates a new instance of KeepRecord
     */
   
    public KeepRecord() {
          principal=null;
       token=null;
        email = null;
        password=null;
        errorStatus="";
        token=null;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        KeepRecord.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        KeepRecord.password = password;
    }
    
    public static void reset(){
        principal=null;
       token=null;
        email = null;
        password=null;
        errorStatus="";
        token=null;
    }

    public static CredentialValidationResult getResult() {
        return result;
    }

    public static void setResult(CredentialValidationResult result) {
        KeepRecord.result = result;
    }

    public static CallerPrincipal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(CallerPrincipal principal) {
        KeepRecord.principal = principal;
    }

    public static Set<String> getRoles() {
        return roles;
    }

    public static void setRoles(Set<String> roles) {
        KeepRecord.roles = roles;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        KeepRecord.token = token;
    }

    public static String getErrorStatus() {
        return errorStatus;
    }

    public static void setErrorStatus(String errorStatus) {
        KeepRecord.errorStatus = errorStatus;
    }

    public static Credential getCredential() {
        return credential;
    }

    public static void setCredential(Credential credential) {
        KeepRecord.credential = credential;
    }
    
}
