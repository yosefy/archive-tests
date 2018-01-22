package org.bb.qa.archive.pageobjects.widgets.videobox;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.function.Function;

public class Player extends PageObject {

    public Controls controls;
    public OnScreenControls onScreenControls;

    @FindBy(tagName = "video")
    private WebElement video;

    @FindBy(tagName = "audio")
    private WebElement audio;

    public Player() {
        super();
        this.controls = new Controls();
        this.onScreenControls = new OnScreenControls();
    }

    @Override
    public void waitForPresent() {
        this.wait.forElementPresent(By.className("avbox__player"));
    }


    public boolean isVideoPlaying() {
        return !isVideoPaused();
    }

    public boolean isVideoPaused() {
        return isPaused(video);
    }

    public void waitForVideoReady() {
        waitForReadyState(video, 2);
    }

    public String getVideoSrc() {
        return getSrc(video);
    }

    protected boolean isPaused(WebElement e) {
        Object val = jsActions.execute("return arguments[0].paused", e);
        return val != null && (Boolean) val;
    }

    protected void waitForReadyState(WebElement e, int readyState) {
        Function<WebElement, ExpectedCondition<Boolean>> cond =
                (WebElement element) -> new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        try {
                            Object val = ((JavascriptExecutor) driver).executeScript(
                                    "return arguments[0].readyState", element);
                            return val != null && ((Long) val) >= readyState;
                        } catch (StaleElementReferenceException | NoSuchElementException e) {
                            return false;
                        }
                    }

                    @Override
                    public String toString() {
                        return "element.readyState: " + element.toString();
                    }
                };
        wait.forCondition(cond, e);
    }

    protected String getSrc(WebElement e) {
        return e.getAttribute("src");
    }
}
