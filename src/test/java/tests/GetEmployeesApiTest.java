package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payloads.EmployeesResponse;
import payloads.Login;
import requests.GetEmployeesApi;
import requests.LoginApi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetEmployeesApiTest {

    private static String accessToken;

    @BeforeAll
    public static void setup() {
        // Perform login to get the access token
        Login loginPayload = new Login.Builder()
                .setEmail("leonard+b2bautomation@onoffapp.com")
                .setPassword("123456Ai")
                .build();
        Response loginResponse = LoginApi.postLogin(loginPayload);
        loginResponse.then().log().all();

        accessToken = loginResponse.path("body.accessToken");
        assertNotNull(accessToken, "Access token was not received");
    }

    @Test
    public void testGetEmployeesSuccess() {
        Response getEmployeesResponse = GetEmployeesApi.getEmployees(accessToken);

        // Assert the request was successful
        getEmployeesResponse.then().statusCode(200);

        EmployeesResponse employeesResponse = getEmployeesResponse.as(EmployeesResponse.class);


        // Ensure the list of employees is not empty
        assertNotNull(employeesResponse.getBody().getEmployees());
        assertFalse(employeesResponse.getBody().getEmployees().isEmpty(), "Employees list should not be empty");

        EmployeesResponse.Employee firstEmployee = employeesResponse.getBody().getEmployees().get(0);
        assertNotNull(firstEmployee.getId(), "Employee ID should not be null");
        assertNotNull(firstEmployee.getEmail(), "Employee email should not be null");
    }
}