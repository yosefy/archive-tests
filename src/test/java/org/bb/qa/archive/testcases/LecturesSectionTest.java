package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.LecturesPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class LecturesSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        LecturesPage page = new LecturesPage().open();

        // section header
        assertThat("sectionHeader.header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("sectionHeader.header.text", page.sectionHeader.getHeaderText(), is(equalTo("Lectures")));
        assertThat("sectionHeader.subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("sectionHeader.subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));

        // pagination
        assertThat("pagination.results", page.paginationHeader.isResultsDisplayed());
        assertThat("pagination.menu", page.pagination.isRootDisplayed());

        // filters
        assertThat("filters.menu", page.filterPanel.menu.hasFilters("Topics", "Sources", "Date"));
        assertThat("no-active-item", page.filterPanel.menu.noItemIsActive());
    }

}
