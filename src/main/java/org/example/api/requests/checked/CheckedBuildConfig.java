package org.example.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.api.models.BuildType;
import org.example.api.requests.CRUDinterface;
import org.example.api.requests.Request;
import org.example.api.requests.unchecked.UncheckedBuildConfig;

public class CheckedBuildConfig extends Request implements CRUDinterface {
    public CheckedBuildConfig(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public BuildType create(Object o) {
        return new UncheckedBuildConfig(spec).create(o)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BuildType.class);
    }

    @Override
    public BuildType get(String name) {
        return new UncheckedBuildConfig(spec)
                .get(name)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(BuildType.class);
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public String delete(String id) {
        return new UncheckedBuildConfig(spec)
                .delete(id).then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().asString();

    }
}
