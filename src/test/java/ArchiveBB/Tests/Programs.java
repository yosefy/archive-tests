package ArchiveBB.Tests;

import PageObjects.ArchiveSources;
import PageObjects.ArchiveTopics;
import PageObjects.ProgramsGenre;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

import static PageObjects.ArchiveDate.DAILY_LESSON_PANEL;
import static PageObjects.ArchiveDate.PANEL_TOPICS;
import static PageObjects.ArchiveDate.PROGRAMS;
import static PageObjects.ProgramsGenre.*;


public class Programs extends InitClass {

    private ProgramsGenre programsGenre;
    private ArchiveTopics archiveTopics;
    private ArchiveSources archiveSources;

    @BeforeMethod
    public void beforeMethod() {
        programsGenre = new ProgramsGenre(driver);
        archiveTopics = new ArchiveTopics(driver);
        archiveSources = new ArchiveSources(driver);
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void compareAllProgramsItemsWithRestItems(String link) {
        Map<String, String> mainMap;
        Map<String, String> secondaryMap;
        driver.get(link);
        programsGenre.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, PROGRAMS);
        programsGenre.clickListAndTarget(GENRE_MAIN_LEFT_PANEL, GENRE_PROGRAM_PANEL);
        mainMap = programsGenre.getAllProgramsItems();

        // get list from vertical panel and over in loop
        List<String> verticalPanelItems = programsGenre.getWebElemListReturnStringList(GENRE_MAIN_LEFT_PANEL);
        // Compare in loop all items (All Programs) with all child categories
        for (int i = 1; i < verticalPanelItems.size(); i++) {
            programsGenre.clickListAndTarget(GENRE_MAIN_LEFT_PANEL, verticalPanelItems.get(i));
            secondaryMap = programsGenre.getAllProgramsItems();
            Assert.assertTrue(programsGenre.checkProgramsInTable(mainMap, secondaryMap));
        }
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void verifyProgramTopicsWithInnerTags(String link) {
        String label = "Anti-Semitism";
        driver.get(link);
        programsGenre.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, PROGRAMS);
        programsGenre.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_TOPICS);
        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),
                ">>> Doesn't found Sources >>> " + label);

        Assert.assertTrue(archiveSources.checkResultsMoreThanZero(),
                "Not found count of expected results");
        // All displayed results in UI under H2
        archiveSources.checkIfGreaterThanZero();
        // Check label with inner tag program
        Assert.assertTrue(archiveTopics.comp2StringArrays(label.split(">"),
                archiveSources.clickOnFirstAndReturnLabel(label).split(">")),
                "Not found count of expected results");
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void verifyProgramTopicsPaginationEpisodeSource(String link) {
        String label = "Anti-Semitism";
        driver.get(link);
        programsGenre.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, PROGRAMS);
        programsGenre.clickListAndTarget(DAILY_LESSON_PANEL, PANEL_TOPICS);
        Assert.assertTrue(archiveTopics.navToTopicsAndApply(label),
                ">>> Doesn't found Sources >>> " + label);

        Assert.assertTrue(programsGenre.paginationUntilEnabled(), "Found empty Episode");
    }
}
