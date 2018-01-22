package org.bb.qa.archive.pageobjects.widgets.unit;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UnitInfo extends PageObject {

    @Override
    public void waitForPresent() {
        this.wait.forElementPresent(By.className("unit-info"));
    }
}
