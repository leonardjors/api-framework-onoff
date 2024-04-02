package requests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Login;

import static io.restassured.RestAssured.given;

public class LoginApi extends BaseApi {

    public static final String apiUrl = baseUrl + "login";

}