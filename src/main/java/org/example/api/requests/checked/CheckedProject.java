package org.example.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.api.models.Project;
import org.example.api.requests.CRUDinterface;
import org.example.api.requests.Request;
import org.example.api.requests.unchecked.UncheckedProject;

public class CheckedProject extends Request implements CRUDinterface {


    public CheckedProject(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Project create(Object o) {
        return new UncheckedProject(spec).create(o)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Project get(String id) {
        return new UncheckedProject(spec)
                .get(id)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public String delete(String id) {
        return new UncheckedProject(spec)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().asString();
    }
}
