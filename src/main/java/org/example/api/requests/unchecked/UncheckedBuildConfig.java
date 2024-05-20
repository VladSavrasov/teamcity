package org.example.api.requests.unchecked;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.api.requests.CRUDinterface;
import org.example.api.requests.Request;

import static io.restassured.RestAssured.given;

public class UncheckedBuildConfig extends Request implements CRUDinterface {
    private static final String BUILD_CONFIG_ENDPOINT = "/app/rest/buildTypes/";

    public UncheckedBuildConfig(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Response create(Object o) {
        return given().spec(spec)
                .body(o)
                .post(BUILD_CONFIG_ENDPOINT);
    }

    @Override
    public Response get(String name) {
        return given()
                .spec(spec)
                .get(BUILD_CONFIG_ENDPOINT + name);
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return given().spec(spec)
                .delete(BUILD_CONFIG_ENDPOINT + "id:" + id);
    }
}
