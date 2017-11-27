package ArchiveBB.Tests;

import PageObjects.EventsMain;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import helper.Class.VideoPlayer;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import videoHelper.VideoRecorder;

import static PageObjects.EventsMain.*;
import static PageObjects.ProgramsGenre.*;
import static helper.Class.VideoPlayer.MEDIA_PLAYER_CONTROLS;
import static helper.Class.VideoPlayer.MEDIA_PLAYER_PAUSE;
import static helper.Class.VideoPlayer.MEDIA_PLAYER_PLAY;


public class Events extends InitClass {

    private EventsMain eventsMain;
    private VideoRecorder videoRecord;
    private VideoPlayer videoPlayer;


    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
        videoRecord = new VideoRecorder(driver);
        videoPlayer = new VideoPlayer(driver);

//        archiveTopics = new ArchiveTopics(driver);
//        archiveSources = new ArchiveSources(driver);
    }

    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void eventsMainAllItemsMultiLang(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);
        int items_count;
        // do test
//        try {
            driver.get(link);
            eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
            eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
            items_count = eventsMain.checkAllEventsItems();
            eventsMain.click(driver.findElement(By.cssSelector(RU_FLAG)));
            eventsMain.click(driver.findElement(By.cssSelector(IL_FLAG)));
//        }

        // stop video recording
//        finally {videoRecord.stopRecording(videoPath);}
//        videoRecord.deleteVideoLog(videoPath);
    }


    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void eventsTestPlayer(String link, String videoPath) throws Exception {

//        try {
            driver.get(link);
            eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
            eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
            eventsMain.getCssListAndClickOnFirstElement(EVENTS_MAIN_TABLE + " a");
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PLAY);
            Assert.assertTrue(videoPlayer.checkMediaControl(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PAUSE),
                    "Action doesn't activated");
            Assert.assertTrue(videoPlayer.HTMLMediaElement_IF_Paused(),"Player Doesn't Started");

            System.out.println(videoPlayer.HTMLMediaElement_PlaybackRate());



            driver.getCurrentUrl();
//        }

        // stop video recording
//        finally {videoRecord.stopRecording(videoPath);}
    }
}
