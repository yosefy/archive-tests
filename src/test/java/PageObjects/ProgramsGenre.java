package PageObjects;

import helper.Class.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProgramsGenre extends BaseClass {

    public ProgramsGenre(WebDriver driver) {
        super(driver);
    }

    public final static String GENRE_MAIN_LEFT_PANEL = ".ui.blue.tiny.fluid.vertical.menu>a.item";
    private final static String ALL_PROGRAMS_ITEMS = ".column>div.ui.tiny.list>div";  // .column>div.ui.tiny.list>div  // .ui.tiny.list>div.item
    private final static String SIDE_BAR = ".layout__sidebar.is-active";
    private final static String SIDE_BAR_ICON = ".sidebar.icon";
    private final static String VERTICAL_HAMBURGER_MENU = ".ui.blue.huge.borderless.fluid.vertical.menu>a";


    public void ChoosePanel (String target){
        boolean ifLabelExist = driver.findElements(By.cssSelector(GENRE_MAIN_LEFT_PANEL)).size()!=0;
        if (ifLabelExist)
            clickListAndTarget(GENRE_MAIN_LEFT_PANEL, target);
    }

    public Map<String, String> getAllProgramsItems (){
        Map<String,String> myMap = new HashMap<String,String>();
        List<WebElement> allItems = driver.findElements(By.cssSelector(ALL_PROGRAMS_ITEMS));
        String key,value;
        for (WebElement run : allItems) {
//            highlightElement(run);
            key = run.getAttribute("data-value");
            value = run.getText();
            if (key!= null) {
                System.out.println("Data Value is :" + key);
                System.out.println("Key Value is :" + value);
                myMap.put(key, value.trim());
            }
        }
        return myMap;
    }

    public void navToPrograms(){
        boolean ifSideBarIsOpened = driver.findElements(By.cssSelector(SIDE_BAR)).size()!=0;
        if(!ifSideBarIsOpened)
            click(driver.findElement(By.cssSelector(SIDE_BAR_ICON)));
        clickListAndTarget(VERTICAL_HAMBURGER_MENU, "Programs");
    }

//    // check if all items in secondMap existing in firstMap
//    public boolean checkProgramsInTable(Map<String, String> firstMap, Map<String, String> secondMap) {
//        for (Map.Entry<String, String> entry : secondMap.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//            if (!firstMap.containsKey(entry.getKey()))
//                return false;
//        }
//        return true;
//    }
}
