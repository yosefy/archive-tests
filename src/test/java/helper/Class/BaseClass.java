package helper.Class;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public void getCssListAndClickOnFirstElement(String list) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        if (listOptions.get(0).isDisplayed()) {
            navigate(list);
            click(listOptions.get(0));
        }
    }

    public boolean isAttributeActive(WebElement element) {
        this.highlightElement(element);
        return element.getAttribute("class").equals("active item");
    }

    public void click(WebElement elementToClick) {
        Actions action = new Actions(driver);
        action.moveToElement(elementToClick).perform();
        this.isElementLoaded(elementToClick);
        this.highlightElement(elementToClick);
        elementToClick.click();
    }

    // Need to navigate because otherwise selenium can not click on element
    protected void navigate(String cssToElement) {
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

    void isElementLoaded(WebElement elementToBeLoaded) {
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

    public String returnValueByAttribute(String pathToField) {
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
        List<String> listStr = new ArrayList<>();
        for (WebElement list : myList)
            listStr.add(list.getText());
        return listStr;
    }

    public List<WebElement> getWebElemListReturnWebElementList(String cssToList) {
        return driver.findElements(By.cssSelector(cssToList));
    }

    // get two chars[] split by > and check from the end to start
    public boolean comp2StringArrays(String[] first, String[] second) {
        if (first.length == second.length) {
            for (int i = first.length - 1; i > 0; i--) {
                System.out.println(first[i] = first[i].trim());
                System.out.println(second[i] = second[i].trim());
                if (!first[i].equals(second[i]))
                    return false;
            }
        }
        return true;
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

    public boolean paginationUntilEnabled() {
        List<String> items;
        while (this.panelStale()) {
            items = this.getWebElemListReturnStringList(PROGRAMS_RESULT_EPISODE);
            for (String item : items) {
                if (item.equals(" ")) {
                    System.out.println("Empty section is found >>>>>> ");
                    return false;
                }
            }

            if (!this.panelDisabledItem())
                break;
        }
        return true;
    }

    private boolean panelDisabledItem() {
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
        } catch (Exception ignored) {
        }
        return true;
    }

    private boolean panelStale() {
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

    public void CheckBrokenImage(String pathToCss){
        List<WebElement> list = driver.findElements(By.cssSelector(pathToCss));

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return arguments[0].complete" +
                "&& typeof arguments[0].naturalWidth != 'undefined'" +
                "&& arguments[0].naturalWidth > 0";

        for(WebElement element : list){
            try {
                if (jsExec.executeScript(script, element).equals(false))
                    System.out.println(String.format("Image [%s] is broken : ",element.getAttribute("src")));
                else
                    System.out.println(String.format("Image [%s] doesn't broken : ",element.getAttribute("src")));
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void UploadFileByRobot() throws AWTException {
        String pathTo = "C:\\Users\\b\\Desktop\\Counter\\Capture.PNG";
        StringSelection ss = new StringSelection(pathTo);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

}


