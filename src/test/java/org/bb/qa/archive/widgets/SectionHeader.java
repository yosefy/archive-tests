package org.bb.qa.archive.widgets;

import org.bb.qa.archive.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SectionHeader extends PageObject {

    @FindBy(className = "section-header")
    public WebElement root;


}
