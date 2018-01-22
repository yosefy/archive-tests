package org.bb.qa.archive.pageobjects.widgets.videobox;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Controls extends PageObject {

    @FindBy(className = "mediaplayer__controls")
    private WebElement root;

    @FindBy(className = "mediaplayer__timecode")
    private WebElement timecode;

    public boolean isRootDisplayed() {
        return isElementDisplayed(root);
    }

}
