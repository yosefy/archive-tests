package ArchiveBB.Tests;

import PageObjects.EventsMain;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import helper.Class.VideoPlayerClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static PageObjects.EventsMain.*;
import static PageObjects.ProgramsGenre.VERTICAL_HAMBURGER_MENU;
import static helper.Class.VideoPlayerClass.*;

public class Player extends InitClass{

    private EventsMain eventsMain;
    private VideoPlayerClass videoPlayer;

    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
        videoPlayer = new VideoPlayerClass(driver);
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void playerVideoSrcAndForwardBtn(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        // click on EVENTS_Unity_Test
        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);
        // get list of all elements from vertical menu
        List<String> webPlayerItemsStr = videoPlayer.getWebElemListReturnListVideoSrc(EVENTS_VERTICAL_MENU);
        int i = 0;
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)){
            eventsMain.click(item);
            Assert.assertTrue(eventsMain.isWebElemAttributeActiveItem(item),"WebElement doesn't active");
            Assert.assertTrue(webPlayerItemsStr.get(i).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // Click on Forward Button
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            i++;
        }
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void playerPlayBtnAndPauseBtn(String link){
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        // click on EVENTS_Unity_Test
        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);
        // get list of web elements from vertical menu
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)){
            eventsMain.click(item);
            Assert.assertTrue(eventsMain.isWebElemAttributeActiveItem(item),"WebElement doesn't active");
            // Click on Play Button
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PLAY);
            Assert.assertTrue(videoPlayer.HTMLMediaElement_IF_Paused(),"Video doesn't started");
            // Check if displayed Pause btn instead of Play btn
            Assert.assertTrue(videoPlayer.checkMediaControlState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PAUSE),
                    "Play button doesn't replaced by Pause button");
            // Click on Forward Button
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
        }
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void playerForwardBtnAndBackwardBtn(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        // click on EVENTS_Unity_Test
        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);
        // get list of all elements from vertical menu
        List<String> webPlayerItemsStr = videoPlayer.getWebElemListReturnListVideoSrc(EVENTS_VERTICAL_MENU);
        int i = 0;
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)){
            videoPlayer.click(item);
            Assert.assertTrue(videoPlayer.isWebElemAttributeActiveItem(item),"WebElement doesn't active");
            System.out.println("Current URL: " + webPlayerItemsStr.get(i));
            Assert.assertTrue(webPlayerItemsStr.get(i).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // Click on Forward Button
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            i++;
            // Check the last element - Should be disabled
            if (i == webPlayerItemsStr.size())
                // Verify if Forward button is disabled
                Assert.assertTrue(videoPlayer.checkMediaControlState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD_DISABLED),
                        "The MEDIA_PLAYER_FORWARD_DISABLED item doesn't disabled");
        }
        // This block testing Backward button and in the end of iteration verify disabled Backward button
        for (int j = webPlayerItemsStr.size()-1; j >= 0; j--) {
            System.out.println("Current URL: " + webPlayerItemsStr.get(j));
            Assert.assertTrue(webPlayerItemsStr.get(j).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // click backward btn
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_BACKWARD);
            if (j==0)
                Assert.assertTrue(videoPlayer.checkMediaControlState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_BACKWARD_DISABLED),
                    "The MEDIA_PLAYER_BACKWARD_DISABLED item doesn't disabled");
        }
    }


    @Test()
    public void playerTimeCode(){

    }

    @Test()
    public void timeCodeUpdateByPlay(){

    }

    @Test()
    public void timeCodeUpdateByScroll(){

    }

    @Test()
    public void timeCodeUpdateByLink(){

    }

    @Test()
    public void speedSelector(){

    }

    @Test()
    public void volumeBar(){

    }

    @Test()
    public void audioVideoToggle(){

    }

    @Test()
    public void languageSelector(){

    }

    @Test()
    public void fullScreenToggle(){

    }

    @Test()
    public void sharingModeOn(){

    }

    @Test()
    public void sharingModeOff(){

    }

    @Test()
    public void sharingModeActions(){

    }

}
