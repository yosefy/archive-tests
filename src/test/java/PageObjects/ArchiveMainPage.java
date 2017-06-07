package PageObjects;

import BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArchiveMainPage extends BaseClass {

    public ArchiveMainPage(WebDriver driver) {
        super(driver);
    }


    public final static String MAIN_VERTICAL_MENU = ".ui.fluid.pointing.vertical.menu>a";
    public final static String MENU_DAILY_LESSONS = "Daily Lessons";
    public final static String MENU_DAILY_TV_VIDEO = "TV & Video Programs";
    public final static String MENU_DAILY_LECTURES_LESSONS = "Lectures & Lessons";
    public final static String MENU_DAILY_KAB_SOURCES = "Kabbalah Sources";
    public final static String MENU_DAILY_EVENTS = "Events";
    public final static String MENU_DAILY_BOOKS = "Books";
    public final static String MENU_DAILY_TOPICS = "Topics";
    public final static String MENU_DAILY_PUBLICATIONS = "Publications";
    public final static String MENU_DAILY_PHOTOS = "Photos";


    public final static String DAILY_LESSON_PANEL = ".ui.blue.large.pointing.secondary.index-filters.menu>a";
    public final static String PANEL_DATE = "Date";
    public final static String PANEL_SOURCES = "Sources";
    public final static String PANEL_TOPICS = "Topics";



//    public final static String filterPageNameCSS = ".sixteen.wide.column>div.ui.segment>a";
//    public final static String dropDownDatePicker = ".sixteen.wide.column .ui.fluid.item.dropdown .menu.transition>div";


    public final static String DATE_DROPDOWN_LIST_SELECTED = ".ui.fluid.item.dropdown>div:nth-child(1)";
    public final static String DATE_DROPDOWN_LIST = ".menu.transition.visible>div";
    public final static String LIST_TODAY = "Today";
    public final static String LIST_YESTERDAY = "Yesterday";
    public final static String LIST_LAST_7_DAYS = "Last 7 Days";
    public final static String LIST_LAST_30_DAYS = "Last 30 Days";
    public final static String LIST_LAST_MONTH = "Last Month";
    public final static String LIST_THIS_MONTH = "This Month";
    public final static String LIST_CUSTOM_RANGE = "Custom Range";
    public final static String DATEPIC_FIRST = ".eight:nth-child(1)>div>input";
    public final static String DATEPIC_SECOND = ".eight:nth-child(2)>div>input";

    public final static String LABEL = ".ui.label";


    public void openDatePickerAndChooseRange (String listToDropDownRange, String range) throws InterruptedException {
        WebElement openDropDown = driver.findElement(By.cssSelector(".dropdown.icon"));
        this.click(openDropDown);
        this.sendListAndTarget(listToDropDownRange, range);
        WebElement applyBtn = driver.findElement(By.cssSelector(".ui.primary.button"));
        this.click(applyBtn);
    }

    public void navToDailyLessonsDate(){
        this.sendListAndTarget(ArchiveMainPage.MAIN_VERTICAL_MENU, ArchiveMainPage.MENU_DAILY_LESSONS);
        this.sendListAndTarget(ArchiveMainPage.DAILY_LESSON_PANEL, ArchiveMainPage.PANEL_DATE);
    }

    public void searchByDate(String date) throws InterruptedException {
        List<WebElement> pages = driver.findElements(By.cssSelector(".stretched.top.aligned.row>div>a"));
        for (WebElement option : pages) {
            System.out.println(option.getText());
            if (option.getText().contains(date)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid red'", option);
                Thread.sleep(500);
                click(option);
                break;
            }
        }
    }


    public boolean checkIFDisplayedFilterTag(String list, String target) {
        return IFsendListAndTarget(list,target);
    }

    public boolean checkIFSelectedDateDropDownList(String list, String target) {
        return IFsendListAndTarget(list, target);
    }

    public void checkDatePicker(String first, String second) {

    }
}
