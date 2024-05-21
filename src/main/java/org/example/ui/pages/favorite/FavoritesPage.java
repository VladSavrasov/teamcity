package org.example.ui.pages.favorite;

import com.codeborne.selenide.SelenideElement;
import org.example.ui.Selectors;
import org.example.ui.pages.Page;

import static com.codeborne.selenide.Selenide.element;

public class FavoritesPage extends Page {
    private SelenideElement header = element(Selectors.byClass("ProjectPageHeader__title--ih"));

    public void waitUntilFavoritesPageIsLoaded() {
        waitUntilPageIsLoaded();
        waitUntilElementIsLoaded(header);
    }
}