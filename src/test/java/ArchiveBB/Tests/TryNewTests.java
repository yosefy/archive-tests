package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import PageObjects.EventsMain;
import PageObjects.ProgramsGenre;
import com.sun.org.glassfish.gmbal.Description;
import helper.Class.FilesClass;
import helper.Class.InitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


public class TryNewTests extends InitClass {

    private ProgramsGenre programsGenre;
    private EventsMain eventsMain;
    private ArchiveTopics archiveTopics;
    private ArchiveSources archiveSources;
    private FilesClass filesClass;

    @BeforeMethod
    public void beforeMethod() {
        programsGenre = new ProgramsGenre(driver);
        archiveTopics = new ArchiveTopics(driver);
        archiveSources = new ArchiveSources(driver);
        eventsMain = new EventsMain(driver);
        filesClass = new FilesClass();
    }

    // https://github.com/tourdedave/the-internet
    // http://elementalselenium.com/tips

//    @Test()
//    @Parameters({"pathToDownloadFiles"})
//    public void brokenLinks(String pathToDownloadFiles) {
//        driver.get("http://the-internet.herokuapp.com/broken_images");
//        eventsMain.CheckBrokenImage(".example>img");

//        driver.get("http://the-internet.herokuapp.com/upload");
//        eventsMain.click(driver.findElement(By.cssSelector("#file-upload")));
//        eventsMain.UploadFileByRobot();
//        eventsMain.click(driver.findElement(By.cssSelector("#file-submit")));
//        Assert.assertTrue(eventsMain.getCssListAndCheckTextIfExist(".example>h3","File Uploaded!"),
//                "Doesn't displayed success message !!!");

//        Assert.assertTrue(filesClass.replaceFilesOrCreateNewFolder(pathToDownloadFiles),"Folder doesn't created");
//
//        driver.get("http://the-internet.herokuapp.com/download");
//        List<WebElement> allDownloadebleLinks = driver.findElements(By.cssSelector(".example>a"));
//
//        WebElement downloadButton = allDownloadebleLinks.get(0);
//
//        System.out.println(downloadButton.getAttribute("href"));
//        String sourceLocation = downloadButton.getAttribute("href");
//        String wget_command = "cmd /c C:\\Wget\\wget.exe -P C:\\SPORT --no-check-certificate " + sourceLocation;
//
//        try {
//            Process exec = Runtime.getRuntime().exec(wget_command);
//            int exitVal = exec.waitFor();
//            System.out.println("Exit value: " + exitVal);
//        } catch (InterruptedException | IOException ex) {
//            System.out.println(ex.toString());
//        }
//        eventsMain.getCssListAndClickOnFirstElement(".example>a");
////        eventsMain.click(driver.findElement(By.cssSelector("#file-upload")));
//        driver.getTitle();
//    }

//    @Test()
//    public void buildHashMapFromMp3Files() throws IOException, NoSuchAlgorithmException {
//        // old files to compare
//        // \\Bbo-092\d$\רדיו כללי\radio old - need to compare
//        // new files to compare
//        // \\Bbo-092\d$\_new_radio\haim hdashim\Test-Lo Lagaat\Makor Old
//
//        String absolutePath = "C:\\SPORT";
//        Map<String, String> mp3Mapped;
//        mp3Mapped = filesClass.returnHashMapWithChecksumAndFileName(absolutePath);
//        System.out.println(mp3Mapped);
//    }

    @Test
    @Description("Some detailed test description")
    public void notificationMessage (){
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        Assert.assertTrue(programsGenre.waitForMessageDisplayed(5,"#flash","Action successful\n" + "×"));
    }

    @Test
    public void DragAndDrop () throws Exception {
//        driver.get("http://jqueryui.com/droppable/");
//        Actions act = new Actions(driver);
//        WebElement iFrame = driver.findElement(By.tagName("iframe"));
//        System.out.println(iFrame.getSize());
//        driver.switchTo().frame(iFrame);
//        WebElement From = driver.findElement(By.id("draggable"));
//        System.out.println(From.getLocation());
//
//        WebElement To = driver.findElement(By.id("droppable"));
//        System.out.println(To.getLocation());
//        act.dragAndDrop(From, To).build().perform();

        driver.get("https://skidding.github.io/dragdealer/#demos");
        WebElement from = driver.findElement(By.cssSelector(".fa.fa-bars"));
        WebElement to = driver.findElement(By.cssSelector(".fa.fa-bars"));

//        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
//        WebElement from = driver.findElement(By.cssSelector(".column:nth-child(1)"));
//        WebElement to = driver.findElement(By.cssSelector(".column:nth-child(2)"));



        eventsMain.dragAndDropElementByActionOnVertical(from,40);

//        String js_filepath = "C:\\Users\\b\\Desktop\\Counter\\drag_and_drop.js";
//        String js_filepath = "C:\\Users\\b\\Desktop\\Counter\\drag_test.js";
//
//        String java_script="";
//        String text;
//
//        BufferedReader input = new BufferedReader(new FileReader(js_filepath));
//        StringBuilder buffer = new StringBuilder();
//
//        while ((text = input.readLine()) != null)
//            buffer.append(text).append(" ");
//        java_script = buffer.toString();
//
//        input.close();
//
//        String source = "__htmlview0--Button1";
//        String target = "__htmlview0--homePage-intHeader";
//
//        java_script = java_script + "$('#"+source+"').simulate( '#" +target+ "');" ;
//
////        driver.executeScript(dragAndDrop, draggable, droppable);
//        ((JavascriptExecutor) driver).executeScript(java_script, from,to);
////        ((JavascriptExecutor) driver).executeScript(java_script);


        driver.getTitle();
//
////        Actions act = new Actions(driver);
//        driver.get("http://the-internet.herokuapp.com/drag_and_drop");
//        WebElement from = driver.findElement(By.cssSelector(".column:nth-child(1)"));
//        System.out.println(from.getLocation());
//        programsGenre.highlightElement(from);
//        WebElement to = driver.findElement(By.cssSelector(".column:nth-child(2)"));
//        System.out.println(to.getLocation());
//        programsGenre.highlightElement(to);
////        act.dragAndDrop(from, to).build().perform();
//
//        org.openqa.selenium.Point coordinates1 = from.getLocation();
//        Point coordinates2 = to.getLocation();
//        Robot robot = new Robot();
//        robot.mouseMove(coordinates1.getX()+50, coordinates1.getY()+150);
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        robot.mouseMove(coordinates2.getX()+50, coordinates2.getY()+150);
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//        Thread.sleep(2000);
    }


    @Test
    public void HorizontalSlider (){
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");
        programsGenre.sliderLeftByArrow(5,".sliderContainer>input");
        programsGenre.sliderRightByArrow(7,".sliderContainer>input");
        driver.getTitle();
    }

    @Test
    public void Hover (){
        driver.get("http://the-internet.herokuapp.com/hovers");
        Actions action = new Actions(driver);
        List<WebElement> allItems = driver.findElements(By.cssSelector(".figure>img"));
        for(WebElement element : allItems){
            action.moveToElement(element).build().perform();
        }
    }

    @Test
    public void infinite_scroll () {
        driver.get("http://the-internet.herokuapp.com/infinite_scroll");
        while(true){
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }
    }

    @Test
    public void CoordinatesInside () throws InterruptedException, AWTException {
        driver.get("http://the-internet.herokuapp.com/context_menu");
        programsGenre.clickByXCoordinatesOfElement("#hot-spot");
    }

    @Test()
    public void names() throws IOException, NoSuchAlgorithmException {

        String test = "C:\\SPORT";

        // small folder for test
        String absolutePathTONewFiles = "\\\\Bbo-092\\d$\\רדיו כללי\\radio old - need to compare";
        // big folder for compare
        String absolutePathToExistingFiles = "\\\\Bbo-092\\d$\\_new_radio\\haim hdashim\\Test-Lo Lagaat\\Makor Old";

        Map<String, String> smallFolder, bigFolder;

        smallFolder = filesClass.returnHashMapWithChecksumAndFileName(absolutePathTONewFiles);
        System.out.println("Size of small folder: " + smallFolder.size());

        bigFolder = filesClass.returnHashMapWithChecksumAndFileName(absolutePathToExistingFiles);
        System.out.println("Size of big folder: " + bigFolder.size());

        filesClass.get2MapsAndCompare(smallFolder, bigFolder);
    }
}

















