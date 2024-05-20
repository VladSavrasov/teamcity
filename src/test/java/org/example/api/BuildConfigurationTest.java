package org.example.api;

import lombok.var;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.requests.checked.CheckedUser;
import org.example.api.spec.Specifications;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {
    @Test
    public void buildConfigurationTest() {
        var testData = testDataStorage.addTestData();
        new CheckedUser(Specifications.getSpec().superUserSpec())
                .create(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec().
                auhSpec(testData.getUser())).
                create(testData.getProject());

        softy.assertThat(project.getId())
                .isEqualTo(testData.getProject().getId());
    }
}
