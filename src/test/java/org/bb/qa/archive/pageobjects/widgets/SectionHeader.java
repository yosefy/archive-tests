package org.bb.qa.archive.pageobjects.widgets;

import org.bb.qa.archive.pageobjects.PageObject;
import org.bb.qa.common.element.ElementUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SectionHeader extends PageObject {

    @FindBy(className = "section-header__title")
    private WebElement header;

    @FindBy(className = "section-header__subtitle")
    private WebElement subHeader;

    @FindBy(css = ".section-header__menu .item")
    private List<WebElement> menuItems;

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

    public boolean noMenuItemIsActive() {
//        for (WebElement webElement : menuItems) {
//           if (ElementUtils.hasCssClass(webElement, "active")) {
//               return true;
//           }
//        }
//        return false;

        return menuItems.stream()
                .anyMatch(x -> ElementUtils.hasCssClass(x, "active"));
    }
}
