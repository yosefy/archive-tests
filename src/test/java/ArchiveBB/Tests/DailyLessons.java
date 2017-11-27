package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import PageObjects.ArchiveDate;
import org.testng.Assert;
import org.testng.annotations.*;
import videoHelper.VideoRecorder;

import java.util.List;

import static PageObjects.ArchiveDate.*;
import static PageObjects.ProgramsGenre.VERTICAL_HAMBURGER_MENU;


public class DailyLessons extends InitClass {

    private ArchiveDate archiveDate;
    private ArchiveSources archiveSources;
    private ArchiveTopics archiveTopics;
    private VideoRecorder videoRecord;

    @BeforeMethod
    public void beforeMethod() {
        archiveDate = new ArchiveDate(driver);
        archiveSources = new ArchiveSources(driver);
        archiveTopics = new ArchiveTopics(driver);
        videoRecord = new VideoRecorder(driver);
    }

    @Test()
    @Video
    @Parameters({"link", "videoPath"})
    public void datePickerToday(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);

        // do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_TODAY);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));


        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }

    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerYesterday(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);

        // do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_YESTERDAY);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

//        Assert.assertTrue(false);

        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }



    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerLast7Days(String link, String videoPath) throws Exception {
//        // start video recording
//        videoRecord.startRecording(videoPath);

        // do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_7_DAYS);
//        }
//        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }

    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerLast30Days(String link, String videoPath) throws Exception {
//        // start video recording
//        videoRecord.startRecording(videoPath);

        // do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_30_DAYS);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

//        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }


    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerLastMonth(String link, String videoPath) throws Exception {
//        // start video recording
//        videoRecord.startRecording(videoPath);

        //do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_MONTH);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }


    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerThisMonth(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);

        //do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_THIS_MONTH);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }

    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerCustomRange(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);

        //do test
        String label;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_CUSTOM_RANGE);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }

    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void datePickerLastMonthRemoveLabel(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);

        // do test
        String today;
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
            today = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_TODAY);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // save the label before remove
        String label = today;

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(ArchiveDate.LABEL, label));

        label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_MONTH);
        ValidationsWithRangeLable(label);

        archiveDate.removeLabel();
        ValidationsWithSingleLable(today);

        // if we're here it means the test passed. remove video files
//        videoRecord.deleteVideoLog(videoPath);
    }


    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void sourcesAndVerifyInnerSources(String link, String videoPath) throws Exception {
        // start video recording
//        videoRecord.startRecording(videoPath);

        // do test
        String label = "Baal HaSulam > Prefaces > General preface";
//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_SOURCES);
            Assert.assertTrue(archiveSources.navToSourceAndApply(label), ">>> Doesn't found Sources >>> " + label);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(), "Not found count of expected results");
        archiveSources.checkIfGreaterThanZero();

        Assert.assertTrue(archiveSources.comp2StringArrays(label.split(">"),
                archiveSources.clickOnFirstAndReturnLabel(label).split(">")),
                "Not found count of expected results");

//        videoRecord.deleteVideoLog(videoPath);
    }


    @Test()
    @Video()
    @Parameters({"link", "videoPath"})
    public void topicsAndVerifyInnerTags(String link, String videoPath) throws Exception {
//        videoRecord.startRecording(videoPath);
        String label = "The Mutual Guarantee";

//        try {
            driver.get(link);
            archiveDate.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, DAILY_LESSONS);
            archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_TOPICS);
//        }
        // stop video recording
//        finally { videoRecord.stopRecording(videoPath); }

        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),
                ">>> Doesn't found Sources >>> " + label);


        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(),
                "Not found count of expected results");
        archiveSources.checkIfGreaterThanZero();

        Assert.assertTrue(archiveSources.comp2StringArrays(label.split(">"),
                archiveSources.clickOnFirstAndReturnLabel(label).split(">")),
                "Not found count of expected results");

//        videoRecord.deleteVideoLog(videoPath);
    }


    private void ValidationsWithSingleLable(String label) {
        String formattedWithSlashLabel = archiveDate.convertToDateFromLabel(label);
        // need to click on label to open the date picker
        archiveDate.openDateRangeByClickOnFilter();
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(ArchiveDate.DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));

        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(ArchiveDate.DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));
    }

    private void ValidationsWithRangeLable(String label) {
        archiveDate.openDateRangeByClickOnFilter();
        List<String> labels = archiveDate.parseDateRangeLabelWithRange(label);

        String formattedWithSlashLabel = labels.get(0);
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(ArchiveDate.DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));

        formattedWithSlashLabel = labels.get(1);
        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(ArchiveDate.DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));
    }

}


