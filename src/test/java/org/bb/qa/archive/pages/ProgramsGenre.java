package org.bb.qa.archive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramsGenre extends PageObject {

    public final static String GENRE_MAIN_LEFT_PANEL = ".ui.blue.tiny.fluid.vertical.menu>a.item";
    public final static String ALL_PROGRAMS_ITEMS = ".column>div.ui.tiny.list>div";
    public final static String SIDE_BAR_ICON = ".sidebar.icon";
    public final static String VERTICAL_HAMBURGER_MENU = ".ui.blue.huge.borderless.fluid.vertical.menu>a";
    public final static String GENRE_PROGRAM_PANEL = "All Programs";
    public final static String PAGINATION_PANEL = ".ui.blue.compact.menu";
    public final static String PROGRAMS_RESULT_EPISODE = ".ui.sortable.very.basic.table.index-list>tbody>tr>td>div>a";

    public ProgramsGenre(WebDriver driver) {
        super(driver);
    }

    public Map<String, String> getAllProgramsItems(String listToCss) {
        Map<String, String> myMap = new HashMap<>();
        List<WebElement> allItems = driver.findElements(By.cssSelector(listToCss));
        String key, value;
        for (WebElement run : allItems) {
            key = run.getAttribute("data-value");
            value = run.getText();
            if (key != null) {
                myMap.put(key, value.trim());
            }
        }
        return myMap;
    }

}
