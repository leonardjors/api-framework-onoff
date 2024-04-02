package requests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Login;
import payloads.LoginResponse;

public class ApiAuthenticator {
    private static String accessToken;
    public static String loginAndGetAccessToken(String username, String password, String recaptchaToken) {
        Login loginPayload = new Login(username, password, recaptchaToken);
        Response response = postLogin(loginPayload);

        if (response.getStatusCode() == 200) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                LoginResponse loginResponse = objectMapper.readValue(response.getBody().asString(), LoginResponse.class);
                accessToken = loginResponse.getAccessToken();
                return accessToken;
            } catch (Exception e) {
                throw new RuntimeException("Error parsing login response", e);
            }
        } else {
            throw new RuntimeException("Login request failed with status code: " + response.getStatusCode());
        }
    }

    private static Response postLogin(Login payload) {
        return io.restassured.RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(LoginApi.apiUrl);
    }

    public static String getAccessToken() {
        return accessToken;
    }
}