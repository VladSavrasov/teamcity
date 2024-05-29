package org.example.api;

import org.example.api.generators.TestData;
import org.example.api.models.Project;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.requests.checked.CheckedUser;
import org.example.api.spec.Specifications;
import org.testng.annotations.Test;

public class BuildConfigurationTest extends BaseApiTest {
    @Test(groups = "sistemtests")
    public void buildConfigurationTest() {
        TestData testData = testDataStorage.addTestData();
        new CheckedUser(Specifications.getSpec().superUserSpec())
                .create(testData.getUser());

        Project project = new CheckedProject(Specifications.getSpec().
                auhSpec(testData.getUser())).
                create(testData.getProject());

        softy.assertThat(project.getId())
                .isEqualTo(testData.getProject().getId());
    }
}
