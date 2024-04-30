package org.example.api.requests.unchecked;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.requests.CRUDinterface;
import org.example.api.requests.Request;

import static io.restassured.RestAssured.given;

public class UncheckedUser extends Request implements CRUDinterface {
    private final static String USER_ENDPOINT = "/app/rest/users";

    public UncheckedUser(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Response create(Object o) {
        return given()
                .spec(spec)
                .body(o)
                .post(USER_ENDPOINT);
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public Response delete(String username) {
        return given()
                .spec(spec)
                .delete(USER_ENDPOINT + "/username:" + username);
    }
}
