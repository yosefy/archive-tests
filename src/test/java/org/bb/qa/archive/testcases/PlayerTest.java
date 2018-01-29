package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.LessonUnitPage;
import org.bb.qa.archive.pageobjects.widgets.videobox.Player;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofSeconds;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;


public class PlayerTest extends TestTemplate {

    @Test
    public void isInitiallyReadyToPlay() {
        Player player = new LessonUnitPage().open().player;
        player.waitForPresent();

        assertThat("player.getVideoSrc.notEmptyOrNullString", player.getVideoSrc(), not(isEmptyOrNullString()));
        assertThat("player.isVideoPaused", player.isVideoPaused());
        assertThat("player.controls.isRootDisplayed", player.controls.isRootDisplayed());
        assertThat("player.onScreenControls.isPlayReady", player.onScreenControls.isPlayReady());
        player.waitForVideoReady();
    }

    @Test
    public void playPauseByOnscreen() {
        Player player = new LessonUnitPage().open().player;
        player.waitForPresent();

        player.playByScreenIcon();
        assertThat("player.isVideoPlaying", player.isVideoPlaying());
        player.pauseByScreenClick();
        assertThat("player.isVideoPaused", player.isVideoPaused());
    }

    @Test
    public void playPauseByControls() {
        Player player = new LessonUnitPage().open().player;
        player.waitForPresent();

        player.playFor(ZERO);
        assertThat("player.isVideoPlaying", player.isVideoPlaying());
        player.pause();
        assertThat("player.isVideoPaused", player.isVideoPaused());
    }

    @Test
    public void timeCodeIsProgressing() {
        Player player = new LessonUnitPage().open().player;
        player.waitForPresent();

        // time code is initially zero
        Duration[] timeCode = player.getTimeCode();
        assertThat("startTime.initial", timeCode[0].isZero());
        assertThat("endTime.initial", !timeCode[1].isZero());

        // play and wait 2 seconds
        player.playFor(ofSeconds(2));
        assertThat("player.isVideoPlaying", player.isVideoPlaying());
        player.pause();
        timeCode = player.getTimeCode(); // get time from UI
        assertThat("startTime after pause", timeCode[0].equals(ofSeconds(2)));

        System.out.println(player.getTimeCodeJS());

    }

    // +- volume
    // full screen


    // controls are hidden automatically after 2 seconds

    // controlls are shown on hover

    //
}
