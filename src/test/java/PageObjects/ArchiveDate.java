package PageObjects;

import helper.Class.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArchiveDate extends BaseClass {

    public ArchiveDate(WebDriver driver) {
        super(driver);
    }


    private final static String MAIN_VERTICAL_MENU = ".ui.fluid.pointing.vertical.menu>a";
    private final static String MENU_DAILY_LESSONS = "Daily Lessons";
    public final static String MENU_DAILY_TV_VIDEO = "TV & Video Programs";
    public final static String MENU_DAILY_LECTURES_LESSONS = "Lectures & Lessons";
    public final static String MENU_DAILY_KAB_SOURCES = "Kabbalah Sources";
    public final static String MENU_DAILY_EVENTS = "Events";
    public final static String MENU_DAILY_BOOKS = "Books";
    public final static String MENU_DAILY_TOPICS = "Topics";
    public final static String MENU_DAILY_PUBLICATIONS = "Publications";
    public final static String MENU_DAILY_PHOTOS = "Photos";


    private final static String DAILY_LESSON_PANEL = ".ui.blue.large.pointing.secondary.index-filters.menu>a";
    private final static String PANEL_DATE = "Date";
    private final static String PANEL_SOURCES = "Sources";
    private final static String PANEL_TOPICS = "Topics";



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
    public final static String DATE_FIRST = ".eight:nth-child(1)>div>input";
    public final static String DATE_SECOND = ".eight:nth-child(2)>div>input";

    public final static String LABEL = ".filter-tags>div";
    private final static String REMOVE_LABEL = ".close.icon";

    private final static String APPLY_BTN = ".ui.button:nth-child(2)";
    public final static String CANCEL_BTN = ".ui.button:nth-child(1)";



    public String saveAndReturnDateRangeLabel(String listToDropDownRange, String range) {
        WebElement openDropDown = driver.findElement(By.cssSelector(".dropdown.icon"));
        click(openDropDown);
        clickListAndTarget(listToDropDownRange, range);
        WebElement applyBtn = driver.findElement(By.cssSelector(APPLY_BTN));
        click(applyBtn);
        return driver.findElement(By.cssSelector(LABEL)).getText().trim();
    }

    public List<String> parseDateRangeLabelWithRange(String label) {
        List<String> myList = new ArrayList<String>();
        if (label.contains("-")) {
            myList = new ArrayList<String>(Arrays.asList(label.split("-")));
            for (int i = 0; i < myList.size(); i++)
                myList.set(i, convertToDateFromLabel(myList.get(i)));
            return myList;
        }

        else {
            myList.set(0, convertToDateFromLabel(label));
            return myList;
        }
    }

    public String convertToDateFromLabel(String value) {
        LocalDate dateName = LocalDate.parse(value.trim(),DateTimeFormatter.ofPattern("d MMM yyyy"));
        System.out.println("DateTimeFormatter.ofPattern(d MMM yyyy)- " + dateName.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return dateName.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String convertToDateFromUi (String dateFromUi){
        LocalDate dateName = LocalDate.parse(dateFromUi.trim(),DateTimeFormatter.ofPattern("M/d/yyyy"));
        System.out.println("DateTimeFormatter.ofPattern(M/d/yyyy)- " + dateName.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return dateName.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    public void navToDailyLessonsDate() {
//        clickListAndTarget(ArchiveDate.MAIN_VERTICAL_MENU, ArchiveDate.MENU_DAILY_LESSONS);
        clickListAndTarget(ArchiveDate.DAILY_LESSON_PANEL, ArchiveDate.PANEL_DATE);
    }

    public void removeLabel () {
        click(driver.findElement(By.cssSelector(REMOVE_LABEL)));
    }

    private String getCurateLabelLastMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        YearMonth thisMonth = YearMonth.now();
        YearMonth lastMonth = thisMonth.minusMonths(1);
        LocalDate initial = LocalDate.of(lastMonth.getYear(), lastMonth.getMonth(), 1);
        LocalDate start = initial.withDayOfMonth(1);
        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        print(start.toString(),"start");
        print(end.toString(),"end");
        return start.format(formatter) + " - " + end.format(formatter);
    }


    public void navToDailyLessonsSources() {
        clickListAndTarget(ArchiveDate.MAIN_VERTICAL_MENU, ArchiveDate.MENU_DAILY_LESSONS);
        clickListAndTarget(ArchiveDate.DAILY_LESSON_PANEL, ArchiveDate.PANEL_SOURCES);
    }

    public void navToDailyLessonsTopics() {
        clickListAndTarget(ArchiveDate.MAIN_VERTICAL_MENU, ArchiveDate.MENU_DAILY_LESSONS);
        clickListAndTarget(ArchiveDate.DAILY_LESSON_PANEL, ArchiveDate.PANEL_TOPICS);
    }

}
