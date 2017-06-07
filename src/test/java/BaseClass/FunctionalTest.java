package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "C:\\automation\\drivers\\geckodriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

//        System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("disable-infobars");
//        WebDriver driver = new ChromeDriver(options);

    }

    @AfterMethod
    public void cleanUp(){
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}