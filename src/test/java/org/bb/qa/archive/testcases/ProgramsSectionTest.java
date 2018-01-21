package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.ProgramsPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ProgramsSectionTest extends TestTemplate {

    @Test
    public void sectionHeaderIsDisplayed() {
        ProgramsPage page = new ProgramsPage().open();
        assertThat("header.displayed",
                page.sectionHeader.isHeaderDisplayed(),
                is(true));
        assertThat("header.text",
                page.sectionHeader.getHeaderText(),
                is(equalTo("Programs")));
        assertThat("subheader.displayed",
                page.sectionHeader.isSubHeaderDisplayed(),
                is(true));
        assertThat("subheader.text",
                page.sectionHeader.getSubHeaderText(),
                not(isEmptyOrNullString()));
    }

    @Test
    public void paginationIsDisplayed() {
        ProgramsPage page = new ProgramsPage().open();
        assertThat("results",
                page.paginationHeader.isResultsDisplayed(),
                is(true));
        assertThat("menu",
                page.pagination.isRootDisplayed(),
                is(true));
    }

    @Test
    public void filtersInitialDisplay() {
        ProgramsPage page = new ProgramsPage().open();
        assertThat("filters",
                page.filterPanel.menu.hasFilters("Genre/Program", "Topics", "Sources", "Date"),
                is(true));
        assertThat("Genre/Program shown",
                page.filterPanel.menu.isActiveItem("Topics"),
                is(true));
    }
}
