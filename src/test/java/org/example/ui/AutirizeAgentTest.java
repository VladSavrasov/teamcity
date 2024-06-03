package org.example.ui;

import org.example.api.generators.TestData;
import org.example.ui.pages.agents.UnautorizedPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class AutirizeAgentTest extends BaseUiTest {
    @Test(groups = "sistemtests")
    public void autorizeAgentTest() {
        TestData testData = testDataStorage.addTestData();
        loginAsUser(testData.getUser());
        new UnautorizedPage()
                .open()
                .autorizeAgent()
                .getAgents().shouldHave(text("1"));

    }
}
