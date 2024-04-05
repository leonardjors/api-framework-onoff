package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static requests.BaseApi.baseUrl;

public class RemoveEmployeeApi {

    public static final String removeEmployeeEndpoint = baseUrl + "remove-employee";

    public static Response removeEmployee(String accessToken, String employeeId) {
        String jsonPayload = "{  \"employeeId\": \"" + employeeId + "\"}";

        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(removeEmployeeEndpoint);
    }
}
