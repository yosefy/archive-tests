package org.bb.qa.archive.pageobjects.widgets.pagination;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Pagination extends PageObject {

    @FindBy(className = "pagination-menu")
    private WebElement root;

    @FindBy(css = ".pagination-menu item")
    private List<WebElement> items;

    @FindBy(css = ".pagination-menu item")
    private WebElement activeItem;

    public boolean isRootDisplayed() {
        return isElementDisplayed(root);
    }

    public WebElement getActiveItem() {
        return activeItem;
    }
}
