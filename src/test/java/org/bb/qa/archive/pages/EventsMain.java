package org.bb.qa.archive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventsMain extends PageObject {

    public final static String EVENTS = "Events";
    public final static String EVENTS_MAIN_TABLE = ".ui.container.padded>table>tbody>tr";
    public final static String EVENTS_MAIN_TABLE_LINKS = ".ui.container.padded>table>tbody>tr>td>a";
    public final static String EVENTS_VERTICAL_MENU = ".ui.small.fluid.vertical.menu>a";
    public final static String US_FLAG = ".item>a>i.us.flag";
    public final static String RU_FLAG = ".item>a>i.ru.flag";
    public final static String IL_FLAG = ".item>a>i.il.flag";
    public final static String EVENTS_Unity_Test = "Unity Evening - “The Story of Kabbalah LaAm”";
    public final static String EVENTS_CONVENTION_GEO_2017 = "Convention in Georgia 2017";

    public EventsMain(WebDriver driver) {
        super(driver);
    }

    public int checkAllEventsItems() {
        return driver.findElements(By.cssSelector(EVENTS_MAIN_TABLE)).size();
    }
}
