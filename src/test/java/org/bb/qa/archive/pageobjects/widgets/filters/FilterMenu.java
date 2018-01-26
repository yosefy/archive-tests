package org.bb.qa.archive.pageobjects.widgets.filters;

import org.bb.qa.archive.pageobjects.PageObject;
import org.bb.qa.common.element.ElementUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterMenu extends PageObject {

    @FindBy(css = ".index-filters .item")
    private List<WebElement> items;

    @FindBy(css = ".index-filters .item.active")
    private WebElement activeItem;



    public boolean noItemIsActive() {
        return items.stream()
                .noneMatch(x -> ElementUtils.hasCssClass(x, "active"));
    }

    public boolean hasFilters(String... names) {
        Set<String> s = items.stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());
        return Arrays.stream(names).allMatch(s::contains);
    }

    public boolean navToFilterAndOpenSubFilter(String... names) {
        for (WebElement webElement : items) {
           if (webElement.getText().trim().equals(names[0])) {
                click(webElement);
                return true;
           }
        }
        return false;
    }

//    public boolean noMenuItemIsActive() {
////        for (WebElement webElement : menuItems) {
////           if (ElementUtils.hasCssClass(webElement, "active")) {
////               return true;
////           }
////        }
////        return false;
//
//        return menuItems.stream()
//                .anyMatch(x -> ElementUtils.hasCssClass(x, "active"));
//    }

    public boolean isActiveItem(String name) {
        return activeItem.getText().equals(name);
    }

    public WebElement getActiveItem() {
        return activeItem;
    }
}
