package org.bb.qa.archive.pageobjects.pages;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArchiveSources extends PageObject {

    public final static String CANCEL_BTN = ".ui.button:nth-child(2)";
    public final static String SOURCE_RESULTS_TABLE = ".ui.sortable.very.basic.table>tbody>tr";
    public final static String INNER_LESSON_TAGS = ".ui.list div";
    private final static String SOURCE_CHILDREN = ".ui.blue.tiny.compact.pointing>a";
    private final static String COLUMNS = ".filter-steps__column-wrapper";
    private final static String APPLY_BTN = ".ui.primary.right.floated.button";

    public boolean navToSourceAndApply(String navToLable) {
        String[] tokens = navToLable.split(">");
        for (int i = 0; i < tokens.length; i++)
            tokens[i] = tokens[i].trim();

        boolean flag = false;
        for (int i = 1; i <= tokens.length; i++) {
            flag = clickListAndTarget(String.format(COLUMNS + ":nth-child(%d) a", i), tokens[i - 1]);
            if (!flag) break;
        }
        navigate(APPLY_BTN);
        click(driver.findElement(By.cssSelector(APPLY_BTN)));
        return flag;
    }


    public boolean checkResultsMoreThanZero() {
        List<String> count = getWebElemListReturnStringList(SOURCE_RESULTS_TABLE);
        return count.size() > 0;
    }

    public void checkIfGreaterThanZero() {
        String[] result;
        List<WebElement> h2 = driver.findElements(By.tagName("H2"));
        System.out.println("number of H2 tags are:" + h2.size());
        for (WebElement option : h2) {
            if (option.getText().contains("Results")) {
                System.out.println(option.getText());
                highlightElement(option);
                result = option.getText().split("of");
                int foo = Integer.parseInt(result[1].trim());
                System.out.println(foo);
            }
        }
    }

    public String clickOnFirstAndReturnLabel(String label) {
        String foundLabel = " ";
        navigate(SOURCE_RESULTS_TABLE + " a");
        this.getCssListAndClickOnFirstElement(SOURCE_RESULTS_TABLE + " a");
        List<WebElement> list = driver.findElements(By.cssSelector(INNER_LESSON_TAGS));
        navigate(INNER_LESSON_TAGS);
        for (WebElement option : list) {
            if (option.getText().trim().contains(label.trim())) {
                click(option);
                foundLabel = option.getText().trim();
                System.out.println(option.getText());
                return foundLabel;
            }
        }
        return foundLabel;
    }
}






