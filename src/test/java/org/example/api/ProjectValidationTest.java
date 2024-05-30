package org.example.api;

import org.apache.http.HttpStatus;
import org.example.api.generators.DataProvidersForParametrizedTests;
import org.example.api.generators.RandomData;
import org.example.api.generators.TestData;
import org.example.api.models.Project;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ProjectValidationTest extends BaseApiTest {

    @Test(groups = "sistemtests",dataProvider = "nums",dataProviderClass = DataProvidersForParametrizedTests.class)
    public void projectNameShouldBeEqualTo255Chars(int count) {

        TestData testData = testDataStorage.addTestData();

        testData.getProject().setName(RandomData.getStringOfSomeChar(count));


        Project project = checkedWithSuperUser.getProjectRequest()
                .create(testData.getProject());

        softy.assertThat(project.getName())
                .isEqualTo(testData.getProject().getName());
    }

    @Test(groups = "sistemtests",dataProvider = "badNums",dataProviderClass = DataProvidersForParametrizedTests.class)
    public void projectNameShouldNotBeMoreThanTo255Chars(int count) {

        TestData testData = testDataStorage.addTestData();

        testData.getProject().setName(RandomData.getStringOfSomeChar(count));

        uncheckedWithSuperUser.getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = "sistemtests")
    public void shouldNotCreateProjectsWithTheSameId() {

        TestData firstTestData = testDataStorage.addTestData();
        TestData secondTestData = testDataStorage.addTestData();

        String firstId = firstTestData.getProject().getId();
                secondTestData.getProject().setId(firstId);

        uncheckedWithSuperUser.getProjectRequest()
                .create(firstTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_OK);

        uncheckedWithSuperUser.getProjectRequest()
                .create(secondTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("Project ID \""+ firstId + "\" is already used by another project"));
    }

    @Test(groups = "sistemtests")
    public void shouldNotCreateProjectsWithTheSameName() {

        TestData firstTestData = testDataStorage.addTestData();
        TestData secondTestData = testDataStorage.addTestData();

        String firstName = firstTestData.getProject().getName();
        secondTestData.getProject().setName(firstName);

        uncheckedWithSuperUser.getProjectRequest()
                .create(firstTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_OK);

        uncheckedWithSuperUser.getProjectRequest()
                .create(secondTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("Project with this name already exists: "+ firstName));
    }
}

