package org.example.api.requests.checked;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.example.api.models.User;
import org.example.api.spec.Specifications;

public class AuthRequest {
    private User user;

    public AuthRequest(User user) {
        this.user = user;
    }

    public String getCsrfToken() {
        return RestAssured
                .given(Specifications.getSpec().auhSpec(user))
                .get("/authenticationTest.html?csrf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }
}
