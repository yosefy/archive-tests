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

    // todo - need to develop
    public final static String EVENTS = "Events";
    public final static String EVENTS_MAIN_TABLE = ".ui.container.padded>table>tbody>tr";
    public final static String US_FLAG = ".item>a>i.us.flag";
    public final static String RU_FLAG = ".item>a>i.ru.flag";
    public final static String IL_FLAG = ".item>a>i.il.flag";

//    public final static String SIDE_BAR_ICON = ".sidebar.icon";
//    public final static String VERTICAL_HAMBURGER_MENU = ".ui.blue.huge.borderless.fluid.vertical.menu>a";
//    public final static String GENRE_PROGRAM_PANEL = "All Programs";
//    public final static String PAGINATION_PANEL = ".ui.blue.compact.menu";
//    public final static String PROGRAMS_RESULT_EPISODE = ".ui.sortable.very.basic.table.index-list>tbody>tr>td>div>a";
//
//
//    public Map<String, String> getAllProgramsItems (){
//        Map<String,String> myMap = new HashMap<String,String>();
//        List<WebElement> allItems = driver.findElements(By.cssSelector(ALL_PROGRAMS_ITEMS));
//        String key,value;
//        for (WebElement run : allItems) {
////            highlightElement(run);
//            key = run.getAttribute("data-value");
//            value = run.getText();
//            if (key!= null) {
//                System.out.println("Data Value is :" + key);
//                System.out.println("Key Value is :" + value);
//                myMap.put(key, value.trim());
//            }
//        }
//        return myMap;
//    }

    public int checkAllEventsItems(){
        List<WebElement> items = driver.findElements(By.cssSelector(EVENTS_MAIN_TABLE));
        System.out.println("Count is: " + items.size());
        return items.size();
    }
}
