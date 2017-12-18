//package helper.Class;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.Reporter;
//
//import java.awt.*;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.StringSelection;
//import java.awt.datatransfer.Transferable;
//import java.awt.event.InputEvent;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.concurrent.TimeUnit;
//
//public class based_from_nano {
//
//    package framework.helpers;
//
//import autoitx4java.AutoItX;
//import framework.Browser;
//import framework.BrowserFactory;
//import framework.WebWindow;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.Reporter;
//import pageobject.BaseAutoIt;
//
//import java.awt.*;
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.StringSelection;
//import java.awt.datatransfer.Transferable;
//import java.awt.event.InputEvent;
//import java.awt.event.KeyEvent;
//import java.io.File;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//    /**
//     * Created by user on 10/02/2015.
//     */
//    public class DriverHelper {
//
//        private WebDriver driver;// = new FirefoxDriver();
//        public static final int TIMEOUT_THREE_SECONDS = 3;
//        public static final int WAIT_FOR_LOAD_PAGE = 15;
//        int _TIME_FOR_PAGE_TO_UPDATE = 15;
//        int _TIME_FOR_PAGE_ELEMENTS_TO_UPDATE = 15;
//        String title = "nanoRep Console";
//
//        public DriverHelper(String browser) {
//            this.createDriver(browser);
//        }
//
//        public String getHandleOfCurrentWindow() {
//            return driver.getWindowHandle();
//        }
//
//        public void createDriver(String browser) {
//            this.setDriver(BrowserFactory.setUp(browser));
//        }
//
//        public void clickWithDelay(By by) throws InterruptedException {
//            Long start_time = System.currentTimeMillis();
//            int timeout = 10000;
//            while (System.currentTimeMillis() - start_time <= timeout) {
//                if (driver.findElements(by).size() != 0) {
//                    driver.findElement(by).click();
//                    return;
//                }
//                System.out.println("time is:" + start_time);
//                Thread.sleep(1000);
//            }
//            Assert.assertNull(driver, "Error, didnt find element");
//        }
//
//        public void clickWebElement(WebElement element) {
//            element.click();
//        }
//
//        public void refreshPage(String title) throws InterruptedException {
//            driver.navigate().refresh();
//            this.waitForPageToLoad();
//        }
//
//        public void waitForPageToUpdate() throws InterruptedException {
//            Thread.sleep(_TIME_FOR_PAGE_TO_UPDATE);
//        }
//
//        public void waitForPageToLoad() throws InterruptedException {
//            (new WebDriverWait(this.driver, WAIT_FOR_LOAD_PAGE)).until(ExpectedConditions.titleIs(title));
//            Thread.sleep(_TIME_FOR_PAGE_TO_UPDATE);
//        }
//
//        /**
//         * wait until the element becomes visible within the specified time
//         *
//         **/
//        public void waitForVisibilityOfElementLocated(By by, int timeoutInSeconds) {
//            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
//        }
//
//        public void waitForVisibilityOfElement(WebElement element, int timeoutInSeconds) {
//            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
//        }
//
//        public void waitForClickabilityOfElement(WebElement element, int timeoutInSeconds) {
//            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
//        }
//
//        public void waitForClickabilityOfElementLocated(By by, int timeoutInSeconds) {
//            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(by));
//        }
//
//        /**
//         * wait for the disappearance of the element
//         **/
//        public void waitForDisappearanceOfElement(By by, int timeoutInSeconds) {
//            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
//            // wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(by)));
//            this.closeAlert();
//            wait.until(ExpectedConditions.not(presenceOfElement(driver.findElement(by))));
//        }
//	/*
//	 * public void waitForDisappearanceOfElement(WebElement element, int
//	 * timeoutInSeconds){ WebDriverWait wait = new WebDriverWait(driver,
//	 * timeoutInSeconds); element.
//	 * wait.until(ExpectedConditions.not(ExpectedConditions.
//	 * presenceOfElementLocated(by))); }
//	 */
//
//        private ExpectedCondition<Boolean> presenceOfElement(final WebElement element) {
//            return new org.openqa.selenium.support.ui.ExpectedCondition<Boolean>() {
//
//                public Boolean apply(WebDriver arg0) {
//                    return element.isDisplayed();
//                }
//            };
//
//        }
//
//        public void waitForMarkingAsSelected(final WebElement element, int timeoutInSeconds) {
//            (new WebDriverWait(driver, timeoutInSeconds)).until(new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver arg0) {
//                    return element.getAttribute("class").contains("lockedItem");
//                }
//            });
//        }
//
//        public void waitForUrlContains(String text, int timeoutInSeconds){
//            (new WebDriverWait(driver, timeoutInSeconds)).until(new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver arg0) {
//                    return driver.getCurrentUrl().contains(text);
//                }
//            });
//        }
//
//
//        public void waitForDissapearenceOfElementLocated(By by, int timeoutInSeconds) {
//            int i = 0;
//            while (i < timeoutInSeconds) {
//                if (driver.findElement(by).isDisplayed() == false)
//                    break;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                i++;
//            }
//        }
//
//        public void waitForDissapearenceOfElement(WebElement element, int timeoutInSeconds) {
//            int i = 0;
//            while (i < timeoutInSeconds) {
//                if (element.isDisplayed() == false)
//                    break;
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                i++;
//            }
//        }
//        public void waitTillChangeApplied(String messages) {
//
//            int timeout = 5000;
//            int time = 0;
//            while (this.checkPopup(messages) && time <= timeout) {
//                System.out.println("processing...");
//                time += 100;
//                this.sleep(100);
//            }
//        }
//
//        public boolean checkPopup(String messages) {
//            this.sleep(300);
//
//            try {
//                if(this._findElement(By
//                        .xpath(String.format(".//div[@id='consolePopup' and contains(.,'%s')]", messages))).isDisplayed()) {
//                    //this.closeAlert();
//                    return true;
//                }
//            } catch (ElementNotVisibleException | NoSuchElementException e) {
//                return false;
//            } catch (WebDriverException e) {
//                return false;
//            }
//            return false;
//        }
//
//
//        public void waitForElementToLoad(String elementXpath, int TIME) {
//            (new WebDriverWait(this.driver, TIME))
//                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
//        }
//
//        public void waitForElementToLoad(By locator, int timeoutInSeconds) {
//            new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
//        }
//
//        public void windowMaximize() {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            driver.manage().window().maximize();
//            // Maximized browser:
//		/*
//		 * controlRobot.keyPress(KeyEvent.VK_F11);
//		 * controlRobot.keyRelease(KeyEvent.VK_F11); controlRobot.delay(300);
//		 * controlRobot.mouseMove(100, 100);
//		 */
//
//        }
//
//        public void clickByXpath(String xpath) {
//            driver.findElement(By.xpath(xpath)).click();
//        }
//
//        public void clickByClassName(String className) {
//            driver.findElement(By.className(className)).click();
//        }
//
//        public void clickByCss(String csspath) {
//            driver.findElement(By.cssSelector(csspath)).click();
//        }
//
//        public void clickById(String idpath) {
//            driver.findElement(By.xpath(idpath)).click();
//        }
//
//        public void textByXpath(String xpath, String txt) {
//            driver.findElement(By.xpath(xpath)).sendKeys(txt);
//        }
//
//        public void clickByLocation(int x, int y) {
//            ((JavascriptExecutor) driver)
//                    .executeScript(String.format("$(document.elementFromPoint(%s, %s)).click();", x, y));
//        }
//
//        public void textById(String id, String txt) {
//            driver.findElement(By.id(id)).sendKeys(txt);
//        }
//
//        public String getTextByClass(String className) {
//            return driver.findElement(By.className(className)).getText();
//        }
//
//        public void clickByText(String text, String elementDescreption) {
//            driver.findElement(By.xpath("//*[contains(text(), " + text + ")]")).click();
//        }
//
//        public WebElement findElementByText(String text) {
//            return driver.findElement(By.xpath("//*[contains(text(), " + text + " )]"));
//        }
//
//        public String getSubImageSourceByXpath(String xpath, String elementDescreption) {
//            return driver.findElement(By.xpath(xpath)).findElement(By.tagName("img")).getAttribute("src");
//        }
//
//        public String getCSSValueByXpath(String xpath, String cssKey) {
//            return driver.findElement(By.xpath(xpath)).getCssValue(cssKey);
//        }
//
//        public String getTextByXpath(String xpath, String elementDescreption) {
//            return driver.findElement(By.xpath(xpath)).getText();
//        }
//
//        public String getTextByXpath(String xpath) {
//            return driver.findElement(By.xpath(xpath)).getText();
//        }
//
//        public WebDriver getTextByXpathInnerHTML(String xpath) {
//            String textHTML = driver.findElement(By.xpath(xpath)).getAttribute("innerHTML");
//            // return this._cleanUpHTML(textHTML);
//            return driver;
//        }
//
//        // private WebDriver _cleanUpHTML(String textHTML) {
//        // return ;
//        // }
//
//        public String getTextById(String id, String elementDescreption) {
//            return driver.findElement(By.id(id)).getText();
//        }
//
//        public java.util.List<WebElement> findListByXpath(String xpath, String elementDescreption) {
//            return driver.findElements(By.xpath(xpath));
//        }
//
//        public String _getAttribute(String xpath, String attributeString) {
//            return _findByXpath(xpath).getAttribute(attributeString);
//        }
//
//        public String findAndGetType(String xpath) {
//            return _getAttribute(xpath, "type");
//        }
//
//        public WebElement _findByXpath(String xpath) {
//            closeISQAlert();
//            return driver.findElement(By.xpath(xpath));
//        }
//
//        public WebElement _findElement(By arg) {
//            return driver.findElement(arg);
//        }
//
//        public void _waitUntilPageLoad() {
//            driver.manage().timeouts().implicitlyWait(WAIT_FOR_LOAD_PAGE, TimeUnit.SECONDS);
//        }
//
//        public java.util.List<WebElement> _findByElementsXpath(String xpath) {
//            closeISQAlert();
//            return driver.findElements(By.xpath(xpath));
//        }
//
//        public java.util.List<WebElement> _findByElementsClass(String xpath) {
//            closeISQAlert();
//            return driver.findElements(By.className(xpath));
//        }
//
//        public java.util.List<WebElement> _findByElementsCSS(String css) {
//            closeISQAlert();
//            return driver.findElements(By.cssSelector(css));
//        }
//
//        public WebElement _findByCss(String css) {
//            closeISQAlert();
//            return driver.findElement(By.cssSelector(css));
//        }
//
//        public WebElement _findByClassName(String css) {
//            closeISQAlert();
//            return driver.findElement(By.className(css));
//        }
//
//        public WebElement _findByTagName(String tagName) {
//            closeISQAlert();
//            return driver.findElement(By.tagName(tagName));
//        }
//
//        public WebElement _findByName(String css) {
//            closeISQAlert();
//            return driver.findElement(By.name(css));
//        }
//
//        public WebElement _findpartialLinkText(String css) {
//            closeISQAlert();
//            return driver.findElement(By.partialLinkText(css));
//        }
//
//        public void _switchToFrame(String css) {
//            closeISQAlert();
//            driver.switchTo().frame(this._findByXpath(css));
//        }
//
//        public void _switchToDefaultFrame() {
//            closeISQAlert();
//            driver.switchTo().defaultContent();
//        }
//
//        public String _getLoginTitle() {
//            return driver.getTitle();
//        }
//
//        public String _getBrowserURL() {
//            return driver.getCurrentUrl();
//        }
//
//        public WebElement _findById(String id) {
//            closeISQAlert();
//            return driver.findElement(By.id(id));
//        }
//
//        public void submit(String xpath) {
//            _findByXpath(xpath).submit();
//        }
//
//        public void type(String xpath, String text) {
//            _findByXpath(xpath).clear();
//            _findByXpath(xpath).sendKeys(text);
//        }
//
//        /**
//         * <p>
//         * sendKeys by css
//         * </p>
//         *
//         * @param css
//         * @param text
//         */
//        public void sendKeysByCSS(String css, String text) {
//            closeISQAlert();
//            _findByCss(css).clear();
//            _findByCss(css).sendKeys(text);
//        }
//
//        // Get the css value of the xpath
//        public String _getCssValue(String xpath, String value) {
//            return driver.findElement(By.xpath(xpath)).getAttribute("value");
//        }
//
//        public boolean visibleByXpath(String xpath) {
//            return driver.findElement(By.xpath(xpath)).isDisplayed();
//        }
//
//        public Boolean visibleById(String id) {
//            return _findById(id).isDisplayed();
//        }
//
//        // public WebDriver getClassesByXpath(String xpath) {
//        // return _getAttribute(xpath, ("class".split(" ")));
//        // }
//
//        public void goToLastPageLow(String title) throws InterruptedException {
//            driver.navigate().back();
//            waitForPageToLoad();
//        }
//
//        public boolean existsByXpath(String selector) {
//            try {
//                driver.findElement(By.xpath(selector));
//            } catch (Exception e) {
//                return false;
//            }
//            return true;
//
//        }
//
//        public boolean existsByClass(String selector) {
//            try {
//                driver.findElement(By.className(selector));
//            } catch (Exception e) {
//                return false;
//            }
//            return true;
//
//        }
//
//        public boolean existsById(String selector) {
//            try {
//                driver.findElement(By.xpath(selector));
//            } catch (Exception e) {
//                return false;
//            }
//            return true;
//        }
//
//        //
//        // public WebDriver countByXpathContains(selector, contains, tag = "*" ):
//        // count = 0
//        // while ( self.existsByXpath( "//%s[ contains ( @%s, '%s' ) ][ %d ]" % (
//        // tag, selector, contains, count + 1 ) ) ):
//        // count = count + 1
//        // return count
//
//        public boolean visible(String selector) {
//            String _status = getCSSValueByXpath(selector, "visibility");
//            if (_status == "hidden")
//                return false;
//            if (_status == "inherit")
//                return true;
//            return false;
//            // return assertEquals(" VISIBLILITY status is different then expected"
//            // + _status);
//        }
//
//        public void quit(WebDriver driver) {
//            driver.quit();
//        }
//
//        public void quit() {
//            if (driver != null) {
//                driver.close();
//                try {
//                    driver.quit();
//                } catch (org.openqa.selenium.remote.UnreachableBrowserException e) {
//
//                }
//                Reporter.log("The browser is closed");
//            }
//            driver = null;
//        }
//
//        public WebDriver getBrowser(WebDriver driver) {
//            return driver;
//        }
//
//        /**
//         * You can go to the page without knowing the domain
//         *
//         **/
//        public void getPage(String path) {
//            try {
//                // waitForPageToLoad();
//                // waitForPageToUpdate();
//                Thread.sleep(2000);
//                // _waitUntilPageLoad();
//                String domain = "";
//                URI uri;
//                uri = new URI(getDriver().getCurrentUrl());
//                domain = uri.getHost();
//                getDriver().get("https://" + domain + path);
//                log("open url: " + domain + path);
//                // getBrowser(domain+path);
//                // System.out.println(domain + path);
//                Thread.sleep(4000);
//                // waitForPageToLoad();
//                // waitForPageToUpdate();
//                // refreshPage("");
//                // _waitUntilPageLoad();
//
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public WebDriver getDriver() {
//            if (driver != null) {
//                return driver;
//            }
//            throw new IllegalStateException("WebDriver is not initialized");
//        }
//
//        public void setDriver(WebDriver driver) {
//            this.driver = driver;
//        }
//
//	/*
//	 * public static void initInstance() {
//	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	 * driver.manage().window().maximize(); System.out.println("Size: " +
//	 * driver.manage().window().getSize().height + " x " +
//	 * driver.manage().window().getSize().width);
//	 *
//	 * }
//	 */
//
//        /**
//         * go into iframe on the page
//         *
//         **/
//
//        public void switchToIframeByXpath(String xpath) {
//            driver.switchTo().frame(driver.findElement(By.xpath(xpath)));
//
//        }
//
//        public void switchToIframeByCss(String css) {
//            driver.switchTo().frame(driver.findElement(By.cssSelector(css)));
//
//        }
//
//        public void switchToIframeWithoutID(WebElement box) {
//            this.switchTodefaultContent();
//            driver.switchTo().frame(box);
//        }
//
//        /**
//         * hover your mouse over an element
//         *
//         **/
//
//        public void hoverItem(WebElement element) {
//
//            Actions actionAndReturnState = new Actions(getDriver());
//            actionAndReturnState.moveToElement(element).build().perform();
//        }
//        public void drugAndDrop(WebElement source, WebElement destination) {
//            Actions actionAndReturnState = new Actions(getDriver());
//            actionAndReturnState.dragAndDrop(source, destination).build().perform();
//        }
//
//
//
//
//        /**
//         * execute Script
//         **/
//
//        public Object executeScript(String script, WebElement element) {
//
//            return ((JavascriptExecutor) driver).executeScript(script, element);
//        }
//
//        public boolean isSelected(WebElement element) {
//            return element.getAttribute("class").contains("checked") || element.getAttribute("class").contains("selected");
//
//        }
//
//
//        /**
//         * returns domain
//         **/
//        public String getHost() {
//            URI uri;
//            String domain = "";
//            try {
//                uri = new URI(getDriver().getCurrentUrl());
//                domain = uri.getHost();
//            } catch (URISyntaxException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            return domain;
//        }
//
//        /**
//         * returns url
//         **/
//        public String getURL() {
//		/*
//		 * URI uri; String url = ""; try { uri = new
//		 * URI(getDriver().getCurrentUrl()); url = uri.getPath(); } catch
//		 * (URISyntaxException e) { // TODO Auto-generated catch block
//		 * e.printStackTrace(); }
//		 */
//            return driver.getCurrentUrl();
//            // return url;
//        }
//
//        /**
//         * close all windows and goto the last one
//         **/
//        public void closeAllWindow() {
//            int handlsSize = driver.getWindowHandles().size();
//            for (int i = 1; i < handlsSize; i++) {
//                driver.switchTo().window(driver.getWindowHandle()).close();
//                this.switchToLastWindow();
//            }
//        }
//
//        /**
//         * attachment file
//         *
//         * @return true if everything ok
//         */
//
//        public boolean checkWindowOpened() {
//            return new BaseAutoIt().getAutoItx().winWaitActive(BaseAutoIt.WINDOW_FOR_ATTACH_FILE, "",
//                    BaseAutoIt.SMALL_TIMEOUT);
//
//        }
//
//        public void moveElement(WebElement element, int toX, int toY) {
//            int browserScale = Browser.getBrowserScale(driver);
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            int[] location = getLocationOfWebElement(element, "");
//
//            controlRobot.delay(300);
//            controlRobot.mouseMove(location[0] + 5, location[1] + 5 + correctionOfBrowserMenu());
//            controlRobot.mousePress(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(1000);
//
//            controlRobot.mouseMove(toX + 5, toY + 2 + correctionOfBrowserMenu());
//            controlRobot.delay(500);
//            controlRobot.mouseMove(toX + 5, toY + correctionOfBrowserMenu() + browserScale);
//            controlRobot.delay(100);
//            controlRobot.mouseRelease(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(1000);
//
//        }
//
//        /**
//         * it dosen't works, now
//         *
//         * @param source
//         * @param target
//         */
//        public void moveElement(WebElement source, WebElement target) {
//		/*
//		 * Actions actionAndReturnState = new Actions (driver); actionAndReturnState.dragAndDrop(sourse,
//		 * target).perform();
//		 */
//
//            long timeout = System.currentTimeMillis();
//            System.out.println("Start clickAndHold");
//            System.out.println("Location:" + source.getLocation().getX() + " x " + source.getLocation().getY() + "  -  "
//                    + target.getLocation().getX() + " x " + target.getLocation().getY());
//
//            // Actions builder = new Actions(driver);
//
//		/*
//		 * System.out.println("moveTo: "+ (System.currentTimeMillis() -
//		 * timeout)); builder.clickAndHold(source);
//		 * System.out.println("click hold: "+ (System.currentTimeMillis() -
//		 * timeout)); builder.moveToElement(target);
//		 * System.out.println("moveTo: "+ (System.currentTimeMillis() -
//		 * timeout)); builder.pause(1000); System.out.println("pause: "+
//		 * (System.currentTimeMillis() - timeout));
//		 * builder.release(source).perform(); System.out.println("release: "+
//		 * (System.currentTimeMillis() - timeout));
//		 */
//
//            // MouseAction
//            // builder.dragAndDrop(source, target).perform();
//
//		/*
//		 * Action dragAndDrop = builder.clickAndHold(sourse)
//		 *
//		 * .moveToElement(target)
//		 *
//		 * .release(target)
//		 *
//		 * .build();
//		 *
//		 * dragAndDrop.perform();
//		 */
//            timeout = System.currentTimeMillis() - timeout;
//            System.out.println("Time of method: " + (System.currentTimeMillis() - timeout));
//        }
//
//        public int[] getLocationOfWebElement(By by) {
//            int[] location = new int[2];
//            closeISQAlert();
//            WebElement element = driver.findElement(by);
//            location[0] = element.getLocation().getX();
//            location[1] = element.getLocation().getY();
//            return location;
//        }
//
//        public void ctrV(String content) {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            StringSelection textF = new StringSelection(content);
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textF, null);
//
//            // perform native keystrokes for Ctrl+V and Enter keys
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_V);
//            controlRobot.keyRelease(KeyEvent.VK_V);
//            controlRobot.delay(70);
//
//        }
//
//        /**
//         * copies the contents
//         *//*
//		 * public String ctrActrC(){ if(controlRobot==null){ try {controlRobot =
//		 * new Robot();} catch (AWTException e) {} }
//		 *
//		 * // perform native keystrokes for Ctrl+A and Enter keys
//		 * controlRobot.keyPress(KeyEvent.VK_CONTROL); controlRobot.delay(70);
//		 * controlRobot.keyPress(KeyEvent.VK_A);
//		 * controlRobot.keyRelease(KeyEvent.VK_A); controlRobot.delay(70);
//		 * controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//		 * controlRobot.delay(700); // perform native keystrokes for Ctrl+C and
//		 * Enter keys controlRobot.keyPress(KeyEvent.VK_CONTROL);
//		 * controlRobot.delay(70); controlRobot.keyPress(KeyEvent.VK_C);
//		 * controlRobot.keyRelease(KeyEvent.VK_C); controlRobot.delay(70);
//		 * controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//		 * controlRobot.delay(700);
//		 *
//		 * StringSelection textF = new StringSelection("");
//		 * Toolkit.getDefaultToolkit().getSystemClipboard().getContents(textF);
//		 * textF. }
//		 */
//
//        /**
//         * press Ctrl+S using keyboard
//         *
//         */
//        public void ctrlS() {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            // perform native keystrokes for Ctrl+S and Enter keys
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_S);
//            controlRobot.keyRelease(KeyEvent.VK_S);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//            controlRobot.delay(700);
//
//        }
//
//
//        public void pressTab() {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException ignored) {}
//            }
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_TAB);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_TAB);
//            controlRobot.delay(70);
//        }
//
//        /**
//         *
//         */
//        public void pressEnter() {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_ENTER);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_ENTER);
//            controlRobot.delay(70);
//        }
//
//        public void pressDelete() {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_DELETE);
//            controlRobot.delay(700);
//            controlRobot.keyRelease(KeyEvent.VK_DELETE);
//            controlRobot.delay(700);
//        }
//
//        /**
//         * close a window
//         */
//        public boolean closeWindowRobot() {
//            Robot controlRobot = null;
//            AutoItX controlAutoItX = new BaseAutoIt().getAutoItx();
//            try {
//                controlRobot = new Robot();
//            } catch (AWTException e) {
//            }
//            // get handle of window
//            String handle = String.format(BaseAutoIt.WIND_HANDLE, controlAutoItX.winGetHandle(""));
//            // perform native keystrokes for ESCAPE and Enter keys
//            controlRobot.keyPress(KeyEvent.VK_ESCAPE);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_ESCAPE);
//            controlRobot.delay(700);
//            // wait to close window
//            if (!controlAutoItX.winWaitClose(handle, "", BaseAutoIt.SMALL_TIMEOUT)) {
//                System.out.println("winWaitClose - fail");
//                System.out.println(
//                        "file path in edit field: " + controlAutoItX.controlGetText(handle, "", BaseAutoIt.EDIT_FIELD));
//                // if window didn't close
//                controlAutoItX.winActivate(handle);
//                controlAutoItX.winClose(handle);
//            }
//            return true;
//        }
//
//        /**
//         * waiting of window
//         */
//        public boolean waitWindowRobot() {
//            Robot controlRobot = null;
//            AutoItX controlAutoItX = new BaseAutoIt().getAutoItx();
//            // wait for windows
//            if (!controlAutoItX.winWaitActive(BaseAutoIt.WINDOW_FOR_ATTACH_FILE, "", BaseAutoIt.SMALL_TIMEOUT)) {
//                return false;
//            }
//            return true;
//        }
//
//        public boolean isDialogPresent(DriverHelper driver) {
//            Alert alert = ExpectedConditions.alertIsPresent().apply(driver.getDriver());
//            return (alert != null);
//        }
//
//        /**
//         * closes the blocking message
//         */
//        public boolean closeISQAlert(DriverHelper driver) {
//            if (isDialogPresent(driver)) {
//                Reporter.log("<br> alert ISQ.Console ");
//                Alert alert = getDriver().switchTo().alert();
//                if (alert.getText().contains("ISQ.Console")) {
//                    Reporter.log("<br> alert ISQ.Console context:  " + alert.getText() + " ");
//                    driver.getDriver().switchTo().alert().accept();
//                    closeWindowsBrowserPopup();
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        public void closeDebugWindow() {
//            this.executeScript("$('h2+a:first-of-type').click()", "to close debug window");
//            this.sleep(500);
//        }
//
//        /**
//         * closes the blocking message
//         */
//        public void closeISQAlert() {
//            if ((ExpectedConditions.alertIsPresent().apply(driver)) != null) {
//                Alert alert = getDriver().switchTo().alert();
//                if (alert.getText().contains("ISQ.Console") || alert.getText().contains("GC Clean:")) {
//                    driver.switchTo().alert().accept();
//                    LoggerHelper.info("Accepting alert...");
//                    closeWindowsBrowserPopup();
//                }
//            }
//        }
//
//        public void closeAlert() {
//            this.sleep(500);
//            try {
//                (new WebDriverWait(driver, 3)).until(ExpectedConditions.alertIsPresent());
//                LoggerHelper.info("Accepting alert...");
//                driver.switchTo().alert().accept();
//            } catch (TimeoutException e) {
//                LoggerHelper.info("Alert isn't presented");
//            }
//        }
//
//        /**
//         * for log
//         *
//         * @param str
//         */
//        public void log(String str) {
//            Reporter.log("<br>" + str + " ");
//        }
//
//        public int getWindowHeight() {
//            return Integer.parseInt(this.executeScript("return window.innerHeight;", "").toString());
//            // return
//            // this.getElementHeight(driver.findElement(By.cssSelector("body")),
//            // "window (body tag)");
//        }
//
//        public int getWindowWidth() {
//            return this.getElementWidth(driver.findElement(By.cssSelector("body")), "window (body tag)");
//        }
//
//        public int getDocumentScrollHeight() {
//            return Integer.parseInt(
//                    this.executeScript("return document.documentElement.scrollHeight;", "HTML document scrollHeight")
//                            .toString());
//        }
//        // --------------------------- methodes with logger inplementations ------
//
//        // @Override
//        public void getBrowser(String url) {
//            if (url.contains("http")) {
//            } else {
//                url = "http://" + url;
//            }
//            LoggerHelper.info(String.format("Open url: %s", url));
//            driver.get(url);
//
//        }
//
//        public void getBrowserTimeoutIgnore(String url) {
//            try {
//                this.executeScript(String.format("window.location.href = 'http://%s';", url), "go to page");
//                this.getBrowser(url);
//            } catch (TimeoutException ignore) {
//                LoggerHelper.info(String.format("Out of timeout to load [%s]", url));
//            }
//        }
//
//        public void waitForLoad() {
//            LoggerHelper.debug("Wait for page loading is complete");
//            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver driver) {
//                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//                }
//            };
//            WebDriverWait wait = new WebDriverWait(driver, 50);
//            wait.until(pageLoadCondition);
//        }
//
//        public void refreshPageWithoutWait() {
//            LoggerHelper.debug("Refresh current page");
//            driver.navigate().refresh();
//            this.waitForLoad();
//        }
//
//        public WebWindow createNewWindow(String url) {
//            return new WebWindow(driver, url);
//        }
//
//        /**
//         * return to default content on the page
//         *
//         **/
//
//        /**
//         * Switch the focus of future commands for this driver to the window
//         **/
//        public void switchToLastWindow() {
//            String domain = this.getLastHandle();
//            LoggerHelper.debug(String.format("Switch to window with domain %s", domain));
//            driver.switchTo().window(domain);
//            // System.out.println(driver.getWindowHandles());
//        }
//
//        private String getLastHandle() {
//            String domain = "";
//            for (String s : driver.getWindowHandles()) {
//                domain = s;
//            }
//            return domain;
//        }
//
//        public void switchToParentWindow() {
//            String lastHandle = getLastHandle();
//            for (String handle : driver.getWindowHandles()) {
//                if (!handle.equals(lastHandle)) {
//                    LoggerHelper.debug(String.format("Switch to parent window with domain %s", handle));
//                    driver.switchTo().window(handle);
//                    break;
//                }
//            }
//        }
//
//
//        public void waitForVisibility(By element, int ms) {
//            this.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//            int i = 0;
//            boolean visible = false;
//            while (i < ms) {
//                try {
//                    visible = driver.findElement(element).isDisplayed();
//                } catch (NoSuchElementException | StaleElementReferenceException e) {
//                }
//                if (visible)
//                    break;
//                this.sleep(500);
//                i += 500;
//            }
//		/*if (!visible)
//			Assert.fail(String.format("Can't see element [%s] for %s ms", element, ms));*/
//            this.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }
//
//        public void waitForVisibility(WebElement element, int ms) {
//            this.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//            int i = 0;
//            boolean visible = false;
//            while (i < ms) {
//                try {
//                    visible = element.isDisplayed();
//                } catch (NoSuchElementException | StaleElementReferenceException | NullPointerException e) {
//
//                }
//                if (visible)
//                    break;
//                this.sleep(100);
//                i += 100;
//
//            }
//		/*if (!visible)
//			Assert.fail(String.format("Can't see element [%s] for %s ms", element, ms));*/
//            this.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }
//
//        public void waitForStalenessOfElement(WebElement element, int seconds) {
//            int ms = 0;
//            final int timeOut = seconds * 1000;
//            while (ms <= timeOut) {
//                try {
//                    if (element.isEnabled()) {
//                        return;
//                    }
//                } catch (NoSuchElementException | StaleElementReferenceException e) {
//                }
//                this.sleep(100);
//                ms += 100;
//            }
//            Assert.fail(String.format("Element [%s] is stale for %s s", element, seconds));
//        }
//
//        public void waitForStalenessOfElement(By locator, int seconds) {
//            int ms = 0;
//            final int timeOut = seconds * 1000;
//            WebElement element;
//            while (ms <= timeOut) {
//                try {
//                    element = this._findElement(locator);
//                    if (element.isEnabled()) {
//                        return;
//                    }
//                } catch (StaleElementReferenceException e) {
//                }
//                this.sleep(100);
//                ms += 100;
//            }
//            Assert.fail(String.format("Element [%s] is stale for %s s", locator, seconds));
//        }
//
//        public void waitForElementToBeClickable(By locator, int ms) {
//            int i = 0;
//            WebElement element;
//            while (i <= ms){
//                try {
//                    element = driver.findElement(locator);
//                    if (element.isDisplayed())
//                        if (element.isEnabled())
//                            return;
//                } catch (ElementNotVisibleException | StaleElementReferenceException e) {
//                }
//                this.sleep(100);
//                i += 100;
//            }
//            Assert.fail(String.format("Element [%s] isn't clickable for %s ms", locator, ms));
//        }
//
//        public void waitForElementToBeClickable(WebElement element, int ms) {
//            int i = 0;
//            while (i <= ms) {
//                try {
//                    if (element.isDisplayed())
//                        if (element.isEnabled())
//                            return;
//                } catch (ElementNotVisibleException | StaleElementReferenceException e) {
//                }
//                this.sleep(100);
//                i += 100;
//            }
//            Assert.fail(String.format("Element [%s] isn't clickable for %s ms", element, ms));
//        }
//
//        /**
//         * close the window in which we are
//         **/
//        public void closeWindow() {
//            LoggerHelper.debug("Close current window and switch to the last window");
//            driver.switchTo().window(driver.getWindowHandle()).close();
//            this.switchToLastWindow();
//        }
//
//        public void clearCookies() {
//            LoggerHelper.debug("Clearing all browser cookies");
//            driver.manage().deleteAllCookies();
//        }
//
//        public void switchTodefaultContent() {
//            LoggerHelper.info("Switch from current iframe to default content");
//            driver.switchTo().defaultContent();
//        }
//
//        public void switchToIframe(WebElement box) {
//            this.switchTodefaultContent();
//            String idIframe = box.findElement(By.tagName("iframe")).getAttribute("id");
//            LoggerHelper.info(String.format("Switch to frame with id [%s]", idIframe));
//            driver.switchTo().frame(idIframe);
//        }
//
//        public void click(WebElement element, String description) {
//            LoggerHelper.info(String.format("Click on %s", description));
//            this.checkElementExist(element, description);
//            this.waitForElementToBeClickable(element, 15000);
//            element.click();
//        }
//
//        public void clickWithoutCheck(WebElement element, String description) {
//            LoggerHelper.info(String.format("Click on %s", description));
//            this.checkElementExist(element, description);
//            element.click();
//        }
//
//        public void setText(WebElement element, String text, String description) {
//            LoggerHelper.info(String.format("Set text [%s] in %s", text, description));
//            this.checkElementExist(element, description);
//            this.waitForVisibility(element, 10000);
//            element.sendKeys(text);
//        }
//
//        public void setKey(WebElement element, Keys key, String description) {
//            LoggerHelper.info(String.format("Set key in %s", description));
//            this.checkElementExist(element, description);
//            this.waitForVisibility(element, 10000);
//            element.sendKeys(key);
//        }
//
//        public void setTextWithKey(WebElement element, String text, Keys key, String description) {
//            LoggerHelper.info(String.format("Set text [%s] in %s", text, description));
//            this.checkElementExist(element, description);
//            this.waitForVisibility(element, 10000);
//            element.sendKeys(text, key);
//        }
//
//        public void clear(WebElement element, String description) {
//            LoggerHelper.debug(String.format("Clear text input field %s", description));
//            this.checkElementExist(element, description);
//            this.waitForVisibility(element, 10000);
//            element.clear();
//        }
//
//        public String getText(WebElement element, String description) {
//            LoggerHelper.debug(String.format("Get text from %s", description));
//            this.checkElementExist(element, description);
//            String text = element.getText();
//            LoggerHelper.debug(String.format(String.format("Inner text is [%s]", text)));
//            return text;
//        }
//
//        public void select(WebElement selectContainer, String value, String description) {
//            LoggerHelper.info(String.format("Select by text [%s] from %s", value, description));
//            new Select(selectContainer).selectByVisibleText(value);
//        }
//
//        public int[] getLocationOfWebElement(WebElement element, String description) {
//            LoggerHelper.debug(String.format("Get location (x,y) of  %s", description));
//            int[] location = new int[2];
//            location[0] = element.getLocation().getX();
//            location[1] = element.getLocation().getY();
//            return location;
//        }
//
//        public String getTextByJQuery(WebElement element, String description) {
//            LoggerHelper.debug(String.format(String.format("Getting text from %s using JQuery...", description)));
//            String text = (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).val();",
//                    element);
//            LoggerHelper.debug(String.format(String.format("Inner text is [%s]", text)));
//            return text;
//
//        }
//
//        public String getTextByJS(WebElement element, String description) {
//            LoggerHelper.debug(String.format(String.format("Getting text from %s using JQuery...", description)));
//            String text = (String) ((JavascriptExecutor) driver).executeScript("return (arguments[0]).value;", element);
//            LoggerHelper.info(String.format(String.format("Inner text is [%s]", text)));
//            return text;
//
//        }
//
//        public String getChackedTextFromSelect(WebElement element, String description) {
//            LoggerHelper.debug(String.format(String.format("Getting text from %s using checked value", description)));
//            String value = this.getTextByJS(element, "select field");
//            return element.findElement(By.cssSelector(String.format("option[value='%s']", value))).getText();
//        }
//
//        public int getElementHeight(WebElement element, String description) {
//            LoggerHelper.debug(String.format(String.format("Get height (px) of %s", description)));
//            String height = element.getCssValue("height");
//            if (height.equals("auto"))
//                return 0;
//            return (int) Double.parseDouble(height.substring(0, height.length() - 2));
//        }
//
//        public int getElementWidth(WebElement element, String description) {
//            LoggerHelper.debug(String.format(String.format("Get width (px) of %s", description)));
//            String width = element.getCssValue("width");
//            if (width.equals("auto"))
//                return 0;
//            return Integer.parseInt(width.substring(0, width.length() - 2));
//        }
//
//        public boolean isVisibleForElementsWithAnimation(WebElement element, String description) {
//            LoggerHelper.debug(String.format(String.format("Check for visibility of %s", description)));
//            return element.getCssValue("opacity").equals("1");
//        }
//
//        public int getPositionOfElement(WebElement element, String description) {
//            LoggerHelper.debug(String.format("Checking that position of %s is centered...", description));
//            int popupWidth = this.getElementWidth(element, description);
//            int popupLocation = this.getLocationOfWebElement(element, description)[0];
//            int windowWidth = this.getWindowWidth();
//            LoggerHelper.debug("delta: " + (windowWidth - popupWidth - 2 * popupLocation)
//                    + "px (window width - element width - 2* lenth from left border of window to right edge of element)");
//            return windowWidth - popupWidth - 2 * popupLocation;
//        }
//
//        /**
//         * execute Script
//         **/
//        public Object executeScript(String script, String description) {
//            LoggerHelper.info(String.format(String.format("Execute js script to %s", description)));
//            return (((JavascriptExecutor) driver).executeScript(script));
//        }
//
//        public Object clickByJS(WebElement element, String description) {
//            LoggerHelper.info(String.format(String.format("Click on %s using js", description)));
//            return ((JavascriptExecutor) driver).executeScript("(arguments[0]).click();", element);
//        }
//
//        public java.util.List<WebElement> findElementsInContainerByCSS(WebElement container, String cssLocator) throws java.util.NoSuchElementException {
//            java.util.List<WebElement> list = container.findElements(By.cssSelector(cssLocator));
//            if (list.isEmpty()) {
//                throw new java.util.NoSuchElementException(
//                        String.format("The element[%s] doesn't exist or the list of elements is empty.", cssLocator));
//            }
//            boolean isReady = false;
//            for (int i = 0; i < 100; i++) {
//                list = container.findElements(By.cssSelector(cssLocator));
//                for (WebElement element : list) {
//                    try {
//                        if (!element.isEnabled()) {
//                            isReady = false;
//                            break;
//                        }
//                    } catch (StaleElementReferenceException e) {
//                        isReady = false;
//                        break;
//                    }
//                    isReady = true;
//                }
//                if (isReady)
//                    break;
//                System.out.println(i);
//                this.sleep(100);
//            }
//            return list;
//        }
//
//        /**
//         * scrolling to the item
//         **/
//
//        public void scrollUp() {
//            this.scrollTo(this._findByTagName("body"), "the top of the page");
//            this.sleep(500);
//        }
//
//        public void scrollDown() {
//            this.scrollTo(this._findById("footer"), "scrollDown");
//        }
//
//        public void scrollToElementIgnoringSteakHeader(WebElement element, int coordinateYCorretion, String description) {
//            LoggerHelper.info(String.format(String.format("Perform scroll to %s", description)));
//            int x = element.getLocation().x;
//            int y = element.getLocation().y + coordinateYCorretion;
//            ((JavascriptExecutor) driver).executeScript(String.format("window.scrollTo(%d,%d)", x, y), "");
//        }
//
//        public void scrollTo(WebElement element, String description) {
//            LoggerHelper.info(String.format(String.format("Perform scroll to %s", description)));
//            int x = element.getLocation().x;
//            int y = element.getLocation().y;
//            this.executeScript(String.format("window.scrollTo(%d,%d)", x, y), "");
//        }
//
//        public void scrollToView(WebElement element, String description) {
//            LoggerHelper.info(String.format(String.format("Perform scroll to view %s", description)));
//            this.sleep(500);
//            this.executeScript("arguments[0].scrollIntoView(true)", element);
//        }
//
//        public void scrollToElementInContainer (WebElement element){
//            this.sleep(500);
//            this.executeScript("arguments[0].scrollIntoView(true)", element);
//        }
//
//        public void clickByRobot(WebElement element, String description) {
//
//            int[] location = this.getLocationOfWebElement(element, description);
//            int coordinateX = location[0];
//            int coordinateY = location[1];
//            if (coordinateY + 50 > this.getWindowHeight()) {
//                this.scrollDown();
//                this.sleep(1000);
//                coordinateY = this.getWindowHeight() - (this.getDocumentScrollHeight() - coordinateY);
//            }
//            this.clickLocation(coordinateX, coordinateY, description);
//        }
//
//        public void moveMouse(WebElement element, String description) {
//            int[] location = this.getLocationOfWebElement(element, description);
//            int coordinateX = location[0];
//            int coordinateY = location[1];
//            if (coordinateY + 150 > this.getWindowHeight()) {
//                this.scrollDown();
//                this.sleep(1000);
//                coordinateY = this.getWindowHeight() - (this.getDocumentScrollHeight() - coordinateY);
//            }
//            this.moveMouse(coordinateX, coordinateY, description);
//        }
//
//        /**
//         * created method click mouse with using robot
//         */
//        private Robot controlRobot = null;
//        //private final int CORRECTION_OF_BROWSER_MENU = 72;
//
//        private int correctionOfBrowserMenu(){
//            if (this.getDriver() instanceof ChromeDriver)
//                return 72;
//            else return 72;
//        }
//
//        public void clickLocation(int x, int y, String description) {
//            LoggerHelper.info(String.format("Click by robot on %s with coordinates (%d, %d)", description, x + 5,
//                    y + correctionOfBrowserMenu() + 3));
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            controlRobot.delay(300);
//            controlRobot.mouseMove(x + 5, y + correctionOfBrowserMenu() + 3);
//            controlRobot.delay(300);
//            controlRobot.mousePress(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(300);
//            controlRobot.mouseRelease(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(700);
//
//        }
//
//        public void clickMultipleRobot(int x, int y, int number, String description) {
//            for (int i = 0; i < number; i++) {
//                clickLocation(x, y, String.format("%s (%d-times)", description, number));
//            }
//        }
//
//        public void clickWithRobot(int numberOfClick) {
//            LoggerHelper.info("Click by robot ");
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            for (int i = 0; i < numberOfClick; i++) {
//                controlRobot.delay(200);
//                controlRobot.mousePress(InputEvent.BUTTON1_MASK);
//                controlRobot.delay(200);
//                controlRobot.mouseRelease(InputEvent.BUTTON1_MASK);
//                controlRobot.delay(500);
//            }
//        }
//
//
//
//        public void moveMouse(int x, int y, String description) {
//            LoggerHelper.info(String.format("Move mouse to %s with coordinates (%d, %d)", description, x + 5, y + 80));
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            controlRobot.delay(300);
//            controlRobot.mouseMove(x + 5, y + 80);
//            controlRobot.delay(700);
//        }
//
//        public void moveElement(int fromX, int fromY, int toX, int toY, String description) {
//            LoggerHelper.info(String.format("Perform drag-and-drop actionAndReturnState with %s: from coordinates (%d, %d) to (%d, %d)",
//                    description, fromX + 5, fromY + correctionOfBrowserMenu() + 5, toX + 5,
//                    toY + correctionOfBrowserMenu() + 5));
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            controlRobot.delay(300);
//            controlRobot.mouseMove(fromX + 5, fromY + correctionOfBrowserMenu() + 5);
//            controlRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//            controlRobot.delay(600);
//            controlRobot.mouseMove(toX + 5, toY + correctionOfBrowserMenu() + 5);
//            controlRobot.delay(900);
//            controlRobot.mouseRelease(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(700);
//
//        }
//
//        public void scrollDownByRobot(WebElement scroll, WebElement scroller, String description) {
//            LoggerHelper.info(String.format("Perform scrolling to bottom using %s", description));
//            int[] location = this.getLocationOfWebElement(scroller, "'Scroller' element");
//            int h2 = this.getElementHeight(scroller, "scroller");
//            int h1 = this.getElementHeight(scroll, "scrollbar");
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            controlRobot.mouseMove(location[0] + 3, location[1] + 80);
//            controlRobot.delay(300);
//            controlRobot.mousePress(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(300);
//            controlRobot.mouseMove(location[0] + 3, location[1] + 80 + (h1 - h2));
//            controlRobot.delay(300);
//            controlRobot.mouseRelease(InputEvent.BUTTON1_MASK);
//            controlRobot.delay(1000);
//        }
//
//        /**
//         * put content using keyboard
//         *
//         * @param content
//         */
//        public void ctrCctrV(String content) {
//            LoggerHelper.info(String.format("Set text [%s] using ctrlC ctrlV", content));
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            StringSelection textF = new StringSelection(content);
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textF, null);
//
//            // perform native keystrokes for Ctrl+V and Enter keys
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_V);
//            controlRobot.keyRelease(KeyEvent.VK_V);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_ENTER);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_ENTER);
//            controlRobot.delay(700);
//
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
//        }
//
//        public void ctrCctrVTest(String content) {
//            this.pressTab();
//            LoggerHelper.info(String.format("Set text [%s] using ctrlC ctrlV", content));
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//
//            StringSelection textF = new StringSelection(content);
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textF, null);
//
//            // perform native keystrokes for Ctrl+V and Enter keys
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_V);
//            controlRobot.keyRelease(KeyEvent.VK_V);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//            controlRobot.delay(700);
//            controlRobot.keyPress(KeyEvent.VK_ENTER);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_ENTER);
//            controlRobot.delay(700);
//
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
//        }
//
//        /**
//         * distinguish
//         */
//        public void ctrA() {
//            LoggerHelper.info("Perform CtrlA");
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            // Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new
//            // StringSelection(""), null);
//            // controlRobot.delay(700);
//            // perform native keystrokes for Ctrl+A and Enter keys
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_A);
//            controlRobot.keyRelease(KeyEvent.VK_A);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//            controlRobot.delay(700);
//
//        }
//
//        /**
//         * get content using keyboard
//         *
//         */
//        public String getContentByCtrlC() {
//            if (controlRobot == null) {
//                try {
//                    controlRobot = new Robot();
//                } catch (AWTException e) {
//                }
//            }
//            LoggerHelper.info("Get inner text using CtrlA+CtrlC");
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
//            controlRobot.delay(700);
//            // perform native keystrokes for Ctrl+A and Enter keys
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_A);
//            controlRobot.keyRelease(KeyEvent.VK_A);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//            controlRobot.delay(700);
//            // perform native keystrokes for Ctrl+C and Enter keys
//            controlRobot.keyPress(KeyEvent.VK_CONTROL);
//            controlRobot.delay(70);
//            controlRobot.keyPress(KeyEvent.VK_C);
//            controlRobot.keyRelease(KeyEvent.VK_C);
//            controlRobot.delay(70);
//            controlRobot.keyRelease(KeyEvent.VK_CONTROL);
//            controlRobot.delay(700);
//            return this.getClipboard();
//        }
//
//        public String getClipboard() {
//            String text = "";
//            try {
//                Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
//                if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//                    text = (String) t.getTransferData(DataFlavor.stringFlavor);
//                    System.out.println(text);
//                }
//            } catch (Exception e) {
//                System.out.println("Something wrong during getting content from clipboard" + e);
//            }
//            return text;
//        }
//
//        /**
//         * close windows browser popup
//         * <p>
//         * method used for it library Robot
//         * </p>
//         **/
//        public void closeWindowsBrowserPopup() {
//            this.sleep(3000);// wait for browser window appearing
//            LoggerHelper.info("Perform pushing 'Esc' button to close popup");
//            Robot robot = null;
//            try {
//                robot = new Robot();
//            } catch (AWTException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            // waiting window popup
//            robot.delay(1000);
//
//            robot.keyPress(KeyEvent.VK_ESCAPE);
//            robot.keyRelease(KeyEvent.VK_ESCAPE);
//
//            // expects to close the window
//            robot.delay(1000);
//        }
//
//        public boolean selectCustomColor(int red, int green, int blue) {
//            LoggerHelper.info(String.format("Set color from custom palette with rgb (%d, %d, %d)", red, green, blue));
//
//            AutoItX controlAutoItX = new BaseAutoIt().getAutoItx();
//            String handle = String.format(BaseAutoIt.WIND_HANDLE, controlAutoItX.winGetHandle(""));
//            controlAutoItX.controlFocus(handle, "", BaseAutoIt.COLOR_RED);
//            controlAutoItX.ControlSetText(handle, "", BaseAutoIt.COLOR_RED, Integer.toString(red));
//            controlAutoItX.controlFocus(handle, "", BaseAutoIt.COLOR_GREEN);
//            controlAutoItX.ControlSetText(handle, "", BaseAutoIt.COLOR_GREEN, Integer.toString(green));
//            controlAutoItX.controlFocus(handle, "", BaseAutoIt.COLOR_BLUE);
//            controlAutoItX.ControlSetText(handle, "", BaseAutoIt.COLOR_BLUE, Integer.toString(blue));
//
//            controlAutoItX.controlClick(handle, "", BaseAutoIt.PALETTE_OK_BTN);
//            return true;
//        }
//
//        public boolean attachmentFile(WebElement eToGetWindow, String pathToFile, String description) {
//            LoggerHelper.info(String.format("Attach file clicking on %s", description));
//            File file = new File(System.getProperty("user.dir") + pathToFile);
//            AutoItX controlAutoItX = new BaseAutoIt().getAutoItx();
//            // this.waitForClickabilityOfElement(eToGetWindow, 10);
//            eToGetWindow.click();
//            // wait for windows
//            if (!controlAutoItX.winWaitActive(BaseAutoIt.WINDOW_FOR_ATTACH_FILE, "", BaseAutoIt.SMALL_TIMEOUT))
//                System.out.println("winWaitActive - false");
//            // get handle of window
//            String handle = String.format(BaseAutoIt.WIND_HANDLE, controlAutoItX.winGetHandle(""));
//            controlAutoItX.controlFocus(handle, "", BaseAutoIt.EDIT_FIELD);
//            controlAutoItX.ControlSetText(handle, "", BaseAutoIt.EDIT_FIELD, file.getAbsolutePath());
//            controlAutoItX.controlClick(handle, "", BaseAutoIt.OK_BTN);
//
//		/*
//		 * // wait to close window if(!controlAutoItX.winWaitClose(handle, "",
//		 * BaseAutoIt.SMALL_TIMEOUT)){
//		 * System.out.println("winWaitClose - fail");
//		 * System.out.println("file path in edit field: "+
//		 * controlAutoItX.controlGetText(handle, "", BaseAutoIt.EDIT_FIELD));
//		 *
//		 * // if window didn't close
//		 * if(file.getAbsolutePath().contains(controlAutoItX.controlGetText(
//		 * handle, "", BaseAutoIt.EDIT_FIELD).trim())){
//		 * controlAutoItX.winActivate(handle);
//		 * controlAutoItX.controlClick(handle, "", BaseAutoIt.OK_BTN);
//		 * System.out.println("AUTOIT click -  OK"); }else{
//		 * System.out.println("CLOSE Attachment");
//		 * controlAutoItX.winClose(handle); return false; }
//		 *
//		 * }
//		 */
//            return true;
//        }
//
//        public void sleep(int millis) {
//            LoggerHelper.debug(String.format("Slipping for %d ms...", millis));
//            try {
//                Thread.sleep(millis);
//            } catch (InterruptedException e) {
//                log(e.toString());
//            }
//        }
//
//        public void setCookies(String cookiesText) {
//            LoggerHelper.debug("Set the cookies");
//            Cookie name = new Cookie(cookiesText, cookiesText);
//            driver.manage().addCookie(name);
//        }
//
//        public void addToLogger(String text) {
//            System.out.println(text);
//            LoggerHelper.info(text);
//        }
//
//        public void checkElementExist(WebElement element, String description) {
//            if (element == null)
//                throw new WebDriverException(String.format("There is no such element [%s]", description));
//        }
//
//        public void dragAndDropJS(String cssLocatorDraggable, String cssLocatorDroppable){
//            String script = "(function( $ ) {" +
//                    "    $.fn.simulateDragDrop = function(options) {" +
//                    "        return this.each(function() {" +
//                    "            new $.simulateDragDrop(this, options);" +
//                    "        });" +
//                    "    };" +
//                    "    $.simulateDragDrop = function(elem, options) {" +
//                    "        this.options = options;" +
//                    "        this.simulateEvent(elem, options);" +
//                    "    };" +
//                    "    $.extend($.simulateDragDrop.prototype, {" +
//                    "        simulateEvent: function(elem, options) {" +
//                    "            /*Simulating drag start*/" +
//                    "            var type = 'dragstart';" +
//                    "            var event = this.createEvent(type);" +
//                    "            this.dispatchEvent(elem, type, event);" +
//                    "            /*Simulating drop*/" +
//                    "            type = 'drop';" +
//                    "            var dropEvent = this.createEvent(type, {});" +
//                    "            dropEvent.dataTransfer = event.dataTransfer;" +
//                    "            this.dispatchEvent($(options.dropTarget)[0], type, dropEvent);" +
//                    "            /*Simulating drag end*/" +
//                    "            type = 'dragend';" +
//                    "            var dragEndEvent = this.createEvent(type, {});" +
//                    "            dragEndEvent.dataTransfer = event.dataTransfer;" +
//                    "            this.dispatchEvent(elem, type, dragEndEvent);" +
//                    "        }," +
//                    "        createEvent: function(type) {" +
//                    "            var event = document.createEvent(\"CustomEvent\");" +
//                    "            event.initCustomEvent(type, true, true, null);" +
//                    "            event.dataTransfer = {" +
//                    "                data: {" +
//                    "                }," +
//                    "                setData: function(type, val){" +
//                    "                    this.data[type] = val;" +
//                    "                }," +
//                    "                getData: function(type){" +
//                    "                    return this.data[type];" +
//                    "                }" +
//                    "            };" +
//                    "            return event;" +
//                    "        }," +
//                    "        dispatchEvent: function(elem, type, event) {" +
//                    "            if(elem.dispatchEvent) {" +
//                    "                elem.dispatchEvent(event);" +
//                    "            }else if( elem.fireEvent ) {" +
//                    "                elem.fireEvent(\"on\"+type, event);" +
//                    "            }" +
//                    "        }" +
//                    "    });" +
//                    "})(jQuery);";
//            System.out.println(cssLocatorDraggable);
//            System.out.println(cssLocatorDroppable);
//
//            this.executeScript(script+"$('"+cssLocatorDraggable+"').simulateDragDrop({ dropTarget: '"+cssLocatorDroppable+"'});" , "to emulate drag-and-drop");
//
//        }
//    }
//
//}



