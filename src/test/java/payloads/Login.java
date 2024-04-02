package payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    @JsonProperty
    private static String username = "leonard@onoffapp.com";
    @JsonProperty
    private static String password = "123456Ai";
    @JsonProperty
    private static String recaptchaToken = "0539b06a-1cbe-45b5-9396-45684f8443b8";

    public Login(String username, String password, String recaptchaToken) {
        Login.username = username;
        Login.password = password;
        Login.recaptchaToken = recaptchaToken;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }
}
