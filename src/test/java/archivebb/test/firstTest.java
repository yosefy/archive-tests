package archivebb.test;
import BaseClass.FunctionalTest;
import PageObjects.ArchiveMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class firstTest extends FunctionalTest {

    @Test
    public void startWebDriver() throws InterruptedException {
        String link = "http://archive.kbb1.com/";
        driver.get(link);
        ArchiveMainPage archiveMainPage = new ArchiveMainPage(driver);

        archiveMainPage.navToDailyLessonsDate();
        archiveMainPage.openDatePickerAndChooseRange(ArchiveMainPage.DATE_DROPDOWN_LIST,ArchiveMainPage.LIST_LAST_MONTH);
        Assert.assertTrue(archiveMainPage.checkIFDisplayedFilterTag(ArchiveMainPage.LABEL,"1 May 2017 - 31 May 2017"));
        Assert.assertTrue(archiveMainPage.checkIFSelectedDateDropDownList(ArchiveMainPage.DATE_DROPDOWN_LIST_SELECTED, ArchiveMainPage.LIST_LAST_MONTH));


        archiveMainPage.checkDatePicker("07-06-2017","07-06-2017");

        Thread.sleep(1000);
        // todo - check date range field
        // todo - drop down range after remove lable


//        archiveMainPage.sendListAndTarget(ArchiveMainPage.filterPageNameCSS,"4");

        archiveMainPage.searchByDate("2017-05-10");


    }
}
