package org.bb.qa.archive.pageobjects.widgets.videobox;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.time.Duration;
import java.util.function.Function;

public class Player extends PageObject {

    public Controls controls;
    public OnScreenControls onScreenControls;

    @FindBy(tagName = "video")
    private WebElement video;

    @FindBy(tagName = "audio")
    private WebElement audio;

    @FindBy(className = "mediaplayer__onscreen-play")
    private WebElement onScreenPlay;

    @FindBy(className = "buttons-wrapper>button")
    private WebElement onControlPlay;

    @FindBy(className = "mediaplayer__controls>div>button>i.pause")
    private WebElement onControlPause;

    @FindBy(className = "avbox__player")
    private WebElement player;

    @FindBy(className = "mediaplayer__timecode")
    private WebElement timeCode;

    @FindBy(className = "mediaplayer__controls--is-fade")
    private WebElement mediaplayerControlsIsFade;

    @FindBy(className = "layout__header")
    private WebElement header;

    @FindBy(className = "mediaplayer__onscreen-controls")
    private WebElement onscreenControls;


    public Player() {
        super();
        this.controls = new Controls();
        this.onScreenControls = new OnScreenControls();
    }

    @Override
    public void waitForPresent() {
        this.wait.forElementClickable(player); //By.className("avbox__player")
    }

    public void playByScreenIcon() {
        click(onScreenPlay);
    }

    public void playFor(Duration waitSec) {
        this.playByControlIcon(waitSec);
    }

    public void moveToControl(Duration waitSec){
        moveMouseToElement(onscreenControls);
        wait.forX(waitSec);
    }

    public void pause() {
        this.playByControlIcon(Duration.ZERO);
    }

    protected void playByControlIcon(Duration waitSec) {
        click(onControlPlay);
        click(header);
        if (!waitSec.isZero() && !waitSec.isNegative()) {
            wait.forX(waitSec);
        }
    }


    public void pauseByScreenClick() {
        click(player);
    }

    public void pauseByControlIcon() {
        click(onControlPause);
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

    public String getTimeCodeJS() {
        String val = timeByJS(video);
        System.out.println(val);

        if (val != null)
            return val;
        else
            return null;
    }

    protected String timeByJS(WebElement e) {
        Object val = jsActions.execute("return arguments[0].currentTime/60", e).toString();
        return (String) val;
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

    public Duration[] getTimeCode() {
        Duration[] dur = new Duration[2];
        String[] mainParts = timeCode.getText().replaceAll("\\s", "").split("/");

        for (int i = 0; i < mainParts.length; i++) {
            // remove all space and split by :
            String[] parts = mainParts[i].trim().split(":");
            Duration d = Duration.ZERO;
            if (parts.length == 3) {
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);
                int seconds = Integer.parseInt(parts[2]);
                d = d.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
            } else if (parts.length == 2) {
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                d = d.plusMinutes(minutes).plusSeconds(seconds);
            } else {
                System.out.println("ERROR - Unexpected input.");
            }
            dur[i] = d;
            System.out.println(dur[i]);
        }
        return dur;
    }

}





