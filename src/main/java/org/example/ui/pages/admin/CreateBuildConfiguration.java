package org.example.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.ui.Selectors;

import static com.codeborne.selenide.Selenide.element;

public class CreateBuildConfiguration extends AdminPage {
    private SelenideElement urlInput = element(Selectors.byId("url"));
    private SelenideElement userNameInput = element(Selectors.byId("username"));
    private SelenideElement passwordInput = element(Selectors.byId("password"));
    private SelenideElement buildTypeName = element(Selectors.byId("buildTypeName"));

    private SelenideElement createFromUrlForm = element(Selectors.byId("createFromUrlForm"));
    private SelenideElement createProjectForm = element(Selectors.byId("createProjectForm"));
    public SelenideElement successMessage = element(Selectors.byClass("successMessage "));

    public CreateBuildConfiguration open(String projectId) {
        Selenide.open(ADMIN_PATH + projectId + "&showMode=createBuildTypeMenu#createFromUrl");
        waitUntilElementIsLoaded(createFromUrlForm);
        return this;
    }

    public CreateBuildConfiguration createBuildConfigurationByUrl(String url, String username, String password) {
        urlInput.sendKeys(url);
        userNameInput.clear();
        userNameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submit();
        waitUntilElementIsLoaded(createProjectForm);
        return this;
    }

    public CreateBuildConfiguration setupBuildConfiguration(String buildName) {
        buildTypeName.clear();
        buildTypeName.sendKeys(buildName);
        submit();
        waitUntilElementIsLoaded(successMessage);
        return this;

    }
}
