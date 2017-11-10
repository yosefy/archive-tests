package ArchiveBB.Tests;
import PageObjects.ProgramsGenre;
import helper.Class.InitClass;
import org.testng.annotations.*;
import videoHelper.VideoRecorder;


public class Programs extends InitClass {

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

        // do test
        String label;
        try {
            driver.get(link);
            programsGenre.navToPrograms();
            programsGenre.ChoosePanel("TalkShow");
            driver.getCurrentUrl();
        }
        // stop video recording
        finally {
            videoRecord.stopRecording(videoPath);
        }


    }
}
