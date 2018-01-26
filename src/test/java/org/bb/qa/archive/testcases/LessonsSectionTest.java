package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.LessonsPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class LessonsSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        LessonsPage page = new LessonsPage().open();

        // section header
        assertThat("header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("header.text", page.sectionHeader.getHeaderText(), is(equalTo("Daily Lessons")));
        assertThat("subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));

        // pagination
        assertThat("pagination.results", page.paginationHeader.isResultsDisplayed());
        assertThat("pagination.menu", page.pagination.isRootDisplayed());

        // filters
        assertThat("filters.menu", page.filterPanel.menu.hasFilters("Topics", "Sources", "Date"));
        assertThat("no-active-item", page.filterPanel.menu.noItemIsActive());
    }

    @Test
    public void coverAllDateRange(){
        // go to Daily Lessons -> Today -> Yesterday -> Last 7 Days -> Last 30 Days -> Last Month -> This Month -> Custom Range
        // Apply each filter -> get date from created label -> Compare with placeholder
    }

    @Test
    public void datePickerLastMonthRemoveLabel(){

        // filter-panel
        //
    }

    @Test
    public void sourcesAndVerifyInnerSources(){

    }

}
