package org.example.api;

import lombok.var;
import org.apache.http.HttpStatus;
import org.example.api.generators.DataProvidersForParametrizedTests;
import org.example.api.generators.RandomData;
import org.example.api.models.Project;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ProjectValidationTest extends BaseApiTest {

    @Test(dataProvider = "nums",dataProviderClass = DataProvidersForParametrizedTests.class)
    public void projectNameShouldBeEqualTo255Chars(int count) {

        var testData = testDataStorage.addTestData();

        testData.getProject().setName(RandomData.getStringOfSomeChar(count));


        Project project = checkedWithSuperUser.getProjectRequest()
                .create(testData.getProject());

        softy.assertThat(project.getName())
                .isEqualTo(testData.getProject().getName());
    }

    @Test(dataProvider = "badNums",dataProviderClass = DataProvidersForParametrizedTests.class)
    public void projectNameShouldNotBeMoreThanTo255Chars(int count) {

        var testData = testDataStorage.addTestData();

        testData.getProject().setName(RandomData.getStringOfSomeChar(count));

        uncheckedWithSuperUser.getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test()
    public void shouldNotCreateProjectsWithTheSameId() {

        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        var firstId = firstTestData.getProject().getId();
                secondTestData.getProject().setId(firstId);

        uncheckedWithSuperUser.getProjectRequest()
                .create(firstTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_OK);

        uncheckedWithSuperUser.getProjectRequest()
                .create(secondTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("Project ID \""+ firstId + "\" is already used by another project"));
    }

    @Test()
    public void shouldNotCreateProjectsWithTheSameName() {

        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        var firstName = firstTestData.getProject().getName();
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

