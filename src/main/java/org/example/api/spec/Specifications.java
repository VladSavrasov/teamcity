package org.example.api.spec;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import org.example.api.config.Config;
import org.example.api.models.User;

public class Specifications {
    private static Specifications spec;

    private Specifications() {
    }

    public static Specifications getSpec() {
        if (spec == null) {
            spec = new Specifications();
        }
        return spec;
    }

    private RequestSpecBuilder reqBuilder() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(("http://" + Config.getProperty("host")));
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.setAccept(ContentType.JSON);
        return requestBuilder;
    }

    public RequestSpecification unauthSpec() {
        RequestSpecBuilder requestBuilder = reqBuilder();

        return requestBuilder.build();
    }

    public RequestSpecification auhSpec(User user) {
        RequestSpecBuilder requestBuilder = reqBuilder();
        requestBuilder.setBaseUri("http://" + user.getUsername() + ":"
                + user.getPassword() + "@" + Config.getProperty("host"));
        return requestBuilder.build();
    }

    public RequestSpecification superUserSpec() {
        RequestSpecBuilder requestBuilder = reqBuilder();
        requestBuilder.setBaseUri("http://:" + Config.getProperty("superUserToken") + "@" + Config.getProperty("host"));
        return requestBuilder.build();
    }
}
