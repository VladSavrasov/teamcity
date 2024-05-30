package org.example.ui.pages.agents;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.example.ui.Selectors;
import org.example.ui.pages.Page;

import static com.codeborne.selenide.Selenide.element;
@Getter
public class UnautorizedPage extends Page {
    public static final String AGENTS_PATH = "/agents/";
    private SelenideElement authorizeAgent = element(Selectors.byClass("AuthorizeAgent__authorizeAgent--Xr"));
    private SelenideElement agents = element(Selectors.byDataHintContainerId("header-agents-active"));
    private SelenideElement agentsOverviewPage = element(Selectors.byClass("AgentsOverviewPage__page--iL"));
    private SelenideElement commonFormbutton = authorizeAgent.ancestor(".pageBG").$(".CommonForm__button--Nb");
    private SelenideElement ringPopup = element(Selectors.byDataTest("ring-popup"));

    public UnautorizedPage open() {
        Selenide.open(AGENTS_PATH + "unauthorized");
        waitUntilElementIsLoaded(authorizeAgent);
        return this;
    }
    public UnautorizedPage autorizeClick(){
        authorizeAgent.click();
        return this;
    }

    public UnautorizedPage autorizeAgent() {
        autorizeClick().
        waitUntilElementIsLoaded(ringPopup);
        commonFormbutton.click();
        waitUntilPageIsLoaded();
        return this;
    }
}
