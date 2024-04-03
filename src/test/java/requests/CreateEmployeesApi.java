package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateEmployeesApi extends BaseApi {

    public static final String createEmployeeEndpoint = baseUrl + "create-employees";

    public static Response createEmployees(String accessToken, Object employeePayload) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .body(employeePayload)
                .when()
                .post(createEmployeeEndpoint);
    }
}
