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
import static PageObjects.ProgramsGenre.VERTICAL_HAMBURGER_MENU;
import static helper.Class.VideoPlayer.*;


public class Events extends InitClass {

    private EventsMain eventsMain;

    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void eventsMainAllItemsMultiLang(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        // ENG
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        int items_count = eventsMain.checkAllEventsItems();
        // RUS
        eventsMain.click(driver.findElement(By.cssSelector(RU_FLAG)));
        Assert.assertEquals(items_count,eventsMain.checkAllEventsItems(),
                "Items counter doesn't equal");
        // HEB
        eventsMain.click(driver.findElement(By.cssSelector(IL_FLAG)));
        Assert.assertEquals(items_count,eventsMain.checkAllEventsItems(),
                "Items counter doesn't equal");
    }
}