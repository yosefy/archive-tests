package helpers;

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

@Listeners(VideoListener.class)
public class BaseSuite {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        ChromeDriverManager.getInstance().setup();
        System.setProperty("log4j2.debug", "1");
//        System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver\\chromedriver.exe");

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("disable-infobars");
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();

        String downloadFilepath = "C:\\SPORT";

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions"); //to disable browser extension popup
        options.addArguments("disable-infobars");     //to disable infobars

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}