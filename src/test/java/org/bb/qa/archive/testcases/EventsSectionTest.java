package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.EventsPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EventsSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        EventsPage page = new EventsPage().open();

        // section header
        assertThat("sectionHeader.header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("sectionHeader.header.text", page.sectionHeader.getHeaderText(), is(equalTo("Events")));
        assertThat("sectionHeader.subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("sectionHeader.subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));
        assertThat("sectionHeader.no-active-item", page.sectionHeader.noMenuItemIsActive());

        // There is no pagination in this page

        // filters
        assertThat("filters.menu", page.filterPanel.menu.hasFilters("Locations", "Year"));
    }
}
