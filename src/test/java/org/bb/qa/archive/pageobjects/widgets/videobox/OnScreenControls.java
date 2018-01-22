package org.bb.qa.archive.pageobjects.widgets.videobox;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnScreenControls extends PageObject {

    @FindBy(className = "mediaplayer__onscreen-play")
    private WebElement play;

    public boolean isPlayReady() {
        return play.isDisplayed() && play.isEnabled();
    }

    public boolean isPlayNotVisible() {
        return isElementNotVisible(play);
    }

}
