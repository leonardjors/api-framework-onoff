package requests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Login;

import static io.restassured.RestAssured.given;

public class LoginApi extends BaseApi {

   public static final String apiUrl = baseUrl + "login";

    public static Response postLogin(Login payload){
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(apiUrl);
    }
}
