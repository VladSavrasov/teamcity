package org.example.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.api.models.User;
import org.example.api.requests.CRUDinterface;
import org.example.api.requests.Request;
import org.example.api.requests.unchecked.UncheckedUser;

public class CheckedUser extends Request implements CRUDinterface {

    public CheckedUser(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public User create(Object o) {
        return new UncheckedUser(spec)
                .create(o).then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(User.class);
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
    public String delete(String id) {
        return new UncheckedUser(spec)
                .delete(id).then()
                .assertThat().statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().asString();
    }
}
