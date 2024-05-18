package org.example.ui;

import lombok.var;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.spec.Specifications;
import org.example.ui.pages.admin.CreateBuildConfiguration;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class CreateBuildConfigurationTest extends BaseUiTest {
    @Test
    public void autorizedUserShouldBeAbleCreateNewBuildConfiguration() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/AlexPshe/spring-core-for-qa";
        loginAsUser(testData.getUser());

        var project = new CheckedProject(Specifications.getSpec().
                auhSpec(testData.getUser())).
                create(testData.getProject());

        new CreateBuildConfiguration()
                .open(project.getId())
                .createBuildConfigurationByUrl(url, testData.getUser().getUsername(),
                        testData.getUser().getPassword())
                .setupBuildConfiguration(testData.getBuildType().getName())
                .successMessage.shouldHave(text(testData.getBuildType().getName()));
    }
}
