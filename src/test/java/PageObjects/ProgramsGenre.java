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

import static PageObjects.ArchiveDate.DAILY_LESSON_PANEL;

public class ProgramsGenre extends BaseClass {

    public ProgramsGenre(WebDriver driver) {
        super(driver);
    }


    private final static String GENRE_MAIN_LEFT_PANEL = ".ui.blue.tiny.fluid.vertical.menu>a.item";

    private final static String SIDE_BAR = ".layout__sidebar.is-active";
    private final static String SIDE_BAR_ICON = ".sidebar.icon";
    private final static String VERTICAL_HAMBURGER_MENU = ".ui.blue.huge.borderless.fluid.vertical.menu>a";


    public void ChoosePanel (String target){
        boolean ifLabelExist = driver.findElements(By.cssSelector(GENRE_MAIN_LEFT_PANEL)).size()!=0;
        if (ifLabelExist)
            clickListAndTarget(GENRE_MAIN_LEFT_PANEL, target);
    }

    public void navToPrograms(){
        boolean ifSideBarIsOpened = driver.findElements(By.cssSelector(SIDE_BAR)).size()!=0;
        if(!ifSideBarIsOpened)
            click(driver.findElement(By.cssSelector(SIDE_BAR_ICON)));
        clickListAndTarget(VERTICAL_HAMBURGER_MENU, "Programs");
    }

}
