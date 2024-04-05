package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetEmployeesApi extends BaseApi {

    public static final String getEmployeesEndpoint = baseUrl + "get-employees";

    public static Response getEmployees(String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when()
                .get(getEmployeesEndpoint);
    }
}
