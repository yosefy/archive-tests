package org.bb.qa.common.drivers;


import org.bb.qa.common.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DriverProvider {

    private static final List<WebDriver> drivers = new ArrayList<>();
    private static int activeBrowserIndex = 0;

    private static final Logger logger = LoggerFactory.getLogger(DriverProvider.class);

    private DriverProvider() {
    }

    private static void newInstance() {
        drivers.add(Browser.lookup(Configuration.getBrowser()).getInstance());
    }

    private static WebDriver getBrowserDriver(int index) {
        for (; drivers.size() <= index; ) {
            newInstance();
        }

        return drivers.get(index);
    }

    public static WebDriver getActiveDriver() {
        return getBrowserDriver(activeBrowserIndex);
    }

    public static WebDriver switchActiveWindow(int index) {
        activeBrowserIndex = index;
        return getActiveDriver();
    }

    public static void close() {
        for (WebDriver driver : drivers) {
            if (driver != null) {
                try {
                    driver.manage().deleteAllCookies();
                    driver.quit();
                } catch (UnsatisfiedLinkError | NoClassDefFoundError | NullPointerException e) {
                    logger.info("Error closing Browser", e);
                }
            }
        }
        drivers.clear();
        activeBrowserIndex = 0;
    }
}
