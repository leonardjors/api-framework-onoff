package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payloads.Login;
import requests.LoginApi;
import requests.UserApi;

import static org.junit.jupiter.api.Assertions.*;

public class GetUserApiTest {

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
    public void testValidGetUserReturnsCompleteUserData() {
        // Fetch user data using the obtained access token
        Response getUserResponse = UserApi.getUser(accessToken);
        getUserResponse.then().statusCode(200).and().log().all(); // Ensure the response status is OK


        // Verify complete and accurate user data is returned
        assertEquals("leonard+b2bautomation@onoffapp.com", getUserResponse.path("body.email"));
        assertEquals("Test", getUserResponse.path("body.firstName"));
        assertEquals("Automation", getUserResponse.path("body.lastName"));
        assertEquals("Viru Väljak 2", getUserResponse.path("body.companyAddress"));
        assertEquals("Onoff", getUserResponse.path("body.companyName"));
        assertEquals("FR55821568060", getUserResponse.path("body.vatNumber"));

    }

    @Test
    public void testGetUserWithInvalidTokenReturnsError() {
        // Use an invalid access token
        String invalidAccessToken = "invalid_token";

        // Attempt to fetch user data using the invalid access token
        Response response = UserApi.getUser(invalidAccessToken);

        response.then().log().all();
        // Assert that the response status code indicates an unauthorized error

        assertEquals(400, response.statusCode());

        // Optional: If your API returns a specific error message for invalid tokens,
        // you can also assert that the expected error message is present in the response.
        String expectedErrorMessage = "request.invalid"; // Adjust based on your API's error message
        String actualErrorMessage = response.path("errors[0].Code");
        assertEquals(expectedErrorMessage, actualErrorMessage, "The error message did not match the expected one.");
    }
}