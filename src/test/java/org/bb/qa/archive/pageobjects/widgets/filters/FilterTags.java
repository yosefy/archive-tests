package org.bb.qa.archive.pageobjects.widgets.filters;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilterTags extends PageObject {

    @FindBy(className = "filter-tags")
    private WebElement root;

    @FindBy(className = "filter-tag")
    private List<WebElement> items;
}
