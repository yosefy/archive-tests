package org.bb.qa.common.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class CommonExpectedConditions {

    private CommonExpectedConditions() {

    }

    /**
     * An Expectation for checking an element is visible and not enabled such that you can not click
     * it.
     *
     * @param givenElement element to be checked
     */
    public static ExpectedCondition<WebElement> elementNotToBeClickable(final WebElement givenElement) {
        return new ExpectedCondition<WebElement>() {

            public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions
                    .visibilityOf(givenElement);

            public WebElement apply(WebDriver driver) {
                WebElement element = visibilityOfElement.apply(driver);
                try {
                    if (element != null && !element.isEnabled()) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "element to be clickable: " + givenElement.getTagName();
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfElement(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    Wait.changeImplicitWait(driver, 0, TimeUnit.SECONDS);
                    Boolean isDisplayed = element.isDisplayed();
                    return !isDisplayed;
                } catch (StaleElementReferenceException | NoSuchElementException e) {
                    // Returns true because stale element reference implies that element
                    // is no longer visible.
                    return true;
                } finally {
                    Wait.restoreDefaultImplicitWait(driver);
                }
            }

            @Override
            public String toString() {
                return "element to no longer be visible: " + element.toString();
            }
        };
    }

    public static ExpectedCondition<Boolean> elementNotPresent(final By bySelector) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.findElements(bySelector).size() < 1);
            }

            @Override
            public String toString() {
                return String.format("Element with provided selector still present!");
            }
        };
    }

}
