/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package ClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:HrResource [hr]<br>
 * USAGE:
 * <pre>
 *        hrClient client = new hrClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Yug
 */
public class hrClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/StaffFlow/resources";

    public hrClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("hr");
    }

    public <T> T checkPayrollRecordExistsForMonth(Class<T> responseType, String userID, String effectiveDate) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("checkRecord/{0}/{1}", new Object[]{userID, effectiveDate}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getMostRecentPayrollRecordForUser(Class<T> responseType, String userID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("mostRecentPayroll/{0}", new Object[]{userID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addUser(String name, String email, String password, String contactNo, String joinDate, String address, String DOB, String designationID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{name, email, password, contactNo, joinDate, address, DOB, designationID})).request().post(null);
    }

    public <T> T getPayrollRecordsForUser(Class<T> responseType, String userID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("payroll/{0}", new Object[]{userID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T showAllLeave(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("leave/all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllAttendanceRecords(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("attendance/all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateUser(String userID, String name, String email, String password, String contactNo, String joinDate, String address, String DOB) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{userID, name, email, password, contactNo, joinDate, address, DOB})).request().post(null);
    }

    public void updatePayrollRecord(String payrollID, String basicSalary, String bonus, String deductions, String effectiveDate) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("payroll/update/{0}/{1}/{2}/{3}/{4}", new Object[]{payrollID, basicSalary, bonus, deductions, effectiveDate})).request().post(null);
    }

    public <T> T getAttendancebyDate(Class<T> responseType, String date) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("attendance/{0}", new Object[]{date}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPayrollRecords(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("payroll/all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deletePayrollRecord(String payrollID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("payroll/delete/{0}", new Object[]{payrollID})).request().delete();
    }

    public <T> T getUsersWithAttendance(Class<T> responseType, String date) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("usersWithAttendance/{0}", new Object[]{date}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void recordAttendance(String userId, String date, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("attendance/{0}/{1}/{2}", new Object[]{userId, date, status})).request().post(null);
    }

    public void rejectLeave(String leaveID, String userID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("rejectleave/{0}/{1}", new Object[]{leaveID, userID})).request().post(null);
    }

    public <T> T getDesignationsforHR(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showDesignationforHR");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T showLeaveToHR(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showLeaveToHr");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserByIdforUpdate(Class<T> responseType, String userID) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("showUseridforupdate/{0}", new Object[]{userID}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayUserList(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayUserList");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteUser(String userID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteUser/{0}", new Object[]{userID})).request().delete();
    }

    public void approveLeave(String leaveID, String userID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("approveleave/{0}/{1}", new Object[]{leaveID, userID})).request().post(null);
    }

    public <T> T getUsersWithPayrollStatus(Class<T> responseType, String effectiveDate) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("usersWithPayrollStatus/{0}", new Object[]{effectiveDate}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPayrollRecordsForMonth(Class<T> responseType, String month, String year) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("payroll/month/{0}/{1}", new Object[]{month, year}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T showDesignation(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("showDesignation");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPayroll(String userID, String basicSalary, String bonus, String deductions, String effectiveDate) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addPayroll/{0}/{1}/{2}/{3}/{4}", new Object[]{userID, basicSalary, bonus, deductions, effectiveDate})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
