/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIPackage;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Yug
 */
@Named(value = "navbean")
@RequestScoped
public class navbean {

    /**
     * Creates a new instance of navbean
     */
    public navbean() {
    }
    
    public String gotoAddHR(){
        return "/superadmin/AddHR.jsf";
    }
}
