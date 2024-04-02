package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class createEmployeesApiIT {

    private String authToken;


    @Test
    public void createEmployeesWithCorrectDetailsReturn200() {
        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";

        String requestBody = "{\"email\": \"leonard+180124@onoffapp.com\", \"password\": \"123456Ai\"}, \"recaptchaToken\": \"0539b06a-1cbe-45b5-9396-45684f8443b8\"}";

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

// Make sure accessToken is not null
        if (authToken == null) {
            throw new RuntimeException("Access token is null. Login test might have failed.");
        }
        int randomNumber = ThreadLocalRandom.current().nextInt(10000);

        // Define email address with random number
        String email = "leonard" + randomNumber + "@onoffapp.com";

        // Define request body for creating employee
        String employeeRequestBody = "{\"employees\":[{\"firstName\":\"Leonard\",\"lastName\":\"Test\",\"email\":\"" + email + "\"}]}";

        // Send POST request to create-employees endpoint with access token and payload
        given()
                .header("Authorization", "Bearer " + authToken)
                .header("Content-Type", "application/json")
                .body(employeeRequestBody)
                .when()
                .post("/create-employees")
                .then()
                .statusCode(200);

    }

}