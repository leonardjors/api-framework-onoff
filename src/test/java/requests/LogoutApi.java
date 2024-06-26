package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Login;

import static io.restassured.RestAssured.given;

public class LogoutApi extends BaseApi {
    private static final String apiUrlLogout = baseUrl + "logout";

    public static Response logout(String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType(ContentType.JSON)
                .when()
                .post(apiUrlLogout);
    }
}

