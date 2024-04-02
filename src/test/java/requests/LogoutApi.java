package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Login;

import static io.restassured.RestAssured.given;

public class LogoutApi extends BaseApi {
    private static final String apiUrl = baseUrl + "logout";

    public static Response postLogout(Login payload) {
        return null;
    }
}

