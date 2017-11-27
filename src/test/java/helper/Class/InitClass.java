package helper.Class;

import com.automation.remarks.testng.VideoListener;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

@Listeners(VideoListener.class)
public class InitClass {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        ChromeDriverManager.getInstance().setup();
//        System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}