package org.example.ui;

import org.example.api.generators.TestData;
import org.example.api.models.BuildType;
import org.example.api.models.Project;
import org.example.api.requests.checked.CheckedBuildConfig;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.spec.Specifications;
import org.example.ui.pages.admin.CreateBuildConfiguration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class CreateBuildConfigurationTest extends BaseUiTest {
    @Test(groups = "sistemtests")
    public void autorizedUserShouldBeAbleCreateNewBuildConfiguration() {
        TestData testData = testDataStorage.addTestData();
        String url = "https://github.com/AlexPshe/spring-core-for-qa";
        loginAsUser(testData.getUser());

        Project project = new CheckedProject(Specifications.getSpec()
                .auhSpec(testData.getUser()))
                .create(testData.getProject());

        new CreateBuildConfiguration()
                .open(project.getId())
                .createBuildConfigurationByUrl(url, testData.getUser().getUsername(),
                        testData.getUser().getPassword())
                .setupBuildConfiguration(testData.getBuildType().getName())
                .successMessage.shouldHave(text(testData.getBuildType().getName()));

        BuildType buildConfig = new CheckedBuildConfig(Specifications.getSpec()
                .auhSpec(testData.getUser()))
                .get(testData.getBuildType().getName());

        softy.assertThat(project.getId())
                .isEqualTo(buildConfig.getProject().getId());
    }
}
