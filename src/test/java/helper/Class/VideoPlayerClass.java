package helper.Class;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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



    public boolean actionAndReturnState(String listToButtons, String action) {
        List<WebElement> buttons = driver.findElements(By.cssSelector(listToButtons));
        for (WebElement elem : buttons) {
            if (((RemoteWebElement) elem).findElementByTagName("i").getAttribute("class").contains(action)) {
                highlightElement(elem);
                System.out.println(elem.getText());
                click(elem);
                return true;
            }
        }
        wait(500);
        return false;
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
        this.isElementLoaded(videoSrcList.get(1));
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

}


