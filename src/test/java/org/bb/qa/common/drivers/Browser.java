package org.bb.qa.common.drivers;

import org.bb.qa.common.drivers.browsers.ChromeBrowser;
import org.bb.qa.common.drivers.browsers.DefaultBrowser;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Browser {
    CHROME(ChromeBrowser.class, "CHROME"),
    //  FIREFOX(FirefoxBrowser.class, "FF"),
//  CHROME_MOBILE(ChromeBrowser.class, "CHROMEMOBILEMERCURY"),
//  HTMLUNIT(HtmlUnitBrowser.class, "HTMLUNIT"),
//  GHOST(GhostBrowser.class, "GHOST"),
//  CHROME_ANDROID(AndroidBrowser.class, "ANDROID"),
    DEFAULT(DefaultBrowser.class, "");

    private static final Logger logger = LoggerFactory.getLogger(Browser.class);

    private Class<? extends BrowserAbstract> browserClass;
    private String name;

    Browser(Class<? extends BrowserAbstract> browserClass, String name) {
        this.name = name;
        this.browserClass = browserClass;
    }

    public static Browser lookup(String browserName) {
        for (Browser name : Browser.values()) {
            if (name.getName().equalsIgnoreCase(browserName)) {
                return name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public WebDriver getInstance() {
        try {
            return browserClass.newInstance().getInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Could not initialize the browser", e);
        }
        return null;
    }

    public Class<? extends BrowserAbstract> getBrowserClass() {
        return browserClass;
    }
}
