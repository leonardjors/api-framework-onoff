package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import requests.ApiAuthenticator;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class loginApiIT {

    @Test
    public void postLoginShouldWithValidCredentialsReturn200() {

// Get the access token. Login happens here if it hasn't happened yet.
        String accessToken = ApiAuthenticator.getAccessToken();

        // Use the access token for authorization in your API calls
        assertNotNull(accessToken);

        // Example API call using the access token
        // Response response = given().auth().oauth2(accessToken).get("/some-endpoint");
        // Assert your test conditions here
    }


    @Test
    public void postLoginShouldWithInvalidPasswordReturn209() {

        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";

        // Define request body for login
        String requestBody = "{\"email\": \"leonard+b2bautomation@onoffapp.com\", \"password\": \"123456\"}, \"recaptchaToken\": \"0539b06a-1cbe-45b5-9396-45684f8443b8\"}";

        // Send POST request to login endpoint and check response status code
        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .statusCode(209);
    }

    @Test
    public void postLoginShouldWithNotExistedUserReturn209() {

        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";

        // Define request body for login
        String requestBody = "{\"email\": \"leonard+b2bautomationapitest@onoffapp.com\", \"password\": \"123456\"}, \"recaptchaToken\": \"0539b06a-1cbe-45b5-9396-45684f8443b8\"}";

        // Send POST request to login endpoint and check response status code
        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .statusCode(209);
    }

    @Test
    public void postLoginShouldWithBlockedUserReturn209() {

        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";

        // Define request body for login
        String requestBody = "{\"email\": \"leonard+180124@onoffapp.com\", \"password\": \"123456\"}, \"recaptchaToken\": \"0539b06a-1cbe-45b5-9396-45684f8443b8\"}";

        // Send POST request to login endpoint and check response status code
        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .statusCode(209);
    }
}


