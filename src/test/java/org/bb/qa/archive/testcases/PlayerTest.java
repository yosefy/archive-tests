package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.LessonUnitPage;
import org.bb.qa.archive.pageobjects.widgets.videobox.Controls;
import org.bb.qa.archive.pageobjects.widgets.videobox.Player;
import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofMinutes;
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
        player.waitForVideoReady();
        player.playFor(ZERO);
        assertThat("player.isVideoPlaying", player.isVideoPlaying());
        player.pause();
        assertThat("player.isVideoPaused", player.isVideoPaused());
    }

    @Test
    public void timeCodeIsProgressing() {
        Player player = new LessonUnitPage().open().player;
        player.waitForPresent();

        player.waitForVideoReady();
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
    }

    // controls are hidden automatically after 2 seconds
    // controls are shown on hover
    @Test
    public void isControlHidden() {
        Player player = new LessonUnitPage().open().player;
        player.waitForVideoReady();

        // play and wait 3 seconds
        player.playFor(ofSeconds(3));
        player.clickOutOfPlayerAndWait();

        assertThat("player.Controls.isHidden", Controls.isHidden());

        player.moveToControl(ofSeconds(1));
        assertThat("player.Controls.isDisplayed", !Controls.isHidden());
    }


    @Test
    public void isTimeCodeUpdatedBySeek() {
        Player player = new LessonUnitPage().open().player;
        player.waitForVideoReady();

        player.seekToTime(ofSeconds(300));

        Duration timeCode = player.getTimeCodeJS();
        assertThat("player.Controls.timecode", timeCode.equals(Duration.ofSeconds(300)));


    }


    @Test
    public void timeCodeUpdateByLink() {
        Player player = new LessonUnitPage().open().player;
        player.waitForVideoReady();

        player.getVideoURLWithArgs("sstart=4m&send=10m");
        player.waitForVideoReady();
        Duration[] timeCode = player.getTimeCode();
        assertThat("startTime.initial", timeCode[0].equals(ofMinutes(4)));

    }

    @Test
    public void speedSelector() {
        Player player = new LessonUnitPage().open().player;
        player.waitForVideoReady();

        player.clickOnPlayerSpeedRate();
        player.selectFromPlayerSpeedRateList("2x");
        assertThat("video.playbackRate", player.getPlaybackRate().equals("2"));
        player.clickOnPlayerSpeedRate();
        player.selectFromPlayerSpeedRateList("1.5x");
        assertThat("video.playbackRate", player.getPlaybackRate().equals("1.5"));
        player.clickOnPlayerSpeedRate();
        player.selectFromPlayerSpeedRateList("1.25");
        assertThat("video.playbackRate", player.getPlaybackRate().equals("1.25"));
        player.clickOnPlayerSpeedRate();
        player.selectFromPlayerSpeedRateList("1x");
        assertThat("video.playbackRate", player.getPlaybackRate().equals("1"));
    }


    @Test
    public void volumeBar() {

    }

    @Test
    public void audioVideoToggle() {

    }

    @Test
    public void languageSelector() {

    }

    @Test
    public void fullScreenToggle() {

    }

    @Test
    public void sharingModeOn() {

    }

    @Test
    public void playerButtons(){

    }

    @Test
    public void sharingModeOff() {

    }

    @Test
    public void sharingModeActions() {

    }


    // +- volume
    // full screen







}
