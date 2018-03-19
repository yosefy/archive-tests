package org.bb.qa.common.drivers.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.bb.qa.common.configuration.Configuration;
import org.bb.qa.common.configuration.Emulator;
import org.bb.qa.common.drivers.BrowserAbstract;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeBrowser extends BrowserAbstract {

    private ChromeOptions options = new ChromeOptions();

    @Override
    public void setOptions() {
        options.addArguments("--headless");
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup
        options.addArguments("--disable-infobars");     //to disable infobars



        Emulator emulator = Configuration.getEmulator();
        if (!emulator.equals(Emulator.DEFAULT)) {
            Map<String, Object> mobileEmulation = new HashMap<>();
            if (StringUtils.isNotBlank(emulator.getUserAgent())) {
                mobileEmulation.put("userAgent", emulator.getUserAgent());
            }
            if (StringUtils.isNotBlank(emulator.getDeviceName())) {
                mobileEmulation.put("deviceName", emulator.getDeviceName());
            } else {
                mobileEmulation.put("deviceMetrics", emulator.getDeviceMetrics());
            }

            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        }
    }


    @Override
    public WebDriver create() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();

        caps.setCapability(ChromeOptions.CAPABILITY, options);
//    cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return new ChromeDriver(caps);
    }

}
