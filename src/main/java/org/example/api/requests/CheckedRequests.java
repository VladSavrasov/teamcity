package org.example.api.requests;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.requests.checked.CheckedUser;
import org.example.api.requests.checked.CheckedBuildConfig;
@Getter
public class CheckedRequests {

    private CheckedUser userRequest;
    private CheckedProject projectRequest;
    private CheckedBuildConfig buildConfigRequest;

    public CheckedRequests(RequestSpecification spec){
        this.userRequest = new CheckedUser(spec);
        this.buildConfigRequest = new CheckedBuildConfig(spec);
        this.projectRequest = new CheckedProject(spec);
    }
}
