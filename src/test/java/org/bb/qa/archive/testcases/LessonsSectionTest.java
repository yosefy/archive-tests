package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.LessonsPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class LessonsSectionTest extends TestTemplate {

    @Test
    public void sectionHeaderIsDisplayed() {
        LessonsPage page = new LessonsPage().open();
        assertThat("header.displayed",
                page.sectionHeader.isHeaderDisplayed(),
                is(true));
        assertThat("header.text",
                page.sectionHeader.getHeaderText(),
                is(equalTo("Daily Lessons")));
        assertThat("subheader.displayed",
                page.sectionHeader.isSubHeaderDisplayed(),
                is(true));
        assertThat("subheader.text",
                page.sectionHeader.getSubHeaderText(),
                not(isEmptyOrNullString()));
    }

    @Test
    public void paginationIsDisplayed() {
        LessonsPage page = new LessonsPage().open();
        assertThat("results",
                page.paginationHeader.isResultsDisplayed(),
                is(true));
        assertThat("menu",
                page.pagination.isRootDisplayed(),
                is(true));
    }

    @Test
    public void filtersInitialDisplay() {
        LessonsPage page = new LessonsPage().open();
        assertThat("filters",
                page.filterPanel.menu.hasFilters("Topics", "Sources", "Date"),
                is(true));
        assertThat("no active item",
                page.filterPanel.menu.noItemIsActive(),
                is(true));
    }
}
