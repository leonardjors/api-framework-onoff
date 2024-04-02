package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

public class getEmployeesApiIT {
    private String authToken;

    @Test
    public void getEmployeesReturn200() {
        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";

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

// Make sure accessToken is not null
        if (authToken == null) {
            throw new RuntimeException("Access token is null. Login test might have failed.");
        }

        given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/get-employees")
                .then()
                .statusCode(200)
                .body("$", is(not(emptyArray()))); // Assert that response body is not an empty array
    }

    @Test
    public void getEmployeesReturnsCorrespondingAmountOfEmployees() {
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

        if (authToken == null) {
            throw new RuntimeException("Access token is null. Login test might have failed.");
        }

        given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .get("/get-employees")
                .then()
                .statusCode(200)
                .body("body.totalItems", equalTo(4));
    }

}
