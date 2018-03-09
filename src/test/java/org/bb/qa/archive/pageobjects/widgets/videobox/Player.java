package org.bb.qa.archive.pageobjects.widgets.videobox;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.List;
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

    @FindBy(className = "mediaplayer__playback-rate")
    private WebElement playbackSpeedRate;

    @FindBy(css = ".mediaplayer__controls > div.mediaplayer__playback-rate > div > div > div")
    private List<WebElement> playbackSpeedRateList;

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
        builder.moveToElement(onscreenControls).perform();
        wait.forX(waitSec);
    }


    public void pause() {
        this.playByControlIcon(Duration.ZERO);
    }

    private void playByControlIcon(Duration waitSec) {
        click(onControlPlay);
        if (!waitSec.isZero() && !waitSec.isNegative()) {
            wait.forX(waitSec);
        }
    }

    public void clickOutOfPlayerAndWait(){
        click(header);
        wait.forX(Duration.ofSeconds(2));
    }

    public void pauseByScreenClick() {
        click(player);
    }

    public void pauseByControlIcon() {
        click(onControlPause);
    }

    public void clickOnPlayerSpeedRate(){
        click(playbackSpeedRate);
    }

    public void selectFromPlayerSpeedRateList(String speedRate){
        for(WebElement entry: playbackSpeedRateList){
           if(entry.getText().contains(speedRate)) {
               click(entry);
               break;
           }
        }
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

    public String getPlaybackRate() {
        return playbackRate(video);
    }

    public void getVideoURLWithArgs(String args) {
        String currentVideoURL = driver.getCurrentUrl();
        String finalUrl = String.format("%s&%s", currentVideoURL, args);
        System.out.println(finalUrl);
        driver.get(finalUrl);
    }

    public void seekToTime(Duration seconds) {
        jsActions.execute(String.format("arguments[0].currentTime=%s", Long.toString(seconds.getSeconds())), video);
    }


    protected String playbackRate(WebElement e) {
        Object val = jsActions.execute("return arguments[0].playbackRate", e);
        return val.toString();
    }

    private boolean isPaused(WebElement e) {
        Object val = jsActions.execute("return arguments[0].paused", e);
        return val != null && (Boolean) val;
    }


    private void waitForReadyState(WebElement e, int readyState) {
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

    private String getSrc(WebElement e) {
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


    public Duration getTimeCodeJS() {
        Instant val = getInstant(video);
        if(val == null)
            return null;


            int seconds = val.getSeconds();
            int minutes = val.getMinutes();
            int hours = val.getHours();

        System.out.println(val);
        return val.getDuration();
    }

    protected Instant getInstant(WebElement e) {
        try {
            String currentTimeStr = String.valueOf(jsActions.execute("return arguments[0].currentTime", e));
            int seconds = (int) Float.parseFloat(currentTimeStr);
            return new Instant(seconds);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return null;
        }
    }

    static class Instant {
        final int hours;
        final int minutes;
        final int seconds;

//        public static void main(String[] args) {
//            System.out.println(new Instant(2 * 60 * 60 + 90));
//        }

        public Instant(int totalSeconds) {
            this.seconds = totalSeconds % 60;
            this.hours = totalSeconds / 3_600;
            this.minutes = (totalSeconds / 60) - (hours * 60);
        }

        public int getHours() {
            return hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public int getSeconds() {
            return seconds;
        }

            public Duration getDuration() {
            Duration d = Duration.ZERO;
            d = d.plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
            return d;
        }

        @Override
        public String toString() {
            return String.format("%2d:%02d:%02d", hours, minutes, seconds);
        }
    }

}





