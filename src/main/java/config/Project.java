/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author Yug
 */
//@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "login.jsf"))

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/project",
        callerQuery = "select password from usertb where email=?",
        groupsQuery = "SELECT type FROM designationtb WHERE designationID IN (SELECT designationID FROM usertb WHERE email = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)

@Named(value = "project")
@ApplicationScoped
public class Project {

}
