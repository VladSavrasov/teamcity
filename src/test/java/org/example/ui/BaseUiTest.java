package org.example.ui;

import com.codeborne.selenide.Configuration;
import org.example.api.BaseTest;
import org.example.api.config.Config;
import org.example.api.models.User;
import org.example.api.requests.checked.CheckedUser;
import org.example.api.spec.Specifications;
import org.example.ui.pages.LoginPage;
import org.testng.annotations.BeforeSuite;

public class BaseUiTest extends BaseTest {
    @BeforeSuite(alwaysRun=true)
    public void setupUiTests() {
        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote =  Config.getProperty("remote");
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder ="target/downloads";
        BrowserSettings.setup(Config.getProperty("browser"));
        Configuration.pageLoadStrategy = "eager";
        Configuration.remoteConnectionTimeout = 600000;
        Configuration.timeout = 180000;
    }
    public void loginAsUser(User user){
        new CheckedUser(Specifications.getSpec().superUserSpec())
                .create(user);
        new LoginPage().open().login(user);
    }
}
