package org.example.ui.elements;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import org.example.ui.Selectors;
@Getter
public class ProjectElement extends PageElement{
    private final SelenideElement icon;
    private final SelenideElement header;

    public ProjectElement(SelenideElement element){
        super(element);
        this.header = findElement(Selectors.byDataTest("subproject"));
        this.icon = findElement("svg");
    }
}
