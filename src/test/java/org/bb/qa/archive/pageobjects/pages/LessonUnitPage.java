package org.bb.qa.archive.pageobjects.pages;

import org.bb.qa.archive.pageobjects.PageObject;
import org.bb.qa.archive.pageobjects.widgets.unit.MediaDownloads;
import org.bb.qa.archive.pageobjects.widgets.unit.RecommendedSameCollection;
import org.bb.qa.archive.pageobjects.widgets.unit.UnitInfo;
import org.bb.qa.archive.pageobjects.widgets.unit.UnitMaterials;
import org.bb.qa.archive.pageobjects.widgets.videobox.Player;
import org.openqa.selenium.By;

public class LessonUnitPage extends PageObject {

    public Player player;
    public UnitInfo info;
    public UnitMaterials materials;
    public MediaDownloads downloads;
    public RecommendedSameCollection rSameCollection;

    public LessonUnitPage() {
        super();
        player = new Player();
        info = new UnitInfo();
        materials = new UnitMaterials();
        downloads = new MediaDownloads();
        rSameCollection = new RecommendedSameCollection();
    }

    public LessonUnitPage open() {
        getUrl(urlBuilder.getUrlForPath("lessons/cu/9BBbQn7u")); // FiDUi9Hd
        this.waitForPresent();
        return this;
    }

    @Override
    public void waitForPresent() {
        // Wait for root app element (react has rendered)
        this.wait.forElementPresent(By.id("app"));

        // Wait for data fetching to complete
        this.info.waitForPresent();
    }
}
