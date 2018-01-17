package org.bb.qa.archive.testcases;

import org.bb.qa.common.TestTemplate;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LessonsSectionTest extends TestTemplate {

    @Test
//    @Parameters("link")
    public void verifyHeader() {
        driver.get("https://archive.kbb1.com");
    }
}
