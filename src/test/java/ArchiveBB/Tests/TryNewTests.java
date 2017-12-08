package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import PageObjects.EventsMain;
import PageObjects.ProgramsGenre;
import helper.Class.FilesClass;
import helper.Class.InitClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    @Test()
    @Parameters({"pathToDownloadFiles"})
    public void brokenLinks(String pathToDownloadFiles) {
//        driver.get("http://the-internet.herokuapp.com/broken_images");
//        eventsMain.CheckBrokenImage(".example>img");

//        driver.get("http://the-internet.herokuapp.com/upload");
//        eventsMain.click(driver.findElement(By.cssSelector("#file-upload")));
//        eventsMain.UploadFileByRobot();
//        eventsMain.click(driver.findElement(By.cssSelector("#file-submit")));
//        Assert.assertTrue(eventsMain.getCssListAndCheckTextIfExist(".example>h3","File Uploaded!"),
//                "Doesn't displayed success message !!!");

        Assert.assertTrue(filesClass.replaceFilesOrCreateNewFolder(pathToDownloadFiles),"Folder doesn't created");

        driver.get("http://the-internet.herokuapp.com/download");
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


        eventsMain.getCssListAndClickOnFirstElement(".example>a");
//        eventsMain.click(driver.findElement(By.cssSelector("#file-upload")));
        driver.getTitle();


    }

    @Test()
    public void buildHashMapFromMp3Files() throws IOException, NoSuchAlgorithmException {
        // old files to compare
        // \\Bbo-092\d$\רדיו כללי\radio old - need to compare
        // new files to compare
        // \\Bbo-092\d$\_new_radio\haim hdashim\Test-Lo Lagaat\Makor Old

        String absolutePath = "C:\\SPORT";
        Map<String, String> mp3Mapped;
        mp3Mapped = filesClass.returnHashMapWithChecksumAndFileName(absolutePath);
        System.out.println(mp3Mapped);

    }
}
