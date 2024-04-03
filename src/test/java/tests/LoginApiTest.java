package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import payloads.Login;
import requests.LoginApi;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginApiTest {

    @Test
    public void testLoginWithInvalidCredentials() {
        // Create a login payload with invalid credentials
        Login loginPayload = new Login.Builder()
                .setEmail("leonard+b2bautomation@onoffapp.com")
                .setPassword("wrongPassword")
                .build();

        // Attempt to login with invalid credentials
        Response loginResponse = LoginApi.postLogin(loginPayload);

        loginResponse.then().log().all();

        // Assert that the response status code indicates a client error
        assertThat(loginResponse.statusCode(), is(209));

        String errorMessage = loginResponse.path("errors[0].Code");
        assertThat(errorMessage, is("credentials.invalid"));
    }

    @Test
    public void testLoginWithNonExistentUserData() {
        // Create a login payload with non-existent user data
        Login loginPayload = new Login.Builder()
                .setEmail("nonexistent@example.com")
                .setPassword("anyPassword")
                .build();

        // Attempt to login with non-existent user data
        Response loginResponse = LoginApi.postLogin(loginPayload);

        loginResponse.then().log().all();

        assertThat(loginResponse.statusCode(), is(209));

        String errorMessage = loginResponse.path("errors[0].Code");
        assertThat(errorMessage, is("credentials.invalid"));
    }
}
