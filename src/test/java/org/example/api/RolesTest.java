package org.example.api;

import org.apache.http.HttpStatus;
import org.example.api.enums.Role;
import org.example.api.generators.TestData;
import org.example.api.generators.TestDataGenerator;
import org.example.api.models.BuildType;
import org.example.api.models.Project;
import org.example.api.requests.UncheckedRequests;
import org.example.api.requests.checked.CheckedBuildConfig;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.spec.Specifications;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class RolesTest extends BaseApiTest {
    @Test(groups = "sistemtests")
    public void unautorizedUserShouldNotHaveRightToCreateProject() {
        TestData testData = testDataStorage.addTestData();

        new UncheckedRequests(Specifications.getSpec().unauthSpec()).getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body(Matchers.emptyString());

        uncheckedWithSuperUser.getProjectRequest()
                .get(testData.getProject().getId())
                .then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(Matchers.containsString("No project found by locator 'count:1,id:" + testData.getProject().getId() + "'"));

    }
    @Test(groups = "sistemtests")
    public void systemAdminShouldHaveRightToCreateProject (){
        TestData testData = testDataStorage.addTestData();

        testData.getUser().setRoles(TestDataGenerator.generateRoles(Role.SYSTEM_ADMIN,"g"));

        checkedWithSuperUser.getUserRequest().create(testData.getUser());

        Project project = new CheckedProject(Specifications.getSpec().
                auhSpec(testData.getUser())).
                create(testData.getProject());

        softy.assertThat(project.getId())
                .isEqualTo(testData.getProject().getId());
    }

    @Test(groups = "sistemtests")
    public void projectAdminTestShouldHaveRightToCreateBuildConfigToHisProject(){
        TestData testData = testDataStorage.addTestData();

        checkedWithSuperUser.getProjectRequest()
                        .create(testData.getProject());


        testData.getUser().setRoles(TestDataGenerator
                .generateRoles(Role.PROJECT_ADMIN,"p:" + testData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        BuildType buildConfig = new CheckedBuildConfig(Specifications.getSpec()
                .auhSpec(testData.getUser()))
                .create(testData.getBuildType());

        softy.assertThat(buildConfig.getId())
                .isEqualTo(testData.getBuildType().getId());
    }

    @Test(groups = "sistemtests")
    public void projectAdminTestShouldNotHaveRightToCreateBuildConfigToAnotherProject(){

        TestData firstTestData = testDataStorage.addTestData();
        TestData secondTestData = testDataStorage.addTestData();

        UncheckedRequests secondUserRequest = new UncheckedRequests(Specifications.getSpec().auhSpec(secondTestData.getUser()));

        checkedWithSuperUser.getProjectRequest().create(firstTestData.getProject());
        checkedWithSuperUser.getProjectRequest().create(secondTestData.getProject());

        firstTestData.getUser().setRoles(TestDataGenerator
                .generateRoles(Role.PROJECT_ADMIN,"p:" + firstTestData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(firstTestData.getUser());

        secondTestData.getUser().setRoles(TestDataGenerator
                .generateRoles(Role.PROJECT_ADMIN,"p:" + secondTestData.getProject().getId()));

        checkedWithSuperUser.getUserRequest()
                .create(secondTestData.getUser());

        secondUserRequest.getBuildConfigRequest()
                .create(firstTestData.getBuildType()).then()
                .assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .body(Matchers.containsString("You do not have enough permissions to edit project with id: "
                        + firstTestData.getProject().getId()));

    }
}
