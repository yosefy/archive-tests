package org.bb.qa.archive.pageobjects.widgets.pagination;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPageHeader extends PageObject {

    @FindBy(className = "pagination-results")
    private WebElement results;

    public boolean isResultsDisplayed() {
        return isElementDisplayed(results);
    }

    public String getResultsText() {
        return results.getText();
    }
}
