package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {

    public static final String getUserEndpoint = baseUrl + "get-user";

    public static Response getUser(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(getUserEndpoint);
    }
}
