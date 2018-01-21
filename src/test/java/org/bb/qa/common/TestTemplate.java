package org.bb.qa.common;

import org.bb.qa.common.annotations.DontRun;
import org.bb.qa.common.annotations.InBrowser;
import org.bb.qa.common.configuration.Configuration;
import org.bb.qa.common.drivers.DriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestTemplate {

    private static final Logger logger = LoggerFactory.getLogger(TestTemplate.class);

    protected WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        TestContext.writeMethodName(method);
//        PageObjectLogging.start(method);

        Configuration.clearCustomTestProperties();

        String browser = Configuration.getBrowser();
        setPropertiesFromAnnotationsOnDeclaringClass(method.getDeclaringClass());
        setPropertiesFromAnnotationsOnMethod(method);
        String currentBrowser = Configuration.getBrowser();

        if (!browser.equals(currentBrowser)) {
            logger.warn("Parameter override",
                    "Browser parameter changed by annotation"
                            + ", old value: " + browser + ", new value: " + currentBrowser);
        }

//        prepareURLs();

        if (isTestExcludedFromEnv(method)) {
            throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");
        }

        driver = DriverProvider.getActiveDriver();
        setWindowSize();

        logger.info("######## Start method: {}", TestContext.getCurrentMethodName());
//        loadFirstPage();
    }

    private void setTestProperty(String key, String value) {
        if (!"".equals(value)) {
            Configuration.setTestValue(key, value);
        }
    }

    private void setPropertiesFromAnnotationsOnDeclaringClass(Class<?> declaringClass) {
        if (declaringClass.isAnnotationPresent(InBrowser.class)) {
            setTestProperty("browser", declaringClass.getAnnotation(InBrowser.class).browser().getName());
            setTestProperty("browserSize", declaringClass.getAnnotation(InBrowser.class).browserSize());
        }
    }

    private void setPropertiesFromAnnotationsOnMethod(Method method) {
        if (method.isAnnotationPresent(InBrowser.class)) {
            setTestProperty("browser", method.getAnnotation(InBrowser.class).browser().getName());
            setTestProperty("browserSize", method.getAnnotation(InBrowser.class).browserSize());
        }
    }

    /**
     * Return false if test is excluded from running on current test environment
     */
    private boolean isTestExcludedFromEnv(Method method) {
        if (method.isAnnotationPresent(DontRun.class)) {
            String[] excludedEnvs = method.getAnnotation(DontRun.class).env();

            for (String excludedEnv : excludedEnvs) {
                if (Configuration.getEnv().contains(excludedEnv)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void setWindowSize() {
        Dimension browserSize = Configuration.getBrowserSize();
        if (browserSize != null) {
            driver.manage().window().setSize(browserSize);
        } else {
            driver.manage().window().maximize();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        logger.info("######## Stop method: {}", TestContext.getCurrentMethodName());
        DriverProvider.close();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        DriverProvider.close();
    }
}