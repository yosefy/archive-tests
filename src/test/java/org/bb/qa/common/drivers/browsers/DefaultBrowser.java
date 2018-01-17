package org.bb.qa.common.drivers.browsers;


import org.bb.qa.common.configuration.Configuration;
import org.bb.qa.common.drivers.Browser;
import org.bb.qa.common.drivers.BrowserAbstract;
import org.openqa.selenium.WebDriver;

public class DefaultBrowser extends BrowserAbstract {

    private BrowserAbstract browserClass;

    DefaultBrowser() {
        try {
            browserClass = Browser.lookup(Configuration.getBrowser()).getBrowserClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
//      PageObjectLogging.logError("Could not initialize the browser", e);
        }
    }

    @Override
    public void setOptions() {
        browserClass.setOptions();
    }

    @Override
    public WebDriver create() {
        return browserClass.create();
    }

}
