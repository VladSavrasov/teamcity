package org.example.ui.pages.favorite;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.example.ui.Selectors;
import org.example.ui.elements.ProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.elements;

public class ProjectsPage extends FavoritesPage {
    private static final String FAVORITE_PROJECTS_URL = "/favorite/projects";
    private ElementsCollection subprojects = elements(Selectors.byClass("Subproject__container--Px"));

    public ProjectsPage open(){
        Selenide.open(FAVORITE_PROJECTS_URL);
        waitUntilFavoritesPageIsLoaded();
    return this;
    }

    public List<ProjectElement> getSubProjects(){
        return generatePageElements(subprojects, ProjectElement::new);
    }
}