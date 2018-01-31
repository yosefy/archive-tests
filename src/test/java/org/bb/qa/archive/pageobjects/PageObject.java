package org.bb.qa.archive.pageobjects;

import org.apache.commons.lang3.NotImplementedException;
import org.bb.qa.archive.helpers.UrlBuilder;
import org.bb.qa.common.drivers.DriverProvider;
import org.bb.qa.common.element.CommonExpectedConditions;
import org.bb.qa.common.element.JsActions;
import org.bb.qa.common.element.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageObject {

    protected WebDriver driver = DriverProvider.getActiveDriver();
    protected UrlBuilder urlBuilder = new UrlBuilder();
    protected Actions builder;
    protected Wait wait;
    protected JsActions jsActions;

    public PageObject() {
        this.builder = new Actions(driver);
        this.wait = new Wait(driver);
        this.jsActions = new JsActions(driver);

        PageFactory.initElements(driver, this);
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    /**
     * Wait for page object to be present on the page.
     * <p>
     * Implement this where necessary
     */
    public void waitForPresent() {
        throw new NotImplementedException("");
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            wait.forElementVisible(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isElementNotVisible(WebElement element) {
        Boolean res = CommonExpectedConditions.invisibilityOfElement(element).apply(driver);
        return res == null ? false : res;
    }

    /**
     * Takes a parent element and strips out the textContent of all child elements and returns textNode content only
     *
     * @param element the parent element
     * @return the text from the child textNodes
     */
    public static String getSelfText(WebElement element) {
        String text = element.getText().trim();
        List<WebElement> children = element.findElements(By.xpath("./*"));
        for (WebElement child : children) {
            text = text.replaceFirst(child.getText(), "").trim();
        }
        return text;
    }


    /**
     * ##########################################################
     * ################## OLD CODE STARTS HERE ##################
     * ##########################################################
     */

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

    public void waitForClickabilityOfElementLocated(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
    }


    public void getCssListAndClickOnElementByText(String list, String text) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        for (WebElement element : listOptions) {
            if (element.getText().equals(text)) {
                this.waitForClickabilityOfElementLocated(element, 50000);
                navigate(list);
                click(listOptions.get(0));
                break;
            }
        }
    }

    public void moveMouseToElement(WebElement element){
        builder.moveToElement(element).perform();
    }

    public boolean isWebElemAttributeActiveItem(WebElement element) {
        this.highlightElement(element);
        return element.getAttribute("class").equals("active item");
    }

    public void click(WebElement elementToClick) {
        Actions action = new Actions(driver);
        action.moveToElement(elementToClick).perform();
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
                return true;
            }
        }
        return false;
    }

    public void isElementLoaded(WebElement elementToBeLoaded) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
        } catch (Exception ignore) {
        }
    }

    public void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid red'", element);
        this.wait(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='none'", element);
    }

    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String returnValueByAttribute(String pathToField) {
        WebElement field = driver.findElement(By.cssSelector(pathToField));
        this.highlightElement(field);
        return field.getAttribute("value");
    }

    public List<String> getWebElemListReturnStringList(String cssToList) {
        List<WebElement> myList = driver.findElements(By.cssSelector(cssToList));
        this.isElementLoaded(myList.get(1));
        List<String> listStr = new ArrayList<>();
        for (WebElement list : myList)
            listStr.add(list.getText());
        return listStr;
    }

    public List<WebElement> getCssPathReturnWebElementList(String cssToList) {
        return driver.findElements(By.cssSelector(cssToList));
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

//    public void navigateToPanelAndSection(String MAIN_SECTION) {
//        // Check browser size and decide if displayed hamburger or not
//        if (driver.manage().window().getSize().getWidth() < 1505)
//            click(driver.findElement(By.cssSelector(ProgramsGenre.SIDE_BAR_ICON)));
//
//        clickListAndTarget(ProgramsGenre.VERTICAL_HAMBURGER_MENU, MAIN_SECTION);
//    }
//
//    public void chooseSectionAndOpenItemByText(String section, String cssPathToOpenFirstElement, String text) {
//        this.navigateToPanelAndSection(section);
//        this.getCssListAndClickOnElementByText(cssPathToOpenFirstElement, text);
//    }
//
//    public boolean paginationUntilEnabled() {
//        List<String> items;
//        while (this.panelStale()) {
//            items = this.getWebElemListReturnStringList(ProgramsGenre.PROGRAMS_RESULT_EPISODE);
//            for (String item : items) {
//                if (item.equals(" ")) {
//                    System.out.println("Empty section is found >>>>>> ");
//                    return false;
//                }
//            }
//
//            if (!this.panelDisabledItem())
//                break;
//        }
//        return true;
//    }
//
//    private boolean panelDisabledItem() {
//        try {
//            List<WebElement> allPanel = driver.findElements(By.cssSelector(ProgramsGenre.PAGINATION_PANEL + ">div"));
//            for (WebElement exit : allPanel) {
//                navigate(ProgramsGenre.PAGINATION_PANEL);
//                if (exit.getAttribute("class").equals("disabled item")) {
//                    highlightElement(exit);
//                    return false;
//                }
//            }
//            return true;
//        } catch (Exception ignored) {
//        }
//        return true;
//    }
//
//    private boolean panelStale() {
//        List<WebElement> singleLeftIcon = driver.findElements(By.cssSelector(ProgramsGenre.PAGINATION_PANEL + ">a>i"));
//        for (WebElement option : singleLeftIcon) {
//            if (option.getAttribute("class").equals("angle right icon")) {
//                navigate(ProgramsGenre.PAGINATION_PANEL);
//                highlightElement(option);
//                click(option);
//                break;
//            }
//        }
//        return true;
//    }


    public void getCoordinatesOfElement(String s) {
        WebElement element = driver.findElement(By.cssSelector(s));
        highlightElement(element);

        Point point = element.getLocation();
        int xcord = point.getX();
        int ycord = point.getY();

        Actions builder = new Actions(driver);
        builder.dragAndDropBy(element, xcord, ycord + 50).click().build().perform();
    }

    public void scrollToElementIgnoringSteakHeader(WebElement element, int coordinateYCorretion) {
        int x = element.getLocation().x;
        int y = element.getLocation().y + coordinateYCorretion;
        ((JavascriptExecutor) driver).executeScript(String.format("window.scrollTo(%d,%d)", x, y), "");
    }

    public void dragAndDropElementByActionOnVertical(WebElement dragFrom, int yOffset) {
        Actions action = new Actions(driver);
        action.clickAndHold(dragFrom).moveByOffset(0, yOffset).build().perform();
        driver.getTitle();
    }

}









