package ArchiveBB.Test;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import helper.Class.InitClass;
import PageObjects.ArchiveDate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;


public class TestDatePicker extends InitClass {

    private ArchiveDate archiveDate;
    private ArchiveSources archiveSources;
    private ArchiveTopics archiveTopics;

    @BeforeMethod
    public void beforeMethod(){
        archiveDate = new ArchiveDate(driver);
        archiveSources = new ArchiveSources(driver);
        archiveTopics = new ArchiveTopics(driver);
    }

    @Test()@Parameters({"link"})
    public void datePickerToday(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_TODAY);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));

        ValidationsWithSingleLable(label);
    }

    @Test()@Parameters({"link"})
    public void datePickerYesterday(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_YESTERDAY);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithSingleLable(label);
    }

    @Test()@Parameters({"link"})
    public void datePickerLast7Days(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_7_DAYS);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithRangeLable(label);
    }



    @Test()@Parameters({"link"})
    public void datePickerLast30Days(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_30_DAYS);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithRangeLable(label);
    }


    @Test()@Parameters({"link"})
    public void datePickerLastMonth(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_MONTH);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithRangeLable(label);
    }


    @Test()@Parameters({"link"})
    public void datePickerThisMonth(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_THIS_MONTH);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithRangeLable(label);
    }

    @Test()@Parameters({"link"})
    public void datePickerCustomRange(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_CUSTOM_RANGE);
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithSingleLable(label);
    }

    @Test()@Parameters({"link"})
    public void datePickerLastMonthRemoveLabel(String link) {
        driver.get(link);
        archiveDate.navToDailyLessonsDate();
        String label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_TODAY);
        String today = label;
        Assert.assertTrue(archiveDate.check(ArchiveDate.LABEL,label));
        ValidationsWithSingleLable(label);

        label = archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_LAST_MONTH);
        ValidationsWithRangeLable(label);

        archiveDate.removeLabel();
        ValidationsWithSingleLable(today);
    }

    @Test()@Parameters({"link"})
    public void Sources(String link) {
        String label;
        link = "http://archive.kbb1.com/?dates=16-07-2017_16-07-2017";
        driver.get(link);

        archiveDate.navToDailyLessonsDate();
        archiveDate.saveAndReturnDateRangeLabel(ArchiveDate.DATE_DROPDOWN_LIST, ArchiveDate.LIST_YESTERDAY);

        archiveDate.navToDailyLessonsSources();
        label = "Baal HaSulam > Prefaces > General preface";
        Assert.assertTrue(archiveSources.navToSourceAndApply(label),">>> Doesn't found Sources >>> " + label);


        archiveDate.navToDailyLessonsTopics();
        label = "Union"; //6. What Is Support in the Torah, in the Work
        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),">>> Doesn't found Topics >>> " + label);

        Assert.assertTrue(archiveSources.checkContent(2));
        System.out.println("Test");

    }

    // todo - check results after applyed labels -  .ui.sortable.very.basic.table>tbody>tr

    private void ValidationsWithSingleLable(String label) {
        String formattedWithSlashLabel = archiveDate.convertToDateFromLabel(label);
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel,dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",formattedWithSlashLabel,dateFromUi));

        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel,dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",formattedWithSlashLabel,dateFromUi));
    }

    private void ValidationsWithRangeLable(String label) {
        List<String> labels = archiveDate.parseDateRangeLabelWithRange(label);

        String formattedWithSlashLabel = labels.get(0);
        String dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_FIRST));

        Assert.assertEquals(formattedWithSlashLabel,dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",formattedWithSlashLabel,dateFromUi));

        formattedWithSlashLabel = labels.get(1);
        dateFromUi = archiveDate.convertToDateFromUi(archiveDate.returnValue(ArchiveDate.DATE_SECOND));

        Assert.assertEquals(formattedWithSlashLabel,dateFromUi,
                String.format("The date in label - [%s] doesn't equal to date in UI - [%s] ",formattedWithSlashLabel,dateFromUi));
    }

}


