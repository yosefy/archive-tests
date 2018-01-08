package org.bb.qa.archive.suites;

import com.automation.remarks.testng.VideoListener;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//@Listeners(VideoListener.class)
public class BaseSuite {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        ChromeDriverManager.getInstance().setup();

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup
        options.addArguments("disable-infobars");     //to disable infobars

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", chromePrefs);

        cap.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}