package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class logoutApiIT {
    private String authToken;

    @Test
    public void postLogoutFromAccountReturn200() {
        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";

        // Define request body for login
        String requestBody = "{\"email\": \"leonard+b2bautomation@onoffapp.com\", \"password\": \"123456Ai\"}, \"recaptchaToken\": \"0539b06a-1cbe-45b5-9396-45684f8443b8\"}";

        // Send POST request to login endpoint and retrieve authentication token
        authToken = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .path("body.accessToken");


        // Make sure authToken is not null
        if (authToken == null) {
            throw new RuntimeException("Authentication token is null. Login test might have failed.");
        }

        // Send POST request to logout endpoint with authentication token
        given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .post("/logout")
                .then()
                .statusCode(200);
    }

    @Test
    public void postLogoutFromAlreadyLoggedOutReturn400() {
        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";



        // Send POST request to logout endpoint with authentication token
        given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .post("/logout")
                .then()
                .statusCode(400);
    }

}
