package helper.Class;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerClass extends BaseClass {

    public VideoPlayerClass(WebDriver driver) {
        super(driver);
    }

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


    public void choosePlayerRate (String MEDIA_PLAYER_RATE) {
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_CONTROLS_RATE)));
        clickListAndTarget(MEDIA_PLAYER_CONTROLS_RATE_LIST, MEDIA_PLAYER_RATE);
    }

    public void clickOnMute(){
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_MUTE)));
    }

    public void clickAudioVideo(){
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_AUDIO_VIDEO_TOGGLE)));
    }

    public void updateVolumeControl (int y){
        click(driver.findElement(By.cssSelector(MEDIA_PLAYER_MUTE)));

        WebElement tryElem = driver.findElement(By.cssSelector(MEDIA_PLAYER_VOLUME_WRAPPER));
        scrollToElementIgnoringSteakHeader(tryElem, y);
        dragAndDropElementByActionOnVertical(tryElem,y);
    }

//    public boolean checkMediaControlState(String listToButtons, String action) {
//        List<WebElement> buttons = driver.findElements(By.cssSelector(listToButtons));
//        for (WebElement elem : buttons) {
//            if (((RemoteWebElement) elem).findElementByTagName("i").getAttribute("class").contains(action)) {
//                highlightElement(elem);
//                System.out.println(elem.getText());
//                return true;
//            }
//        }
//        return false;
//    }

    public List<String> getWebElemListReturnListVideoSrc(String eventsVerticalMenu) {
        List<WebElement> videoSrcList = driver.findElements(By.cssSelector(eventsVerticalMenu));
        this.isElementLoaded(videoSrcList.get(0));
        List<String> listStr = new ArrayList<>();
        for (WebElement list : videoSrcList) {
            click(list);
            this.isElementLoaded(list);
            listStr.add(this.HTMLMediaElement_GetVideoSource());
        }
        return listStr;
    }

    public String[] getTimeCode (){
        LocalTime now = LocalTime.now();
        int currentMinute = now.getMinute();
        int nextMinute;
        System.out.println("currentMinute is: " + currentMinute);

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
                System.out.println("The player didn't get end time !!!");
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
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean HTMLMediaElement_IF_Audio() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('audio').tagName";
        try {
            String result = jsExec.executeScript(script).toString();
            return result.equals("AUDIO");
        }catch (WebDriverException e){
            return false;
        }
    }

    public boolean isPlayerAudioVideoToggleLoaded(){
        List<WebElement> element = driver.findElements(By.cssSelector(MEDIA_PLAYER_AUDIO_VIDEO_TOGGLE));

        return element != null;

    }
}


