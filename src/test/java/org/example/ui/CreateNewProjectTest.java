package org.example.ui;

import lombok.var;
import org.example.api.requests.checked.CheckedProject;
import org.example.api.spec.Specifications;
import org.example.ui.pages.favorite.ProjectsPage;
import org.example.ui.pages.admin.CreateNewProject;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class CreateNewProjectTest extends BaseUiTest {
    @Test
    public void autorizedUserShouldBeAbleCreateNewProject() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/AlexPshe/spring-core-for-qa";
        loginAsUser(testData.getUser());
        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubProjects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(text(testData.getProject().getName()));

        new CheckedProject(Specifications.getSpec()
                .auhSpec(testData.getUser()))
                .get(testData.getProject().getId());
    }
}
