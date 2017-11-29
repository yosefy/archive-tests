package ArchiveBB.Tests;

import PageObjects.EventsMain;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import helper.Class.VideoPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

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
    public void eventsTestPlayer(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));

        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);

        List<WebElement> webPlayerItems = eventsMain.getCssListReturnWebElementsList(EVENTS_VERTICAL_MENU);
        String videoSource = videoPlayer.HTMLMediaElement_GetVideoSource();

        for(WebElement item : webPlayerItems){
            Assert.assertTrue(eventsMain.isAttributeActive(item),
                    "WebElement doesn't active");
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            Assert.assertEquals(videoSource, videoPlayer.HTMLMediaElement_GetVideoSource(),
                    "Video source doesn't updated");
        }


        videoPlayer.checkMediaControl(MEDIA_PLAYER_CONTROLS,"");
        //
        driver.getCurrentUrl();



//        Assert.assertTrue(videoPlayer.checkMediaControl(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PAUSE),
//                "Action doesn't activated");
//        Assert.assertTrue(videoPlayer.HTMLMediaElement_IF_Paused(), "Player Doesn't Started");
    }
}
