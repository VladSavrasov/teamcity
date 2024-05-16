package org.example.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.var;
import org.example.ui.Selectors;
import org.example.ui.elements.PageElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;

public abstract class Page {
    private SelenideElement submitButton = element(Selectors.byType("submit"));
    private SelenideElement savingWaitingMarker = element(Selectors.byId("saving"));
    private SelenideElement pageWaitingMarker = element(Selectors.byDataTest("ring-loader"));

    public void submit() {
        submitButton.click();
        waitUntilDataIsSaved();
    }

    private void waitUntilDataIsSaved() {
        savingWaitingMarker.shouldNotBe(visible, Duration.ofSeconds(30));
    }

    protected void waitUntilPageIsLoaded() {
        pageWaitingMarker.shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public <T extends PageElement> ArrayList<T> generatePageElements(ElementsCollection collection,
                                                                      Function<SelenideElement, T> creator) {
        var elements = new ArrayList<T>();

        collection.forEach(webElement -> elements.add(creator.apply(webElement)));
        return elements;
    }
}
