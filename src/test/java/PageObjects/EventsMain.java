package PageObjects;

import helper.Class.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EventsMain extends BaseClass {

    public EventsMain(WebDriver driver) {
        super(driver);
    }

    public final static String EVENTS = "Events";
    public final static String EVENTS_MAIN_TABLE = ".ui.container.padded>table>tbody>tr";
    public final static String EVENTS_VERTICAL_MENU = ".ui.small.fluid.vertical.menu>a";

    public final static String US_FLAG = ".item>a>i.us.flag";
    public final static String RU_FLAG = ".item>a>i.ru.flag";
    public final static String IL_FLAG = ".item>a>i.il.flag";

    public final static String EVENTS_Unity_Test = "Unity Evening - “The Story of Kabbalah LaAm”";
    public final static String EVENTS_CONVENTION_GEO_2017 = "Convention in Georgia 2017";


    public int checkAllEventsItems(){
        List<WebElement> items = driver.findElements(By.cssSelector(EVENTS_MAIN_TABLE));
        System.out.println("Count is: " + items.size());
        return items.size();
    }
}
