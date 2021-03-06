package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.ProgramsPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ProgramsSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        ProgramsPage page = new ProgramsPage().open();

        // section header
        assertThat("header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("header.text", page.sectionHeader.getHeaderText(), is(equalTo("Programs")));
        assertThat("subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));

        // pagination
        assertThat("pagination.results", page.paginationHeader.isResultsDisplayed());
        assertThat("pagination.menu", page.pagination.isRootDisplayed());

        // filters
        assertThat("filters.menu", page.filterPanel.menu.hasFilters("Genre/Program", "Topics", "Sources", "Date"));
        assertThat("Genre/Program shown", page.filterPanel.menu.isActiveItem("Genre/Program"));
    }

}
