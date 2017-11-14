package helper.Class;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BaseClass {

    protected WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean clickListAndTarget(String list, String target) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        for (WebElement options : listOptions){
            if (options.getText().trim().contains(target.trim())) {
                highlightElement(options);
                click(options);
                return true;
            }
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
    public void navigate(String cssToElement){
        WebElement element = driver.findElement(By.cssSelector(cssToElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", "", element);
    }

    public boolean check(String list, String target) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        for (WebElement options : listOptions){
            if (options.getText().trim().equals(target.trim())) {
                this.highlightElement(options);
                print(options.getText(),"Displayed value in: ");
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
        try {Thread.sleep(500);}
        catch (InterruptedException e) {e.printStackTrace();}
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='none'", element);
    }

    public String returnValue(String pathToField) {
        WebElement field = driver.findElement(By.cssSelector(pathToField));
        this.highlightElement(field);
        return field.getAttribute("value");
    }

    protected void print(String printValue, String desc){
        System.out.println(printValue + " - " + desc);
    }

    public List<String> getList(String cssToList) {
        List<WebElement> myList = driver.findElements(By.cssSelector(cssToList));
        this.isElementLoaded(myList.get(1));
        List<String> listStr = new ArrayList<String>();
        for(WebElement list : myList)
            listStr.add(list.getText());
        return listStr;
    }

    public String getStringFromWebElementByCSS(String path) {
        List<WebElement> listOfSources = driver.findElements(By.cssSelector(path));
        int sourceNameInDailyLesson = listOfSources.size() - 1;
        this.highlightElement(listOfSources.get(sourceNameInDailyLesson));
        return listOfSources.get(sourceNameInDailyLesson).getText();
    }

    public boolean comp2StringArrays(String[] first, String[] second){
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

    public boolean comp2Strings(String first, String second){
        // Need to get only the last part of the source
        String[] tokens = second.split(">");
        for (int i = 0; i < tokens.length; i++)
            tokens[i] = tokens[i].trim();

        System.out.println(first);
        System.out.println(tokens[tokens.length-1]);

        return first.trim().equals(tokens[tokens.length - 1].trim());
    }

    // check if all items in secondMap existing in firstMap
    public boolean checkProgramsInTable(Map<String, String> firstMap, Map<String, String> secondMap) {
        for (Map.Entry<String, String> entry : secondMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            if (!firstMap.containsKey(entry.getKey()))
                return false;
        }
        return true;
    }

}


