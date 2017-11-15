package PageObjects;

import helper.Class.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArchiveTopics extends BaseClass {

    private final static String TOPICS_RESULTS_TABLE = ".ui.basic.clearing.bottom.attached.segment.tab.active>div>div>div";
    private final static String APPLY_BTN = ".ui.button:nth-child(1)";
    public final static String TOPICS_RESULTS = ".ui.sortable.very.basic.table>tbody>tr a";
    public final static String TAG_INSIDE_PROGRAM = ".ui.list>div:nth-child(1) a";

    public ArchiveTopics(WebDriver driver) {
        super(driver);
    }

    public boolean navToTopicsAndApply(String navToTopic){
        boolean flag = clickListAndTarget(TOPICS_RESULTS_TABLE, navToTopic);
        click(driver.findElement(By.cssSelector(APPLY_BTN)));
        return flag;
    }

    public String openFirstResultAndReturnTopics(String part){
        clickListAndTarget(TOPICS_RESULTS, part);
        navigate(TAG_INSIDE_PROGRAM);
        String sources = getStringFromWebElementByCSS(TAG_INSIDE_PROGRAM);
        String topics = sources.replace("Lesson topics - ","").trim();
        System.out.println("Topics-" + topics);
        return topics;
    }
}
