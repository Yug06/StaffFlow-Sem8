/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package ClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:JakartaEE8Resource [rest]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Yug
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/StaffFlow/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public <T> T getDataByIdforUpdate(Class<T> responseType, String designationID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("showidforupdate/{0}", new Object[]{designationID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayDesignation(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayDesignation");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getEmployeecount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getEmployeecount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addDesignation(String type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addDesignation/{0}", new Object[]{type})).request().post(null);
    }

    public void updateHR(String userID, String name, String email, String password, String contactNo, String joinDate, String address, String DOB) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateHR/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{userID, name, email, password, contactNo, joinDate, address, DOB})).request().put(null);
    }

    public void giveFeedback(String description, String date, String userID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("giveFeedback/{0}/{1}/{2}", new Object[]{description, date, userID})).request().post(null);
    }

    public <T> T getHRByIdforUpdate(Class<T> responseType, String userID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("showHRidforUpdate/{0}", new Object[]{userID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteDesignation(String designationID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteDesignation/{0}", new Object[]{designationID})).request().delete();
    }

    public <T> T getHRCount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getHRcount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T DisplayFeedback(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showFeedback");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayHR(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showHR");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addHR(String name, String email, String password, String contactNo, String joinDate, String address, String DOB) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addHR/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{name, email, password, contactNo, joinDate, address, DOB})).request().post(null);
    }

    public void deleteHR(Integer userID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteHR/{0}", new Object[]{userID})).request().delete();
    }

    public <T> T getPMcount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getPMcount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateDesignation(String designationID, String type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateDestination/{0}/{1}", new Object[]{designationID, type})).request().put(null);
    }

    public <T> T displayEmployee(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showEmp");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayPM(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showPM");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
