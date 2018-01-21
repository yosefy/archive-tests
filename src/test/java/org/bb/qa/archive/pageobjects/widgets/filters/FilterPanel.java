package org.bb.qa.archive.pageobjects.widgets.filters;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilterPanel extends PageObject {

    @FindBy(className = "filter-panel")
    private WebElement root;

    public FilterMenu menu = new FilterMenu();
    public FilterTags tags = new FilterTags();
}
