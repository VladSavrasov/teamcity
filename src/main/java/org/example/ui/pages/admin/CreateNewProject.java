package org.example.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.ui.Selectors;

import static com.codeborne.selenide.Selenide.element;

public class CreateNewProject extends AdminPage {
    private SelenideElement urlInput = element(Selectors.byId("url"));
    private SelenideElement projectNameInput = element(Selectors.byId("projectName"));
    private SelenideElement buildTypeNameInput = element(Selectors.byId("buildTypeName"));
    private SelenideElement createProjectForm = element(Selectors.byId("createProjectForm"));

    public CreateNewProject open(String locator) {
        Selenide.open(ADMIN_PATH + locator + "&showMode=createProjectMenu");
        waitUntilPageIsLoaded();
        return this;
    }

    public CreateNewProject createProjectByUrl(String url) {
        urlInput.sendKeys(url);
        submit();
        waitUntilElementIsLoaded(createProjectForm);
        return this;
    }

    public void setupProject(String projectName, String buildTypeName) {
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        submit();
    }
}
