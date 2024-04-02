package payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty
    private String accessToken;

    // Getter
    public String getAccessToken() {
        return accessToken;
    }
}
