package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payloads.Login;
import requests.LoginApi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginUtil {

    private static String accessToken;

    @BeforeAll
    public static void loginAndStoreToken() {
        Login loginPayload = new Login.Builder()
                .setEmail("leonard+b2bautomation@onoffapp.com")
                .setPassword("123456Ai")
                .build();

        // Use LoginApi.postLogin method to send the login request
        Response loginResponse = LoginApi.postLogin(loginPayload);


        loginResponse.then().log().all().statusCode(200);

        // Extracting the accessToken
        accessToken = loginResponse.path("body.accessToken");

    }

    @Test
    public void simpleLoginTest() {

        assertNotNull(accessToken, "Access token is null, login might have failed");
    }

    // Utility method to get stored accessToken for other tests
    public static String getAccessToken() {
        return accessToken;
    }
}