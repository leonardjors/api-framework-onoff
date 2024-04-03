package payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private String recaptchaToken;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private Login(String email, String password, String recaptchaToken) {
        this.email = email;
        this.password = password;
        this.recaptchaToken = recaptchaToken;
    }

    // Static builder class
    public static class Builder {

        private String email;
        private String password;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }


        public Login build() {
            String recaptchaToken = "0539b06a-1cbe-45b5-9396-45684f8443b8";
            return new Login(email, password, recaptchaToken);
        }
    }
}