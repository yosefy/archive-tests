package BaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseClass {

    protected WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendListAndTarget(String list, String target) {
        List<WebElement> filters = driver.findElements(By.cssSelector(list));
        for (WebElement options : filters){
            if (options.getText().trim().equals(target.trim())) {
                this.highlightElement(options);
                click(options);
                break;
            }
        }
    }

    public boolean IFsendListAndTarget(String list, String target) {
        List<WebElement> filters = driver.findElements(By.cssSelector(list));
        for (WebElement options : filters){
            if (options.getText().trim().equals(target.trim())) {
                this.highlightElement(options);
                return true;
            }
        }
        return false;
    }

    public void click(WebElement elementToClick){
        this.isElementLoaded(elementToClick);
        click(elementToClick);
    }

    private WebElement isElementLoaded(WebElement elementToBeLoaded) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
    }

    public void highlightElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid red'", element);
        System.out.println(element.getText());
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='none'", element);
    }
}
