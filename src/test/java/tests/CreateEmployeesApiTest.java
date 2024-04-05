package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payloads.Employees;
import payloads.Login;
import requests.CreateEmployeesApi;
import requests.LoginApi;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateEmployeesApiTest {

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
    public void testCreateEmployeesSuccess() {
        int randomNumber = 1 + (int) (Math.random() * 1000);

        String randomEmail = "leonard+" + randomNumber + "@onoffapp.com";


        // Construct employee payload
        Employees.Employee employee = new Employees.Employee("Leonard", "Test", randomEmail);
        Employees employeesPayload = new Employees(Arrays.asList(employee));

        // Call the createEmployees method
        Response response = CreateEmployeesApi.createEmployees(accessToken, employeesPayload);
        response.then().log().all();
        // Verify the request was successful
        assertEquals(200, response.statusCode());
        assertEquals(randomEmail, response.path("body.employees[0].email"));
        assertEquals("Leonard", response.path("body.employees[0].firstName"));
        assertEquals("Test", response.path("body.employees[0].lastName"));

    }

    @Test
    public void testCreateEmployeeWithIncompleteData() {
        Employees.Employee employee = new Employees.Employee("Leonard", "Test", ""); // Missing email
        Employees employeesPayload = new Employees(Arrays.asList(employee));

        Response response = CreateEmployeesApi.createEmployees(accessToken, employeesPayload);


        assertEquals(400, response.statusCode());
    }

    @Test
    public void testCreateEmployeeWithTooLongData() {
        String longString = new String(new char[1001]).replace('\0', 'a');
        Employees.Employee employee = new Employees.Employee(longString, longString, longString + "@onoffapp.com");
        Employees employeesPayload = new Employees(Arrays.asList(employee));

        Response response = CreateEmployeesApi.createEmployees(accessToken, employeesPayload);

        assertEquals(400, response.statusCode());
    }

    @Test
    public void testCreateEmployeeWithEmailInUse() {
        Employees.Employee employee = new Employees.Employee("Leonard", "Test", "leonard@b2b-automation@onoffapp.com"); //email in use
        Employees employeesPayload = new Employees(Arrays.asList(employee));

        Response response = CreateEmployeesApi.createEmployees(accessToken, employeesPayload);

        assertEquals(400, response.statusCode());
    }

}
