package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import PageObjects.EventsMain;
import PageObjects.ProgramsGenre;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;
import java.util.Map;

import static PageObjects.ArchiveDate.*;
import static PageObjects.ProgramsGenre.*;


public class TryNewTests extends InitClass {

    private ProgramsGenre programsGenre;
    private EventsMain eventsMain;
    private ArchiveTopics archiveTopics;
    private ArchiveSources archiveSources;

    @BeforeMethod
    public void beforeMethod() {
        programsGenre = new ProgramsGenre(driver);
        archiveTopics = new ArchiveTopics(driver);
        archiveSources = new ArchiveSources(driver);
        eventsMain = new EventsMain(driver);
    }

    // https://github.com/tourdedave/the-internet
    // http://elementalselenium.com/tips

    @Test()
    public void brokenLinks() throws AWTException {
//        driver.get("http://the-internet.herokuapp.com/broken_images");
//        eventsMain.CheckBrokenImage(".example>img");

        driver.get("http://the-internet.herokuapp.com/upload");
        eventsMain.click(driver.findElement(By.cssSelector("#file-upload")));
        eventsMain.UploadFileByRobot();
        eventsMain.click(driver.findElement(By.cssSelector("#file-submit")));
        Assert.assertTrue(eventsMain.getCssListAndCheckTextIfExist(".example>h3","File Uploaded!"),
                "Doesn't displayed success message !!!");

        driver.getTitle();
    }

}
