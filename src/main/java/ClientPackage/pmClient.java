/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package ClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:PmResource [Pm]<br>
 * USAGE:
 * <pre>
 *        pmClient client = new pmClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Yug
 */
public class pmClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/StaffFlow/resources";

    public pmClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Pm");
    }

    public <T> T displayProjects(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showProjects");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addProject(String userID, String title, String description, String startDate, String endDate) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addProject/{0}/{1}/{2}/{3}/{4}", new Object[]{userID, title, description, startDate, endDate})).request().post(null);
    }

    public void updateProject(String userID, String title, String description, String startDate, String endDate, String projectID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateProject/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{userID, title, description, startDate, endDate, projectID})).request().put(null);
    }

    public void deleteProject(Integer projectID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteProject/{0}", new Object[]{projectID})).request().delete();
    }

    public <T> T getProjectTitle(Class<T> responseType, String title) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("showProjbytitle/{0}", new Object[]{title}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProjectID(Class<T> responseType, String projectID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("showProjbyid/{0}", new Object[]{projectID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
