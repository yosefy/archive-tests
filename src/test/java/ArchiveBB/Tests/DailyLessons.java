package ArchiveBB.Tests;

import PageObjects.ArchiveDate;
import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static PageObjects.ArchiveDate.*;


public class DailyLessons extends InitClass {

    private ArchiveDate archiveDate;
    private ArchiveSources archiveSources;
    private ArchiveTopics archiveTopics;

    @BeforeMethod
    public void beforeMethod() {
        archiveDate = new ArchiveDate(driver);
        archiveSources = new ArchiveSources(driver);
        archiveTopics = new ArchiveTopics(driver);
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerToday(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_TODAY);
        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerYesterday(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_YESTERDAY);
        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }


    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerLast7Days(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_LAST_7_DAYS);
        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerLast30Days(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_LAST_30_DAYS);
        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }


    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerLastMonth(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_LAST_MONTH);
        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }


    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerThisMonth(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_THIS_MONTH);
        // assert all success criteria
        ValidationsWithRangeLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerCustomRange(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_CUSTOM_RANGE);
        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void datePickerLastMonthRemoveLabel(String link) {
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_DATE);
        String today = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_TODAY);
        // save the label before remove
        String label = today;
        // assert all success criteria
        ValidationsWithSingleLable(label);
        Assert.assertTrue(archiveDate.getCssListAndCheckTextIfExist(LABEL, label));

        label = archiveDate.saveAndReturnDateRangeLabel(DATE_DROPDOWN_LIST, LIST_LAST_MONTH);
        ValidationsWithRangeLable(label);

        archiveDate.removeLabel();
        ValidationsWithSingleLable(today);
    }


    @Test()
    @Video()
    @Parameters({"link"})
    public void sourcesAndVerifyInnerSources(String link) {
        String label = "Baal HaSulam > Prefaces > General preface";
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_SOURCES);
        Assert.assertTrue(archiveSources.navToSourceAndApply(label),
                ">>> Doesn't found Sources >>> " + label);
        // assert all success criteria
        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(),
                "Not found count of expected results");
        archiveSources.checkIfGreaterThanZero();

        Assert.assertTrue(archiveSources.comp2StringArrays(label.split(">"),
                archiveSources.clickOnFirstAndReturnLabel(label).split(">")),
                "Not found count of expected results");
    }


    @Test()
    @Video()
    @Parameters({"link"})
    public void topicsAndVerifyInnerTags(String link) {
        String label = "The Mutual Guarantee";
        driver.get(link);
        archiveDate.navigateToPanelAndSection(DAILY_LESSONS);
        archiveDate.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_TOPICS);
        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),
                ">>> Doesn't found Sources >>> " + label);
        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(),
                "Not found count of expected results");
        archiveSources.checkIfGreaterThanZero();
        Assert.assertTrue(archiveSources.comp2StringArrays(label.split(">"),
                archiveSources.clickOnFirstAndReturnLabel(label).split(">")),
                "Not found count of expected results");
    }


    private void ValidationsWithSingleLable(String label) {
        String formattedWithSlashLabel = archiveDate.convertToDateFromLabel(label);
        // need to click on label to open the date picker
        archiveDate.openDateRangeByClickOnFilter();
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",
                        formattedWithSlashLabel, dateFromUi));

        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",
                        formattedWithSlashLabel, dateFromUi));
    }

    private void ValidationsWithRangeLable(String label) {
        archiveDate.openDateRangeByClickOnFilter();
        List<String> labels = archiveDate.parseDateRangeLabelWithRange(label);

        String formattedWithSlashLabel = labels.get(0);
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",
                        formattedWithSlashLabel, dateFromUi));

        formattedWithSlashLabel = labels.get(1);
        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValueByAttribute(DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel, dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",
                        formattedWithSlashLabel, dateFromUi));
    }

}


