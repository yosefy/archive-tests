package helper.Class;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BaseClass {

    protected WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected boolean clickListAndTarget(String list, String target) {
        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
        for (WebElement options : listOptions){
            if (options.getText().trim().equals(target.trim())) {
                click(options);
                return true;
            }
        }
        return false;
    }

    protected void click(WebElement elementToClick) {
        this.isElementLoaded(elementToClick);
        this.highlightElement(elementToClick);
        elementToClick.click();
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
        wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
    }

    public void highlightElement(WebElement element) {
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

    public static boolean comp2str(String firstValue, String secondValue) {
        return firstValue.trim().toLowerCase().equals(secondValue.trim().toLowerCase());
    }

    public List<String> getList(String cssToList) {
        List<WebElement> myList = driver.findElements(By.cssSelector(cssToList));
        List<String> listStr = new ArrayList<String>();

        for(WebElement list : myList)
            listStr.add(list.getText());

        return listStr;
    }
}


