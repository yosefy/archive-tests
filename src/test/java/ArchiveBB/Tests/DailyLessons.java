package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import helper.Class.InitClass;
import PageObjects.ArchiveDate;
import org.testng.Assert;
import org.testng.annotations.*;
import videoHelper.VideoRecorder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


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
    @Parameters({"link", "videoPath"})
    public void datePickerToday(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_TODAY);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerYesterday(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_YESTERDAY);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }



    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerLast7Days(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_7_DAYS);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerLast30Days(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_30_DAYS);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }


    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerLastMonth(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        //do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_MONTH);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }


    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerThisMonth(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        //do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_THIS_MONTH);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerCustomRange(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        //do test
        String label;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_CUSTOM_RANGE);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void datePickerLastMonthRemoveLabel(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        String today;
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsDate();
            today = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_TODAY);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // save the label before remove
        String label = today;

        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL, label));

        label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_MONTH);
        ValidationsWithRangeLable(label);

        archiveDate.removeLabel();
        ValidationsWithSingleLable(today);

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }


    @Test()
    @Parameters({"link", "videoPath"})
    public void sourcesAndVerifyInnerSources(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        String label = "Baal HaSulam > Prefaces > General preface";
        try {
            driver.get(link);
            archiveDate.navToDailyLessonsSources();
            Assert.assertTrue(archiveSources.navToSourceAndApply(label), ">>> Doesn't found Sources >>> " + label);
        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // assert all success criteria
        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(), "Not found count of expected results");
        archiveSources.checkIfGreaterThanZero();
        Assert.assertTrue(archiveSources.comp2StringArrays(label.split(">"),
                archiveSources.openFirstResultAndReturnSources("Part 4: KFS").split("\\.")), "");

        deleteVideoLog(videoPath);
    }


    @Test()
    @Parameters({"link", "videoPath"})
    public void topicsAndVerifyInnerTags(String link, String videoPath) throws Exception {
        videoRecord.startRecording(videoPath);
        String label = "A-adam hu olam katan";
        driver.get(link);
        archiveDate.navToDailyLessonsTopics();
        videoRecord.stopRecording(videoPath);
        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label), ">>> Doesn't found Sources >>> " + label);

        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(), "Not found count of expected results");
        archiveSources.checkIfGreaterThanZero();
        Assert.assertTrue(archiveTopics.comp2Strings(label,
                archiveTopics.openFirstResultAndReturnTopics("part 1")),
                "Not equal inner tags with topics");
    }


    private void ValidationsWithSingleLable(String label) {
        String formattedWithSlashLabel = archiveDate.convertToDateFromLabel(label);
        // need to click on label to open the date picker
        archiveDate.openDateRangeByClickOnFilter();
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));

        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));
    }

    private void ValidationsWithRangeLable(String label) {
        archiveDate.openDateRangeByClickOnFilter();
        List<String> labels = archiveDate.parseDateRangeLabelWithRange(label);

        String formattedWithSlashLabel = labels.get(0);
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));

        formattedWithSlashLabel = labels.get(1);
        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ", formattedWithSlashLabel, dateFromUi));
    }

    private void deleteVideoLog(String videoPath) throws IOException {
        if (!videoPath.equals("")) {
            List<File> toDelete = videoRecord.getCreatedMovieFiles();
            Files.deleteIfExists(Paths.get(String.valueOf(toDelete.get(0))));
        }
    }
}


