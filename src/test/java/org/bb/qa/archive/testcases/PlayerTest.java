package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.LessonUnitPage;
import org.bb.qa.archive.pageobjects.widgets.videobox.Player;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;


public class PlayerTest extends TestTemplate {

    @Test
    public void playerIsInitiallyReadyToPlay() {
        Player player = new LessonUnitPage().open().player;
        player.waitForPresent();
        assertThat("src", player.getVideoSrc(), not(isEmptyOrNullString()));
        assertThat("paused", player.isVideoPaused());
        assertThat("controls", player.controls.isRootDisplayed());
        assertThat("onScreen.play", player.onScreenControls.isPlayReady());
        player.waitForVideoReady();
    }
}
