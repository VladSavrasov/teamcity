package org.example.api.generators;

import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Data;
import org.example.api.models.BuildType;
import org.example.api.models.NewProjectDescription;
import org.example.api.models.User;
import org.example.api.requests.unchecked.UncheckedProject;
import org.example.api.requests.unchecked.UncheckedUser;
import org.example.api.spec.Specifications;

@Builder
@Data
public class TestData {
    private User user;
    private NewProjectDescription project;
    private BuildType buildType;

    public void delete(){
        RequestSpecification spec = Specifications.getSpec().superUserSpec();
        new UncheckedProject(spec).delete(project.getId());
        new UncheckedUser(spec).delete(user.getUsername());
    }
}
