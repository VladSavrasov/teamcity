package org.example.api.requests.checked;

import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.example.api.models.ServerAuthDettings.ServerAuthSettings;
import org.example.api.requests.Request;

import static io.restassured.RestAssured.given;

public class ManageServerAuthSettingsReq extends Request {

    public ManageServerAuthSettingsReq(RequestSpecification spec) {
        super(spec);
    }

    public static final String AUTHSETTINGS_ENDPOINT = "/app/rest/server/authSettings";

    public void putPermissionsTrue(ServerAuthSettings response) {

        given(spec)
                .body(response)
                .put(AUTHSETTINGS_ENDPOINT)
                .then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    public ServerAuthSettings getPermissionsTrue() {

        return given(spec)
                .get(AUTHSETTINGS_ENDPOINT)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(ServerAuthSettings.class);
    }
    public ServerAuthSettings checkPermissions() {
        ServerAuthSettings authenticationSettings = getPermissionsTrue();
        if (!authenticationSettings.isPerProjectPermissions()) {
            authenticationSettings.setPerProjectPermissions(true);
            putPermissionsTrue(authenticationSettings);
            authenticationSettings = getPermissionsTrue();
        }
        return authenticationSettings;
    }
}
