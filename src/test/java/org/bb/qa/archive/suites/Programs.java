//package org.bb.qa.archive.suites;
//
//import com.automation.remarks.video.annotations.Video;
//import org.bb.qa.archive.helpers.Utils;
//import org.bb.qa.archive.pageobjects.pages.ArchiveSources;
//import org.bb.qa.archive.pageobjects.pages.ArchiveTopics;
//import org.bb.qa.archive.pageobjects.pages.ProgramsGenre;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.bb.qa.archive.pageobjects.pages.ArchiveDate.*;
//import static org.bb.qa.archive.pageobjects.pages.ProgramsGenre.*;
//
//
//public class Programs extends BaseSuite {
//
//    private ProgramsGenre programsGenre;
//    private ArchiveTopics archiveTopics;
//    private ArchiveSources archiveSources;
//
//    @BeforeMethod
//    public void beforeMethod() {
//        programsGenre = new ProgramsGenre();
//        archiveTopics = new ArchiveTopics();
//        archiveSources = new ArchiveSources();
//    }
//
//    @Test()
//    @Video()
//    @Parameters({"link"})
//    public void compareAllProgramsItemsWithRestItems(String link) {
//        Map<String, String> mainMap;
//        Map<String, String> secondaryMap;
//        driver.get(link);
//        programsGenre.navigateToPanelAndSection(PROGRAMS);
//        programsGenre.clickListAndTarget(GENRE_MAIN_LEFT_PANEL, GENRE_PROGRAM_PANEL);
//        mainMap = programsGenre.getAllProgramsItems(ALL_PROGRAMS_ITEMS);
//
//        // get list from vertical panel and over in loop
//        List<String> verticalPanelItems = programsGenre.getWebElemListReturnStringList(GENRE_MAIN_LEFT_PANEL);
//        // Compare in loop all items (All Programs) with all child categories
//        for (int i = 1; i < verticalPanelItems.size(); i++) {
//            programsGenre.clickListAndTarget(GENRE_MAIN_LEFT_PANEL, verticalPanelItems.get(i));
//            secondaryMap = programsGenre.getAllProgramsItems(ALL_PROGRAMS_ITEMS);
//            Assert.assertTrue(programsGenre.checkProgramsInTable(mainMap, secondaryMap));
//        }
//    }
//
//    @Test()
//    @Video()
//    @Parameters({"link"})
//    public void verifyProgramTopicsWithInnerTags(String link) {
//        String label = "Anti-Semitism";
//        driver.get(link);
//        programsGenre.navigateToPanelAndSection(PROGRAMS);
//        programsGenre.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_TOPICS);
//        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),
//                ">>> Doesn't found Sources >>> " + label);
//
//        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(),
//                "Not found count of expected results");
//        // All displayed results in UI under H2
//        archiveSources.checkIfGreaterThanZero();
//        // Check label with inner tag program
//        Assert.assertTrue(Utils.comp2StringArrays(label.split(">"),
//                archiveSources.clickOnFirstAndReturnLabel(label).split(">")),
//                "Not found count of expected results");
//    }
//
//    @Test()
//    @Video()
//    @Parameters({"link"})
//    public void verifyProgramTopicsPaginationEpisodeSource(String link) {
//        String label = "Anti-Semitism";
//        driver.get(link);
//        programsGenre.navigateToPanelAndSection(PROGRAMS);
//        programsGenre.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_TOPICS);
//        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),
//                ">>> Doesn't found Sources >>> " + label);
//
//        Assert.assertTrue(programsGenre.paginationUntilEnabled(), "Found empty Episode");
//    }
//}
