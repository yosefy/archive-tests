package ArchiveBB.Tests;

import PageObjects.EventsMain;
import PageObjects.ProgramsGenre;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import helper.Class.VideoPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static PageObjects.EventsMain.*;
import static PageObjects.ProgramsGenre.*;
import static helper.Class.VideoPlayer.*;


public class Events extends InitClass {

    private EventsMain eventsMain;
    private VideoPlayer videoPlayer;

    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
        videoPlayer = new VideoPlayer(driver);
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void eventsMainAllItemsMultiLang(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        int items_count = eventsMain.checkAllEventsItems();
        eventsMain.click(driver.findElement(By.cssSelector(RU_FLAG)));
        eventsMain.click(driver.findElement(By.cssSelector(IL_FLAG)));
    }


    @Test()
//    @Video()
    @Parameters({"link"})
    public void eventsTestPlayer(String link) throws InterruptedException {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        // click on EVENTS_Unity_Test
        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);
        // get list of all elements from vertical menu
        List<String> webPlayerItemsStr = videoPlayer.getWebElemListReturnListVideoSrc(EVENTS_VERTICAL_MENU);
        int i = 0;
        for(WebElement item : eventsMain.getWebElemListReturnWebElementList(EVENTS_VERTICAL_MENU)){
            eventsMain.click(item);
            Assert.assertTrue(eventsMain.isAttributeActive(item),"WebElement doesn't active");
            Assert.assertTrue(webPlayerItemsStr.get(i).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            i++;
        }

//        Assert.assertTrue(videoPlayer.checkMediaControl(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PAUSE),
//                "Action doesn't activated");
//        Assert.assertTrue(videoPlayer.HTMLMediaElement_IF_Paused(), "Player Doesn't Started");
    }
}
