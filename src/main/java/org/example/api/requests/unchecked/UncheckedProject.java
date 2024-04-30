package org.example.api.requests.unchecked;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.requests.CRUDinterface;
import org.example.api.requests.Request;

import static io.restassured.RestAssured.given;

public class UncheckedProject extends Request implements CRUDinterface {
    private static final String PROJECT_ENDPOINT = "/app/rest/projects";

    public UncheckedProject(RequestSpecification spec) {
        super(spec);
    }


    @Override
    public Response create(Object o) {
        return given()
                .spec(spec)
                .body(o)
                .post(PROJECT_ENDPOINT);
    }

    @Override
    public Response get(String id) {
        return given()
                .spec(spec)
                .get(PROJECT_ENDPOINT + "/id:" + id);
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return given()
                .spec(spec)
                .delete(PROJECT_ENDPOINT + "/id:" + id);
    }
}
