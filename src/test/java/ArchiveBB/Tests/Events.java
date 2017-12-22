package ArchiveBB.Tests;

import PageObjects.EventsMain;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static PageObjects.EventsMain.*;
import static PageObjects.ProgramsGenre.VERTICAL_HAMBURGER_MENU;


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
        eventsMain.navigateToPanelAndSection(EVENTS);
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