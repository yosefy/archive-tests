//package helpers;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Point;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.awt.*;
//import java.awt.datatransfer.StringSelection;
//import java.awt.event.InputEvent;
//import java.awt.event.KeyEvent;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static pages.ProgramsGenre.*;
//
//public class BasePageObject {
//
//    private static final Logger logger = LoggerFactory.getLogger(BasePageObject.class);
//    protected WebDriver driver;
//
//    public BasePageObject(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    public boolean clickListAndTarget(String list, String target) {
//        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
//        for (WebElement options : listOptions) {
//            if (options.getText().trim().equals(target.trim())) {
//                highlightElement(options);
//                click(options);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void getCssListAndClickOnFirstElement(String list) {
//        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
//        if (listOptions.get(0).isDisplayed()) {
//            navigate(list);
//            click(listOptions.get(0));
//        }
//    }
//
//    public void waitForClickabilityOfElementLocated(WebElement element, int timeoutInSeconds) {
//        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//
//    public void getCssListAndClickOnElementByText(String list, String text) {
//        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
//        for(WebElement element : listOptions){
//            if (element.getText().equals(text)){
//                this.waitForClickabilityOfElementLocated(element,50000);
//                navigate(list);
//                click(listOptions.get(0));
//                break;
//            }
//        }
//    }
//
//    public boolean isWebElemAttributeActiveItem(WebElement element) {
//        this.highlightElement(element);
//        return element.getAttribute("class").equals("active item");
//    }
//
//    public void click(WebElement elementToClick) {
//        Actions action = new Actions(driver);
//        action.moveToElement(elementToClick).perform();
////        this.isElementLoaded(elementToClick);
//        this.highlightElement(elementToClick);
//        elementToClick.click();
//    }
//
//    // Need to navigate because otherwise selenium can not click on element
//    public void navigate(String cssToElement) {
//        WebElement element = driver.findElement(By.cssSelector(cssToElement));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-150)", "", element);
//    }
//
//    public boolean getCssListAndCheckTextIfExist(String list, String target) {
//        List<WebElement> listOptions = driver.findElements(By.cssSelector(list));
//        for (WebElement options : listOptions) {
//            if (options.getText().trim().equals(target.trim())) {
//                this.highlightElement(options);
//                print(options.getText(), "Displayed value in: ");
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void isElementLoaded(WebElement elementToBeLoaded) {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, 15);
//            wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
//        }catch (Exception ignore){}
//    }
//
//    public void highlightElement(WebElement element) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='1px solid red'", element);
//        this.wait(500);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='none'", element);
//    }
//
//    public void wait (int milliseconds){
//        try {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String returnValueByAttribute(String pathToField) {
//        WebElement field = driver.findElement(By.cssSelector(pathToField));
//        this.highlightElement(field);
//        return field.getAttribute("value");
//    }
//
//    protected void print(String printValue, String desc) {
//        System.out.println(printValue + " - " + desc);
//    }
//
//    public List<String> getWebElemListReturnStringList(String cssToList) {
//        List<WebElement> myList = driver.findElements(By.cssSelector(cssToList));
//        this.isElementLoaded(myList.get(1));
//        List<String> listStr = new ArrayList<>();
//        for (WebElement list : myList)
//            listStr.add(list.getText());
//        return listStr;
//    }
//
//    public List<WebElement> getCssPathReturnWebElementList(String cssToList) {
//        return driver.findElements(By.cssSelector(cssToList));
//    }
//
//    // get two chars[] split by > and check from the end to start
//    /**
//    *   Two strings should have the same length
//     **/
//    public boolean comp2StringArrays(String[] first, String[] second) {
//        if (first.length == second.length) {
//            for (int i = first.length - 1; i > 0; i--) {
//                System.out.println(first[i] = first[i].trim());
//                System.out.println(second[i] = second[i].trim());
//                if (!first[i].equals(second[i]))
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    // getCssListAndCheckTextIfExist if all items in secondMap existing in firstMap
//    public boolean checkProgramsInTable(Map<String, String> firstMap, Map<String, String> secondMap) {
//        for (Map.Entry<String, String> entry : secondMap.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//            if (!firstMap.containsKey(entry.getKey()))
//                return false;
//        }
//        return true;
//    }
//
//    public void navigateToPanelAndSection(String MAIN_SECTION) {
//        // Check browser size and decide if displayed hamburger or not
//        if (driver.manage().window().getSize().getWidth() <1505 )
//            click(driver.findElement(By.cssSelector(SIDE_BAR_ICON)));
//
//        clickListAndTarget(VERTICAL_HAMBURGER_MENU, MAIN_SECTION);
//    }
//
//    public void chooseSectionAndOpenItemByText(String section, String cssPathToOpenFirstElement, String text){
//        this.navigateToPanelAndSection(section);
//        this.getCssListAndClickOnElementByText(cssPathToOpenFirstElement, text);
//    }
//
//    public boolean paginationUntilEnabled() {
//        List<String> items;
//        while (this.panelStale()) {
//            items = this.getWebElemListReturnStringList(PROGRAMS_RESULT_EPISODE);
//            for (String item : items) {
//                if (item.equals(" ")) {
//                    System.out.println("Empty section is found >>>>>> ");
//                    return false;
//                }
//            }
//
//            if (!this.panelDisabledItem())
//                break;
//        }
//        return true;
//    }
//
//    private boolean panelDisabledItem() {
//        try {
//            List<WebElement> allPanel = driver.findElements(By.cssSelector(PAGINATION_PANEL + ">div"));
//            for (WebElement exit : allPanel) {
//                navigate(PAGINATION_PANEL);
//                if (exit.getAttribute("class").equals("disabled item")) {
//                    highlightElement(exit);
//                    return false;
//                }
//            }
//            return true;
//        } catch (Exception ignored) {
//        }
//        return true;
//    }
//
//    private boolean panelStale() {
//        List<WebElement> singleLeftIcon = driver.findElements(By.cssSelector(PAGINATION_PANEL + ">a>i"));
//        for (WebElement option : singleLeftIcon) {
//            if (option.getAttribute("class").equals("angle right icon")) {
//                navigate(PAGINATION_PANEL);
//                highlightElement(option);
//                click(option);
//                break;
//            }
//        }
//        return true;
//    }
//
//    public void CheckBrokenImage(String pathToCss){
//        List<WebElement> list = driver.findElements(By.cssSelector(pathToCss));
//
//        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
//        String script = "return arguments[0].complete" +
//                "&& typeof arguments[0].naturalWidth != 'undefined'" +
//                "&& arguments[0].naturalWidth > 0";
//
//        for(WebElement element : list){
//            try {
//                if (jsExec.executeScript(script, element).equals(false))
//                    System.out.println(String.format("Image [%s] is broken : ",element.getAttribute("src")));
//                else
//                    System.out.println(String.format("Image [%s] doesn't broken : ",element.getAttribute("src")));
//            }catch (Exception e){
//                System.out.println(e);
//            }
//        }
//    }
//
//    public void UploadFileByRobot() throws AWTException {
//        String pathTo = "C:\\Users\\b\\Desktop\\Counter\\Capture.PNG";
//        StringSelection ss = new StringSelection(pathTo);
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
//
//        //imitate mouse events like ENTER, CTRL+C, CTRL+V
//        Robot robot = new Robot();
//
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//    }
//
//    public boolean waitForMessageDisplayed(Integer minutesForWait, String pathToCSS, String message) {
//        LocalTime now = LocalTime.now();
//        int currentMinute = now.getMinute();
//        int nextMinute = 0;
//        System.out.println("currentMinute is: " + currentMinute);
//
//        WebElement savingLbl;
//        boolean flag;
//        for (int second = 0; ; second++) {
//            if (second >= minutesForWait) {
//                // click on button
//                driver.findElement(By.cssSelector(".example>p>a")).click();
//                // check if displayed block message
//                try {
//                    flag = driver.findElements(By.cssSelector(pathToCSS)).size() == 1;
//                    if (flag) {
//                        savingLbl = driver.findElement(By.cssSelector(pathToCSS));
//                        highlightElement(savingLbl);
//                        System.out.println(savingLbl.getText());
//                        if (savingLbl.getText().equals(message))
//                            return true;
//                    }
//                } catch (Exception ignored) {
//                }
//                nextMinute = now.getMinute();
//                System.out.println("Waiting : " + nextMinute +" minute");
//                System.out.println("Waiting for changes to be saved...");
//            }
//
//            if (nextMinute - currentMinute >= minutesForWait) {
//                System.out.println(String.format("The message - [%s] didn't displayed during [%s] !!!",
//                        message,minutesForWait.toString()));
//                return false;
//            }
//        }
//    }
//
//    public void dragAndDrop(WebElement from, WebElement to) {
//        Actions builder = new Actions(driver);
//        builder.keyDown(Keys.CONTROL)
//                .click(from)
//                .dragAndDrop(from, to)
//                .keyUp(Keys.CONTROL);
//        Action selected = builder.build();
//        selected.perform();
//
//
////        Actions builder = new Actions(Actions builder = new Actions(fDriver);
////    builder.keyDown(Keys.CONTROL)
////        .click(element)
////        .dragAndDrop(element, elementDropped)
////        .keyUp(Keys.CONTROL);
////
////        Action selected = builder.build();
////
////        selected.perform(););
////        builder.clickAndHold(from).build().perform();
////        builder.moveToElement(to).build().perform();
////        builder.release(to).build().perform();
//
////        Actions builder = new Actions(driver);
////        Action dragAndDrop = builder.clickAndHold(cssPathToWebElem)
////                .moveToElement(cssPathFromWebElem)
////                .release(cssPathToWebElem)
////                .build();
////        dragAndDrop.perform();
//    }
//
//    public void sliderLeftByArrow(int numberOfTimes, String pathToCssWebElement) {
//        Actions moveSlider = new Actions(driver);
//        WebElement element = driver.findElement(By.cssSelector(pathToCssWebElement));
//        if (numberOfTimes>0) {
//            moveSlider.click(element).build().perform();
//            for (int i = 0; i < numberOfTimes; i++) {
//                moveSlider.sendKeys(Keys.ARROW_LEFT).build().perform();
//                wait(200);
//            }
//        }
//    }
//
//    public void sliderRightByArrow(int numberOfTimes, String pathToCssWebElement) {
//        Actions moveSlider = new Actions(driver);
//        WebElement element = driver.findElement(By.cssSelector(pathToCssWebElement));
//        if (numberOfTimes>0) {
//            moveSlider.click(element).build().perform();
//            for (int i = 0; i < numberOfTimes; i++) {
//                moveSlider.sendKeys(Keys.ARROW_RIGHT).build().perform();
//                wait(200);
//            }
//        }
//    }
//
//    public void getCoordinatesOfElement(String s) {
//        WebElement element = driver.findElement(By.cssSelector(s));
//        highlightElement(element);
//
//        Point point = element.getLocation();
//        int xcord = point.getX();
//        System.out.println("Element's Position by X: " + xcord);
//        int ycord = point.getY();
//        System.out.println("Element's Position by Y: " + ycord);
//
//        Actions builder = new Actions(driver);
//        builder.dragAndDropBy(element, xcord, ycord + 50).click().build().perform();
//    }
//
//    public void clickByXCoordinatesOfElement(String s) throws AWTException, InterruptedException {
//        WebElement element = driver.findElement(By.cssSelector(s));
//        Robot robot = new Robot();
//
//        Point point = element.getLocation();
//        int xcord = point.getX();
//        System.out.println("Element's Position by X: " + xcord);
//        int ycord = point.getY();
//        System.out.println("Element's Position by Y: " + ycord);
//
////        Actions builder = new Actions(driver);
////        builder.moveToElement(element).moveByOffset(xcord, ycord+50).click().perform();
////        driver.getTitle();
////        builder.moveToElement(element).moveToElement(element,xcord, ycord+50).click().perform();
//////        builder.clickAndHold(ycord + 50).click().build().perform();
////        driver.getTitle();
////        builder.moveToElement(element).click()
//
//        driver.getTitle();
//        robot.mouseMove(xcord, ycord);
//        robot.mouseMove(xcord+50, ycord);
//        robot.mouseMove(xcord, ycord+50);
//
//
//        driver.getTitle();
//
//    }
//
//    public void scrollToElementIgnoringSteakHeader(WebElement element, int coordinateYCorretion) {
//        int x = element.getLocation().x;
//        int y = element.getLocation().y + coordinateYCorretion;
//        ((JavascriptExecutor) driver).executeScript(String.format("window.scrollTo(%d,%d)", x, y), "");
//    }
//
//    public void scrollTo(WebElement element, String description) {
//        int x = element.getLocation().x;
//        int y = element.getLocation().y;
//        ((JavascriptExecutor) driver).executeScript(String.format("window.scrollTo(%d,%d)", x, y), "");
//    }
//
//    public void dragAndDropElementByRobotOnHorizontal(WebElement dragFrom, WebElement dragTo, int xOffset) throws Exception {
//        //Setup robot
//        Robot robot = new Robot();
//        robot.setAutoDelay(50);
//
//        //Fullscreen page so selenium coordinates are same as robot coordinates
//        robot.keyPress(KeyEvent.VK_F11);
//        Thread.sleep(2000);
//
//        //Get size of elements
//        Dimension fromSize = dragFrom.getSize();
//        Dimension toSize = dragTo.getSize();
//
//        //Get centre distance
//        int xCentreFrom = fromSize.width / 2;
//        int yCentreFrom = fromSize.height / 2;
//        int xCentreTo = toSize.width / 2;
//        int yCentreTo = toSize.height / 2;
//
//        //Get x and y of WebElement to drag to
//        Point toLocation = dragTo.getLocation();
//        Point fromLocation = dragFrom.getLocation();
//
//        //Make Mouse coordinate centre of element and account for offset
//        toLocation.x += xOffset + xCentreTo;
//        toLocation.y += yCentreTo;
//        fromLocation.x += xCentreFrom;
//        fromLocation.y += yCentreFrom;
//
//        //Move mouse to drag from location
//        robot.mouseMove(fromLocation.x, fromLocation.y);
//
//        //Click and drag
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//
//        //Drag events require more than one movement to register
//        //Just appearing at destination doesn't work so move halfway first
//        robot.mouseMove(((toLocation.x - fromLocation.x) / 2) + fromLocation.x, ((toLocation.y - fromLocation.y) / 2) + fromLocation.y);
//
//        //Move to final position
//        robot.mouseMove(toLocation.x, toLocation.y);
//
//        //Drop
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        robot.keyPress(KeyEvent.VK_F11);
//        Thread.sleep(2000);
//    }
//
//    public void dragAndDropElementByActionOnVertical(WebElement dragFrom, int yOffset) {
////        navigate(".fa.fa-bars");
//        Actions action = new Actions(driver);
//        action.clickAndHold(dragFrom).moveByOffset(0, yOffset).build().perform();
//        driver.getTitle();
//    }
//
//    public int getCurrentMachineSecond(){
//        LocalTime now = LocalTime.now();
//        int currentSecond = now.getSecond();
//        System.out.println("currentSecond is: " + currentSecond);
//        return currentSecond;
//    }
//}
//
//
//
//
//
//
//
//
//
