package org.example.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.ui.Selectors;
import org.example.ui.pages.Page;

import static com.codeborne.selenide.Selenide.element;
import static java.lang.Thread.sleep;

public class CreateNewProject extends Page {
    private SelenideElement urlInput = element(Selectors.byId("url"));
    private SelenideElement projectNameInput = element(Selectors.byId("projectName"));
    private SelenideElement buildTypeNameInput = element(Selectors.byId("buildTypeName"));

    public CreateNewProject open(String locator) {
        Selenide.open("/admin/createObjectMenu.html?projectId=" + locator + "&showMode=createProjectMenu");
        waitUntilPageIsLoaded();
        return this;
    }

    public CreateNewProject createProjectByUrl(String url) throws InterruptedException {
        urlInput.sendKeys(url);
        submit();
        sleep(3_000);
        return this;
    }
    public void setupProject(String projectName,String buildTypeName){
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        submit();
    }
}
