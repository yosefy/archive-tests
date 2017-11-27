package helper.Class;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;

public class VideoPlayer extends BaseClass{

    public VideoPlayer(WebDriver driver) {
        super(driver);
    }

    public final static String MEDIA_PLAYER_CONTROLS = ".mediaplayer__controls > div.buttons-wrapper > button";
    public final static String MEDIA_PLAYER_PLAY = "play";
    public final static String MEDIA_PLAYER_PAUSE = "pause";



    public void action(String listToButtons, String action) {
        List<WebElement> buttons = driver.findElements(By.cssSelector(listToButtons));
        for(WebElement elem : buttons){
            if (((RemoteWebElement) elem).findElementByTagName("i").getAttribute("class").contains(action)) {
                highlightElement(elem);
                System.out.println(elem.getText());
                click(elem);
            }
        }
    }

    public boolean checkMediaControl(String listToButtons, String action){
        List<WebElement> buttons = driver.findElements(By.cssSelector(listToButtons));
        for(WebElement elem : buttons){
            if (((RemoteWebElement) elem).findElementByTagName("i").getAttribute("class").contains(action)) {
                highlightElement(elem);
                return true;
            }
        }
        return false;
    }

    public boolean HTMLMediaElement_IF_Paused(){
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').paused";
        return ((Boolean) jsExec.executeScript(script)).compareTo(false) == 0;
    }

    public boolean HTMLMediaElement_IF_Muted(){
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').muted";
        return ((Boolean) jsExec.executeScript(script)).compareTo(false) == 0;
    }

    public double HTMLMediaElement_PlaybackRate(){
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return document.querySelector('video').playbackRate";
        String result =  jsExec.executeScript(script).toString();
        return Double.parseDouble(result);
    }

}


