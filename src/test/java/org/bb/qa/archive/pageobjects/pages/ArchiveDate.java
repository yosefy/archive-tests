package org.bb.qa.archive.pageobjects.pages;

import org.bb.qa.archive.helpers.Utils;
import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ArchiveDate extends PageObject {

    public final static String DAILY_LESSON_PANEL = ".ui.blue.large.pointing.secondary.index-filters.menu>div>a";
    public final static String PANEL_DATE = "Date";
    public final static String PANEL_SOURCES = "Sources";
    public final static String PANEL_TOPICS = "Topics";
    public final static String PROGRAMS = "Programs";
    public final static String DAILY_LESSONS = "Daily Lessons";
    public final static String DAILY_LESSONS_SECOND_ITEM = ".ui.container.padded>table>tbody>tr>td:nth-child(1)>a";
    public final static String DATE_DROPDOWN_LIST_SELECTED = ".ui.fluid.item.dropdown>div:nth-child(1)";
    public final static String DATE_DROPDOWN_LIST = ".ui.fluid.item.dropdown>div>div";
    public final static String LIST_TODAY = "Today";
    public final static String LIST_YESTERDAY = "Yesterday";
    public final static String LIST_LAST_7_DAYS = "Last 7 Days";
    public final static String LIST_LAST_30_DAYS = "Last 30 Days";
    public final static String LIST_LAST_MONTH = "Last Month";
    public final static String LIST_THIS_MONTH = "This Month";
    public final static String LIST_CUSTOM_RANGE = "Custom Range";
    public final static String DATE_FIRST = ".eight:nth-child(1)>div>input";
    public final static String DATE_SECOND = ".eight:nth-child(2)>div>input";
    public final static String LABEL = ".filter-tags>div";
    public final static String LABEL_OPEN = ".ui.blue.basic.button";
    public final static String CANCEL_BTN = ".ui.button:nth-child(1)";
    private final static String REMOVE_LABEL = ".close.icon";
    private final static String APPLY_BTN = ".ui.button:nth-child(2)";

    public String saveAndReturnDateRangeLabel(String listToDropDownRange, String range) {
        WebElement openDropDown = driver.findElement(By.cssSelector(".sixteen.wide.column>div"));
        click(openDropDown);
        clickListAndTarget(listToDropDownRange, range);
        WebElement applyBtn = driver.findElement(By.cssSelector(APPLY_BTN));
        click(applyBtn);
        return driver.findElement(By.cssSelector(LABEL)).getText().trim();
    }

    public List<String> parseDateRangeLabelWithRange(String label) {
        List<String> myList = new ArrayList<>();
        if (label.contains("-")) {
            myList = new ArrayList<>(Arrays.asList(label.split("-")));
            for (int i = 0; i < myList.size(); i++)
                myList.set(i, Utils.convertToDateFromLabel(myList.get(i)));
            return myList;
        } else {
            myList.set(0, Utils.convertToDateFromLabel(label));
            return myList;
        }
    }

    public String convertToDateFromUi(String dateFromUi) {
        LocalDate dateName = LocalDate.parse(dateFromUi.trim(), DateTimeFormatter.ofPattern("M/d/yyyy"));
        return dateName.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // if label existing
    public void openDateRangeByClickOnFilter() {
        boolean ifLabelExist = driver.findElements(By.cssSelector(".ui.blue.basic.button")).size() != 0;
        if (ifLabelExist)
            click(driver.findElement(By.cssSelector(LABEL_OPEN)));
    }

    public void removeLabel() {
        click(driver.findElement(By.cssSelector(REMOVE_LABEL)));
    }

}
