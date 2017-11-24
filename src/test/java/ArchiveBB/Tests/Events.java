package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import PageObjects.EventsMain;
import PageObjects.ProgramsGenre;
import helper.Class.InitClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import videoHelper.VideoRecorder;

import java.util.List;
import java.util.Map;

import static PageObjects.ArchiveDate.*;
import static PageObjects.EventsMain.*;
import static PageObjects.ProgramsGenre.*;


public class Events extends InitClass {

    private EventsMain eventsMain;
    private VideoRecorder videoRecord;

//    private ArchiveTopics archiveTopics;
//    private ArchiveSources archiveSources;

    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
        videoRecord = new VideoRecorder(driver);

//        archiveTopics = new ArchiveTopics(driver);
//        archiveSources = new ArchiveSources(driver);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void eventsMainAllItemsMultiLang(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);
        int items_count;
        // do test
        try {
            driver.get(link);
            eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
            eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
            items_count = eventsMain.checkAllEventsItems();
            eventsMain.click(driver.findElement(By.cssSelector(RU_FLAG)));
            eventsMain.click(driver.findElement(By.cssSelector(IL_FLAG)));
        }

        // stop video recording
        finally {videoRecord.stopRecording(videoPath);}
        videoRecord.deleteVideoLog(videoPath);
    }

}
