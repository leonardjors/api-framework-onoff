package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payloads.Employees;
import payloads.Login;
import requests.CreateEmployeesApi;
import requests.LoginApi;
import requests.RemoveEmployeeApi;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RemoveEmployeeApiTest {


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
    public void testRemoveEmployeeSuccess() {
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
        String employeeId = response.path("body.employees[0].id");
        assertNotNull(employeeId, "Employee ID should not be null");
        // Extract the employee ID from the response

        // Step 2: Remove the employee using the retrieved ID
        Response removeResponse = RemoveEmployeeApi.removeEmployee(accessToken, employeeId);
        removeResponse.then().log().all().statusCode(200);

    }
}


