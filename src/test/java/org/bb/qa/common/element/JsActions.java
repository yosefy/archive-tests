package org.bb.qa.common.element;

import org.bb.qa.common.drivers.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Set of commonly used actions invoked by executing JavaScript on a web page
 */
public class JsActions {

    protected final JavascriptExecutor js;
    protected final WebDriver driver;

    public JsActions(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
        this.driver = driver;
    }

    public JsActions() {
        this.driver = DriverProvider.getActiveDriver();
        this.js = (JavascriptExecutor) driver;
    }

    public Object execute(String script, WebElement element) {
        return js.executeScript(script, element);
    }

    public Object execute(String script) {
        return js.executeScript("return " + script);
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToSpecificElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebElement element, int offset) {
        int elementPosition = element.getLocation().getY() - offset;
        js.executeScript("window.scroll(0,arguments[0])", elementPosition);
    }

    public void scrollBy(int x, int y) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }

    public void changeElementOpacity(String selector, int value) {
        js.executeScript(
                "document.querySelector(arguments[0]).style.opacity = arguments[1];",
                selector, value);
    }

    public String getWindowErrors() {
        return js.executeScript("return window.errors || ''").toString();
    }

    public Long getCurrentPosition() {
        return (Long) js.executeScript("return window.pageYOffset;");
    }
}
