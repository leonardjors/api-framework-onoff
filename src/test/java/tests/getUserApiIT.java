package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.ApiAuthenticator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class getUserApiIT {

    private String accessToken;

    @BeforeEach
    public void setUp() {
        // Get the accessToken before each test
        accessToken = ApiAuthenticator.getAccessToken();
        assertNotNull(accessToken, "Access token is null, login failed.");
    }

    @Test
    public void getUserReturn200() {
        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";


        accessToken = ApiAuthenticator.loginAndGetAccessToken("leonard+051223@onoffapp.com", "123456Ai", "0539b06a-1cbe-45b5-9396-45684f8443b8");

        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/get-user")
                .then()
                .statusCode(200);


    }

    @Test
    public void testSomeApiEndpoint() {
        // Get the access token. Login happens here if it hasn't happened yet.
        String accessToken = ApiAuthenticator.getAccessToken();

        // Use the access token for authorization in your API calls
        assertNotNull(accessToken);

        // Example API call using the access token
        // Response response = given().auth().oauth2(accessToken).get("/some-endpoint");
        // Assert your test conditions here
    }

    @Test
    public void getUserReturnsCorrespondingUserDetails() {
        RestAssured.baseURI = "https://b2b-staging.onoffapp.net/b2b-console";


        // Define request body for login


        given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/get-user")
                .then()
                .body("body.email", equalTo("leonard+b2bautomation@onoffapp.com"))
                .body("body.firstName", equalTo("Test"))
                .body("body.lastName", equalTo("Automation"));
    }

}
