package ArchiveBB.Tests;
import PageObjects.ProgramsGenre;
import helper.Class.InitClass;
import org.testng.Assert;
import org.testng.annotations.*;
import videoHelper.VideoRecorder;

import java.util.List;
import java.util.Map;

import static PageObjects.ProgramsGenre.GENRE_MAIN_LEFT_PANEL;


public class Programs extends InitClass{

    private ProgramsGenre programsGenre;
    private VideoRecorder videoRecord;

    @BeforeMethod
    public void beforeMethod() {
        programsGenre = new ProgramsGenre(driver);
        videoRecord = new VideoRecorder(driver);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void programsFirstTest(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);
        Map<String,String> mainMap;
        Map<String,String> secondaryMap;
        // do test
        try {
            driver.get(link);
            programsGenre.navToPrograms();
            programsGenre.ChoosePanel("All Programs");
            mainMap = programsGenre.getAllProgramsItems();

            // get list from vertical panel and over in loop
            List<String> verticalPanelItems = programsGenre.getList(GENRE_MAIN_LEFT_PANEL);

            for (int i = 1; i < verticalPanelItems.size(); i++) {
                programsGenre.clickListAndTarget(GENRE_MAIN_LEFT_PANEL, verticalPanelItems.get(i));
                secondaryMap = programsGenre.getAllProgramsItems();
                Assert.assertTrue(programsGenre.checkProgramsInTable(mainMap, secondaryMap));
            }
        }
        // stop video recording
        finally {
            videoRecord.stopRecording(videoPath);
        }
    }
}
