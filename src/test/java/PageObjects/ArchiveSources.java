package PageObjects;

import helper.Class.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class ArchiveSources extends BaseClass {

    public ArchiveSources(WebDriver driver) {
        super(driver);
    }

    private final static String SOURCE_CHILDREN = ".ui.blue.tiny.compact.pointing>a";
    private final static String COLUMNS = ".filter-steps__column-wrapper";


    private final static String APPLY_BTN = ".ui.button:nth-child(1)";
    public final static String CANCEL_BTN = ".ui.button:nth-child(2)";
    public final static String SOURCE_RESULTS_TABLE = ".ui.sortable.very.basic.table>tbody>tr";



    public boolean navToSourceAndApply(String navToLable) {
        String[] tokens = navToLable.split(">");

        for (int i = 0; i < tokens.length; i++)
            tokens[i] = tokens[i].trim();

        boolean flag = false;
        for (int i = 1; i <= tokens.length ; i++) {
            flag = clickListAndTarget(String.format(COLUMNS + ":nth-child(%d) a", i), tokens[i - 1]);
            if (!flag) break;
        }

        click(driver.findElement(By.cssSelector(APPLY_BTN)));
        return flag;
    }


    public boolean checkContent(int expectedCount) {
        List<String> count = getList(SOURCE_RESULTS_TABLE);
        return expectedCount == count.size();
    }
}






