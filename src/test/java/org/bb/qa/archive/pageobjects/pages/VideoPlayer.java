package org.bb.qa.archive.pageobjects.pages;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoPlayer extends PageObject {

    private static final Logger logger = LoggerFactory.getLogger(VideoPlayer.class);

    public final static String MEDIA_PLAYER_PLAY = "play";
    public final static String MEDIA_PLAYER_PAUSE = "pause";
    public final static String MEDIA_PLAYER_FORWARD = "step forward icon";
    public final static String MEDIA_PLAYER_FORWARD_DISABLED = "step forward disabled icon";

    public final static String MEDIA_PLAYER_BACKWARD = "step backward icon";
    public final static String MEDIA_PLAYER_BACKWARD_DISABLED = "step backward disabled icon";

    public final static String MEDIA_PLAYER_CONTROLS = ".mediaplayer__controls>div.buttons-wrapper>button";
    public final static String MEDIA_PLAYER_TIMECODE = ".mediaplayer__timecode>time";
    public final static String MEDIA_PLAYER_SEEKBAR = ".seekbar__knob";
    public final static String MEDIA_PLAYER_CONTROLS_RATE = ".mediaplayer__controls > div.mediaplayer__playback-rate > div > button";
    public final static String MEDIA_PLAYER_CONTROLS_RATE_LIST = ".mediaplayer__controls > div.mediaplayer__playback-rate > div > div div>span";

    public final static String MEDIA_PLAYER_RATE_1X = "1x";
    public final static String MEDIA_PLAYER_RATE_1_5X = "1.5x";
    public final static String MEDIA_PLAYER_RATE_2X = "2x";

    public final static String MEDIA_PLAYER_MUTE = ".mediaplayer__volume>button";
    public final static String MEDIA_PLAYER_VOLUME = ".mediaplayer__volume";
    public final static String MEDIA_PLAYER_VOLUME_WRAPPER = ".volume-popover__wrapper";
    public final static String MEDIA_PLAYER_AUDIO_VIDEO_TOGGLE = ".mediaplayer__audiovideo";

    public final static String MEDIA_PLAYER_LANGUAGES = ".mediaplayer__languages>div";
    public final static String MEDIA_DOWNLOADS_LANGUAGES = ".content__aside-unit>div>div>div>div";

    public final static String MEDIA_PLAYER_FULL_SCREEN = ".player-button.player-control-fullscreen";
    public final static String MEDIA_PLAYER_SHARE = ".player-button.player-control-edit-slice";

    public final static String MEDIA_PLAYER = ".mediaplayer__onscreen-controls";



    public final HashMap<String, String> allLanguagesHash = new HashMap<String, String>() {{
        put("en", "English");
        put("he", "Hebrew");
        put("ru", "Russian");
        put("es", "Spanish");
        put("it", "Italian");
        put("de", "German");
        put("fr", "French");
        put("hu", "Hungarian");
        put("zh", "Chinese");
        put("pt", "Portuguese");
        put("tr", "Turkish");
        put("lt", "Lithuanian");
        put("ja", "Japanese");
        put("bg", "Bulgarian");
        put("ka", "Georgian");
        put("ro", "Romanian");
        put("ua", "Ukrainian");
    }};

    public final HashMap<String, String> playerHotKeys = new HashMap<String, String>() {{
            put("0", Keys.NUMPAD0.toString()); // Seek to 0
            put("f", "f"); // Full Screen
            put("j", "j"); // Skip time -5
            put("k", "k"); // Play/Pause
            put("l", "l"); // Skip time +5
            put(",", ","); // Skip time -1
            put(".", "."); // Skip time 1
            put("Space", Keys.SPACE.toString()); // Play/Pause
            put("Home", Keys.HOME.toString()); // Seek to 0
            put("End", Keys.END.toString()); // Seek to duration
            put("ArrowLeft", Keys.ARROW_LEFT.toString()); // Skip time -5
            put("ArrowUp", Keys.ARROW_UP.toString()); // Volume +5
            put("ArrowRight", Keys.ARROW_RIGHT.toString()); // Skip time +5
            put("ArrowDown", Keys.ARROW_DOWN.toString()); // Volume -5
            put("j_Shift", Keys.chord(Keys.SHIFT, "j")); // Skip time -10
            put("ArrowLeft_Shift", Keys.chord(Keys.SHIFT, Keys.ARROW_LEFT)); // Skip time -10
            put("l_Shift", Keys.chord(Keys.SHIFT, "l")); // Skip time +10
            put("ArrowRight_Shift", Keys.chord(Keys.SHIFT, Keys.ARROW_RIGHT)); // Skip time +10
            put("ArrowUp_Shift", Keys.chord(Keys.SHIFT, Keys.ARROW_UP)); // Volume +10
            put("ArrowDown_Shift", Keys.chord(Keys.SHIFT, Keys.ARROW_DOWN)); // Volume -10
    }};

    public boolean actionAndReturnState(String listToButtons, String action) {
        List<WebElement> buttons = driver.findElements(By.cssSelector(listToButtons));
        for (WebElement elem : buttons) {
            if (((RemoteWebElement) elem).findElementByTagName("i").getAttribute("class").contains(action)) {
                highlightElement(elem);
                click(elem);
                System.out.println(elem.getText());
                return true;
            }
        }
        wait(500);
        return false;
    }


    public void choosePlayerRate(String MEDIA_PLAYER_RATE) {
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_CONTROLS_RATE)));
        clickListAndTarget(MEDIA_PLAYER_CONTROLS_RATE_LIST, MEDIA_PLAYER_RATE);
    }

    public void clickOnMute() {
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_MUTE)));
    }

    public void clickAudioVideo() {
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_AUDIO_VIDEO_TOGGLE)));
    }

    public void clickOnShare() {
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_SHARE)));
    }


    public void updateVolumeControl(int y) {
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_MUTE)));
        WebElement tryElem = driver.findElement(By.cssSelector(MEDIA_PLAYER_VOLUME_WRAPPER));
        isElementLoaded(tryElem);
        scrollToElementIgnoringSteakHeader(tryElem, y);
        dragAndDropElementByActionOnVertical(tryElem, y);
    }

    public List<String> getWebElemListReturnListVideoSrc(String eventsVerticalMenu) {
        List<WebElement> videoSrcList = driver.findElements(By.cssSelector(eventsVerticalMenu));
        isElementLoaded(videoSrcList.get(0));
        List<String> listStr = new ArrayList<>();
        for (WebElement list : videoSrcList) {
            click(list);
            isElementLoaded(list);
            listStr.add(this.HTMLMediaElement_GetVideoSource());
        }
        return listStr;
    }

    public String[] getTimeCode() {
        LocalTime now = LocalTime.now();
        int currentMinute = now.getMinute();
        int nextMinute;
        logger.debug("currentMinute is: " + currentMinute);

        String[] time = new String[2];
        List<WebElement> element = driver.findElements(By.cssSelector(MEDIA_PLAYER_TIMECODE));
        time[0] = element.get(0).getText();
        time[1] = element.get(1).getText();
        // wait until displayed duration time
        while (time[1].equals("00:00")) {
            element = driver.findElements(By.cssSelector(MEDIA_PLAYER_TIMECODE));
            time[1] = element.get(1).getText();
            nextMinute = now.getMinute();
            if (nextMinute - currentMinute >= 2) {
                logger.debug("The player didn't get end time !!!");
                break;
            }
        }
        return time;
    }

    public String HTMLMediaElement_GetVideoSource() {
        String source = ((ChromeDriver) driver).findElementByTagName("video").getAttribute("src");
        System.out.println("Current SRC: " + source);
        return source;
    }

    public boolean HTMLMediaElement_IF_Paused() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').paused";
        return ((Boolean) jsExec.executeScript(script)).compareTo(false) == 0;
    }

    public boolean HTMLMediaElement_IF_Muted() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').muted";
        return ((Boolean) jsExec.executeScript(script)).compareTo(false) == 0;
    }

    public double HTMLMediaElement_PlaybackRate() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').playbackRate";
        String result = jsExec.executeScript(script).toString();
        return Double.parseDouble(result);
    }

    public boolean HTMLMediaElement_IF_Video() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').tagName";
        try {
            String result = jsExec.executeScript(script).toString();
            return result.equals("VIDEO");
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean HTMLMediaElement_IF_Audio() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('audio').tagName";
        try {
            String result = jsExec.executeScript(script).toString();
            return result.equals("AUDIO");
        } catch (WebDriverException e) {
            return false;
        }
    }


    public boolean HTMLMediaElement_IF_FullScreen() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.webkitIsFullScreen";
        return ((Boolean) jsExec.executeScript(script)).compareTo(false) == 0;
    }

    public String HTMLMediaElement_IF_CURRENT_TIME() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').currentTime/60";
        return jsExec.executeScript(script).toString();
    }


    public boolean isPlayerAudioVideoToggleLoaded() {
        List<WebElement> element = driver.findElements(By.cssSelector(MEDIA_PLAYER_AUDIO_VIDEO_TOGGLE));
        return element != null;
    }

    public List<String> getListOfLanguagesFromPlayer() {
        // open language drop down if closed
        if (!driver.findElement(By.cssSelector(MEDIA_PLAYER_LANGUAGES + ">div")).getText().contains("visible"))
            click(driver.findElement(By.cssSelector(MEDIA_PLAYER_LANGUAGES + ">button")));
        highlightElement(driver.findElement(By.cssSelector(MEDIA_PLAYER_LANGUAGES + ">button")));
        List<String> list = getWebElemListReturnStringList(MEDIA_PLAYER_LANGUAGES + ">div>div");
        // close opened language menu
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_LANGUAGES + ">button")));
        return list;
    }

    // todo - need to move to other page object class
    public List<String> getListOfLanguagesFromMediaDownloads() {
        WebElement languageMenu = driver.findElement(By.cssSelector(MEDIA_DOWNLOADS_LANGUAGES));
        if (!languageMenu.getText().contains("active"))
            click(languageMenu);
        highlightElement(languageMenu);
        List<String> list = getWebElemListReturnStringList(MEDIA_DOWNLOADS_LANGUAGES + ">div>div>span");
        // close opened language menu
        click(languageMenu);
        return list;
    }


    public Map<String, String> listFromPlayerToHash(List<String> languages) {
        Map<String, String> playerLanguagesMap = new HashMap<>();
        for (String i : languages) playerLanguagesMap.put(allLanguagesHash.get(i), i);
        return playerLanguagesMap;
    }


    public void playerKeyPress(String key){

        WebElement player = driver.findElement(By.cssSelector(MEDIA_PLAYER));
        player.sendKeys(key);
    }

    public void waitForFullScreenStatusUpdate(int timeoutInSeconds, String expectedValue) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        new WebDriverWait(driver, timeoutInSeconds).
                until(func -> jsExec.executeScript("return document.webkitIsFullScreen").toString().
                        equals(expectedValue));
    }

    public int getVolumePercentage() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return parseInt(document.getElementsByTagName('video')[0].volume * 100)";
        try {
            return Integer.parseInt(jsExec.executeScript(script).toString());

        }catch (NumberFormatException num_err) {
            return -1;
        }
    }
}



