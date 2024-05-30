package org.example.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.example.ui.Selectors;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;

@Getter
public class StartUpPage extends Page {
    private SelenideElement acceptLicense = element("input[id='accept']");
    private SelenideElement proceedButton = element("input[id='proceedButton']");
    private SelenideElement header = element(Selectors.byId("header"));

    public StartUpPage open() {
        Selenide.open("/");
        return this;
    }
    protected void waitUntilProceedButtonIsLoaded() {
        proceedButton.shouldBe(visible, Duration.ofMinutes(10));
    }

    public StartUpPage setupTeamCityServer() {
        waitUntilPageIsLoaded();
        waitUntilElementIsLoaded(proceedButton);
        proceedButton.click();
        waitUntilPageIsLoaded();
        waitUntilElementIsLoaded(proceedButton);
        proceedButton.click();
        waitUntilPageIsLoaded();
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
        acceptLicense.scrollTo();
        acceptLicense.click();
        submit();
        waitUntilPageIsLoaded();
        waitUntilElementIsLoaded(header);
        return this;
    }
}

