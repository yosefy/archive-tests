package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.SourcesPage;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SourcesSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        SourcesPage page = new SourcesPage().open();

        // section header
        assertThat("header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("header.text", page.sectionHeader.getHeaderText(), is(equalTo("Kabbalah Sources")));
        assertThat("subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));
    }
}
