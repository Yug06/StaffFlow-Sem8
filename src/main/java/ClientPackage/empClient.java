/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package ClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:EmpResource [emp]<br>
 * USAGE:
 * <pre>
 *        empClient client = new empClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Yug
 */
public class empClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/StaffFlow/resources";

    public empClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("emp");
    }

    public <T> T showLeaveByUser(Class<T> responseType, String userID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("showLeaveByUser/{0}", new Object[]{userID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void applyForLeave(String userID, String startDate, String endDate, String subject) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("applyForLeave/{0}/{1}/{2}/{3}", new Object[]{userID, startDate, endDate, subject})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
