package org.bb.qa.archive.pageobjects.widgets;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectionHeader extends PageObject {

    @FindBy(className = "section-header__title")
    private WebElement header;

    @FindBy(className = "section-header__subtitle")
    private WebElement subHeader;

    public boolean isHeaderDisplayed() {
        return isElementDisplayed(header);
    }

    public boolean isSubHeaderDisplayed() {
        return isElementDisplayed(subHeader);
    }

    public String getHeaderText() {
        return header.getText();
    }

    public String getSubHeaderText() {
        return subHeader.getText();
    }
}
