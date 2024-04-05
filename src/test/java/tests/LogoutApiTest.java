package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payloads.Login;
import requests.GetEmployeesApi;
import requests.LoginApi;
import requests.LogoutApi;

import static org.junit.jupiter.api.Assertions.*;

public class LogoutApiTest {

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
    public void testSessionTerminationAfterLogout() {
        // Ensure logged in
        assertTrue(accessToken != null && !accessToken.isEmpty(), "Failed to log in");

        // Logout
        Response logoutResponse = LogoutApi.logout(accessToken);
        assertEquals(200, logoutResponse.statusCode(), "Logout failed");

        // Attempt to access account data using the same access token
        Response accountDataResponse = GetEmployeesApi.getEmployees(accessToken);

        // Verify that access is denied
        assertTrue(accountDataResponse.statusCode() == 401, "Session was not correctly terminated after logout");
    }
}
