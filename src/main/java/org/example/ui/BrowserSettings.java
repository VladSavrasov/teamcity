package org.example.ui;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserSettings {
    public static void setup(String browser) {
        Configuration.browser = browser;
        switch (browser) {
            case "firefox":
                setFirefoxOptions();
                break;

            case "chrome":
                setChromeOptions();
                break;
        }
    }

    private static void setFirefoxOptions() {
        Configuration.browserCapabilities = new FirefoxOptions();
        Configuration.browserCapabilities.setCapability("selenoid:options", getSelenoidOptoins());
    }

    private static void setChromeOptions() {
        Configuration.browserCapabilities = new ChromeOptions();
        Configuration.browserCapabilities.setCapability("selenoid:options", getSelenoidOptoins());
    }

    private static Map<String, Boolean> getSelenoidOptoins() {
        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableLog", true);
        return options;
    }
}
