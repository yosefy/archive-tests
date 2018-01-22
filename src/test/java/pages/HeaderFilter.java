package pages;

import helpers.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class HeaderFilter extends BasePageObject {
    public HeaderFilter(WebDriver driver) {super(driver);}

    private final static String FILTER_BY = ".ui.container.padded.horizontally>a";
    private final static String EVENTS_FILTER_TABS = ".ui.huge.tabular.menu>a";
    private final static String SUM_COLUMN_TO_CHECK = ".column>div>a";

    Set<String> hSet = new HashSet<String>() {{
        add("Topics");
        add("Sources");
        add("Date");
        add("Year");
        add("Genre/Program");
        add("Conventions");
        add("Holidays");
        add("Picnics");
        add("Unity Days");
        add("Friends Gatherings");
        add("Meals");
        add("Locations");
        add("Publishers");
    }};

    //        System.out.println(hSet);

    public boolean checkFilterBy (ArrayList<String> firstFilter){
        List<WebElement> firstListFiltersFromUI = driver.findElements(By.cssSelector(FILTER_BY));
        System.out.println("Size of filer: " + firstListFiltersFromUI.size());
        for (int i = 0; i < firstListFiltersFromUI.size(); i++) {
            highlightElement(firstListFiltersFromUI.get(i));
            if (firstListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i)))
                System.out.println("OK - " + firstListFiltersFromUI.get(i));
            else {
                System.out.println("NOT -" + firstListFiltersFromUI.get(i));
                return false;
            }
        }
        return true;
    }


    public boolean checkFilterByEvent(ArrayList<String> firstFilter){
        if (driver.findElements(By.cssSelector(SUM_COLUMN_TO_CHECK)).size()>15) {
            List<WebElement> secondListFiltersFromUI = driver.findElements(By.cssSelector(EVENTS_FILTER_TABS));
            for (int i=0 ; i<secondListFiltersFromUI.size() ; i++) {

                click(secondListFiltersFromUI.get(i));

                if(secondListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i))&&firstFilter.get(i).equals("Conventions"))
                    if(!checkFilterBy(new ArrayList<>(Arrays.asList("Locations", "Year"))))
                        return false;
                else if(secondListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i))&&firstFilter.get(i).equals("Holidays"))
                    if(!checkFilterBy(new ArrayList<>(Arrays.asList("Holidays", "Year"))))
                        return false;
                else if(secondListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i))&&firstFilter.get(i).equals("Picnics"))
                    if(!checkFilterBy(new ArrayList<>(Collections.singletonList("Year"))))
                        return false;
                else if(secondListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i))&&firstFilter.get(i).equals("Unity Days"))
                    if(!checkFilterBy(new ArrayList<>(Collections.singletonList("Year"))))
                        return false;
                else if(secondListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i))&&firstFilter.get(i).equals("Friends Gatherings"))
                    if(!checkFilterBy(new ArrayList<>(Collections.singletonList("Date"))))
                        return false;
                else if(secondListFiltersFromUI.get(i).getText().trim().equals(firstFilter.get(i))&&firstFilter.get(i).equals("Meals"))
                    if(!checkFilterBy(new ArrayList<>(Collections.singletonList("Date"))))
                        return false;
            }
        }
        return true;
    }

}
