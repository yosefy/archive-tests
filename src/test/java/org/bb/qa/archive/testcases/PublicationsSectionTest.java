package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.PublicationsPage;
import org.bb.qa.archive.pageobjects.pages.SourcesPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PublicationsSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        PublicationsPage page = new PublicationsPage().open();

        // section header
        assertThat("header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("header.text", page.sectionHeader.getHeaderText(), is(equalTo("Publications")));
        assertThat("subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));

        // pagination
        assertThat("pagination.results", page.paginationHeader.isResultsDisplayed());
        assertThat("pagination.menu", page.pagination.isRootDisplayed());

        // filters
        assertThat("filters.menu", page.filterPanel.menu.hasFilters("Publishers", "Date"));
        assertThat("no-active-item", page.filterPanel.menu.noItemIsActive());
    }
}
