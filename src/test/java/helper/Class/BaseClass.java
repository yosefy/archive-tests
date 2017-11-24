package helper.Class;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import videoHelper.VideoRecorder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static PageObjects.ArchiveSources.SOURCE_RESULTS_TABLE;
import static PageObjects.ArchiveTopics.TAG_INSIDE_PROGRAM;
import static PageObjects.ArchiveTopics.TOPICS_RESULTS;
import static PageObjects.ProgramsGenre.*;

public class BaseClass {

    protected WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean clickListAndTarget(String list, String target) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        for (WebElement options : listOptions) {
            if (options.getText().trim().equals(target.trim())) {
                highlightElement(options);
                click(options);
                return true;
            }
        }
        return false;
    }

    public String openFirstResultAndReturnTopics(){
        getCssListAndClickOnFirstElement(TOPICS_RESULTS);
        navigate(TAG_INSIDE_PROGRAM);
        String sources = getStringFromWebElementByCSS(TAG_INSIDE_PROGRAM);
        String topics = sources.replace("Lesson topics - ","").trim();
        System.out.println("Topics-" + topics);
        return topics;
    }

    public String openFirstResultAndReturnSources(String part){
        navigate(SOURCE_RESULTS_TABLE + " a");
        clickListAndTarget(SOURCE_RESULTS_TABLE + " a", part);
        navigate(".ui.list .item a");

        String sources = getStringFromWebElementByCSS(".ui.list .item span");

        System.out.println("Sources-" + sources);
        return sources.trim();
    }

    public boolean getCssListAndClickOnFirstElement(String list) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        if (listOptions.get(0).isDisplayed()) {
            navigate(list);
            click(listOptions.get(0));
            return true;
        }
        return false;
    }


    public void click(WebElement elementToClick) {
        Actions action = new Actions(driver);
        action.moveToElement(elementToClick).perform();
        this.isElementLoaded(elementToClick);
        this.highlightElement(elementToClick);
        elementToClick.click();
    }

    // Need to navigate because otherwise selenium can not click on element
    public void navigate(String cssToElement) {
        WebElement element = driver.findElement(By.cssSelector(cssToElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)", "", element);
    }


    public boolean getCssListAndCheckTextIfExist(String list, String target) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        for (WebElement options : listOptions) {
            if (options.getText().trim().equals(target.trim())) {
                this.highlightElement(options);
                print(options.getText(), "Displayed value in: ");
                return true;
            }
        }
        return false;
    }

    private void isElementLoaded(WebElement elementToBeLoaded) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
    }

    protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid red'", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='none'", element);
    }

    public String returnValue(String pathToField) {
        WebElement field = driver.findElement(By.cssSelector(pathToField));
        this.highlightElement(field);
        return field.getAttribute("value");
    }

    protected void print(String printValue, String desc) {
        System.out.println(printValue + " - " + desc);
    }

    public List<String> getWebElemListReturnStringList(String cssToList) {
        List<WebElement> myList = driver.findElements(By.cssSelector(cssToList));
        this.isElementLoaded(myList.get(1));
        List<String> listStr = new ArrayList<String>();
        for (WebElement list : myList)
            listStr.add(list.getText());
        return listStr;
    }

    public String getStringFromWebElementByCSS(String path) {
        List<WebElement> listOfSources = driver.findElements(By.cssSelector(path));
        int sourceNameInDailyLesson = listOfSources.size() - 1;
        this.highlightElement(listOfSources.get(sourceNameInDailyLesson));
        return listOfSources.get(sourceNameInDailyLesson).getText();
    }

    public boolean comp2StringArrays(String[] first, String[] second) {
        if (first.length == second.length) {
            for (int i = 0; i < first.length; i++) {
                first[i] = first[i].trim();
                second[i] = second[i].trim();
                if (!first[i].equals(second[i]))
                    return false;
            }
        }
        return true;
    }

    public boolean comp2Strings(String first, String second) {
        // Need to get only the last part of the source
        String[] tokens = second.split(">");
        for (int i = 0; i < tokens.length; i++)
            tokens[i] = tokens[i].trim();

        System.out.println(first);
        System.out.println(tokens[tokens.length - 1]);

        return first.trim().equals(tokens[tokens.length - 1].trim());
    }

    // getCssListAndCheckTextIfExist if all items in secondMap existing in firstMap
    public boolean checkProgramsInTable(Map<String, String> firstMap, Map<String, String> secondMap) {
        for (Map.Entry<String, String> entry : secondMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            if (!firstMap.containsKey(entry.getKey()))
                return false;
        }
        return true;
    }

    public void navigateToPanelAndSection(String MAIN_PANEL, String MAIN_SECTION) {
        boolean ifSideBarIsOpened = driver.findElements(By.cssSelector(SIDE_BAR)).size() != 0;
        if (!ifSideBarIsOpened)
            click(driver.findElement(By.cssSelector(SIDE_BAR_ICON)));

        clickListAndTarget(MAIN_PANEL, MAIN_SECTION);
    }

    private VideoRecorder videoRecord;
    public void deleteVideoLog(String videoPath) throws IOException {
        if (!videoPath.equals("")) {
            List<File> toDelete = videoRecord.getCreatedMovieFiles();
            Files.deleteIfExists(Paths.get(String.valueOf(toDelete.get(0))));
        }
    }

    public boolean paginationUntilEnabled() throws Exception {
        List<String> items;
        while (this.panelStale()) {
            items = this.getWebElemListReturnStringList(PROGRAMS_RESULT_EPISODE);
            for (String item : items) {
                if (item.equals(" ")) {
                    System.out.println("Empty Episode found >>>>>> ");
                    return false;
                }
            }

            if (!this.panelDisabledItem())
                break;
        }
        return true;
    }

    private boolean panelDisabledItem (){
        try {
            List<WebElement> allPanel = driver.findElements(By.cssSelector(PAGINATION_PANEL + ">div"));
            for (WebElement exit : allPanel) {
                navigate(PAGINATION_PANEL);
                if (exit.getAttribute("class").equals("disabled item")) {
                    highlightElement(exit);
                    return false;
                }
            }
            return true;
        }catch (Exception ignored){}
        return true;
    }

    private boolean panelStale(){
            List<WebElement> singleLeftIcon = driver.findElements(By.cssSelector(PAGINATION_PANEL + ">a>i"));
            for (WebElement option : singleLeftIcon) {
                if (option.getAttribute("class").equals("angle right icon")) {
                    navigate(PAGINATION_PANEL);
                    highlightElement(option);
                    click(option);
                    break;
                }
            }
        return true;
    }
}


