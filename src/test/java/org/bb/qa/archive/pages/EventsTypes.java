package org.bb.qa.archive.pages;

import org.openqa.selenium.WebDriver;

public class EventsTypes extends PageObject {

    public EventsTypes(WebDriver driver) {
        super(driver);
    }

    // todo - need to develop
//    public final static String GENRE_MAIN_LEFT_PANEL = ".ui.blue.tiny.fluid.vertical.menu>a.item";
//    private final static String ALL_PROGRAMS_ITEMS = ".column>div.ui.tiny.list>div";
//    public final static String SIDE_BAR = ".layout__sidebar.is-active";
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

}
