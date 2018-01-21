package org.bb.qa.archive.pageobjects.pages;

import org.bb.qa.archive.pageobjects.PageObject;
import org.bb.qa.archive.pageobjects.widgets.SectionHeader;
import org.bb.qa.archive.pageobjects.widgets.filters.FilterPanel;
import org.bb.qa.archive.pageobjects.widgets.pagination.Pagination;
import org.bb.qa.archive.pageobjects.widgets.pagination.ResultsPageHeader;
import org.openqa.selenium.By;

public class ProgramsPage extends PageObject {

    public SectionHeader sectionHeader = new SectionHeader();
    public ResultsPageHeader paginationHeader = new ResultsPageHeader();
    public Pagination pagination = new Pagination();
    public FilterPanel filterPanel = new FilterPanel();

    public ProgramsPage open() {
        getUrl(urlBuilder.getUrlForPath("programs"));

        // Wait for root app element (react has rendered)
        this.wait.forElementPresent(By.id("app"));

        // Wait for root unit-list element (data fetching is done)
        this.wait.forElementPresent(By.className("unit-list"));

        return this;
    }

}
