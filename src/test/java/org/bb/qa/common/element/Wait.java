package org.bb.qa.common.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Wait {

    private static final int DEFAULT_TIMEOUT = 15;

    private static final Logger logger = LoggerFactory.getLogger(Wait.class);

    private WebDriverWait wait;
    private WebDriver driver;

    public Wait(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
    }

    public WebElement forElementPresent(By by) {
        return forCondition(ExpectedConditions::presenceOfElementLocated, by);
    }

    public WebElement forElementPresent(By by, Duration duration) {
        return forCondition(ExpectedConditions::presenceOfElementLocated, by, duration);
    }

    public WebElement forElementClickable(WebElement element) {
        return forCondition(ExpectedConditions::elementToBeClickable, element);
    }

    public WebElement forElementClickable(WebElement element, Duration duration) {
        return forCondition(ExpectedConditions::elementToBeClickable, element, duration);
    }

    public WebElement forElementClickable(By by) {
        return forCondition(ExpectedConditions::elementToBeClickable, by);
    }

    public WebElement forElementClickable(By by, Duration duration) {
        return forCondition(ExpectedConditions::elementToBeClickable, by, duration);
    }

    public WebElement forElementVisible(WebElement element) {
        return forCondition(ExpectedConditions::visibilityOf, element);
    }

    public WebElement forElementVisible(WebElement element, Duration duration) {
        return forCondition(ExpectedConditions::visibilityOf, element, duration);
    }

    public WebElement forElementVisible(By by) {
        return forCondition(ExpectedConditions::visibilityOfElementLocated, by);
    }

    public WebElement forElementVisible(By by, Duration duration) {
        return forCondition(ExpectedConditions::visibilityOfElementLocated, by, duration);
    }

    public void forUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    public void forX(Duration duration) {
        logger.info("Wait for {} ms", duration.toMillis());
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            logger.error("Wait.forX", e);
        }
    }

    public <T, V> V forCondition(Function<T, ExpectedCondition<V>> f, T x) {
        presetConditionImplicitWait(x);
        try {
            return wait.until(f.apply(x));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public <T, V> V forCondition(Function<T, ExpectedCondition<V>> f, T x, Duration duration) {
        presetConditionImplicitWait(x);
        try {
            return new WebDriverWait(driver, (int) duration.getSeconds()).until(f.apply(x));
        } finally {
            restoreDefaultImplicitWait();
        }
    }

    public <T> void presetConditionImplicitWait(T x) {
        if (x instanceof WebElement) {
            changeImplicitWait(0, TimeUnit.MILLISECONDS);
            try {
                ((WebElement) x).getTagName();
            } catch (WebDriverException e) {
                logger.warn("Element not initialized properly", e);
            }
        } else {
            changeImplicitWait(250, TimeUnit.MILLISECONDS);
        }
    }

    public static void changeImplicitWait(WebDriver driver, int value, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(value, timeUnit);
    }

    public static void restoreDefaultImplicitWait(WebDriver driver) {
        Wait.changeImplicitWait(driver, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    private void changeImplicitWait(int value, TimeUnit timeUnit) {
        Wait.changeImplicitWait(driver, value, timeUnit);
    }

    private void restoreDefaultImplicitWait() {
        Wait.restoreDefaultImplicitWait(driver);
    }

}
