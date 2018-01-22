package org.bb.qa.common.element;

import org.openqa.selenium.WebElement;

public class ElementUtils {

    public static boolean hasCssClass(WebElement e, String cssClass) {
        String classes = e.getAttribute("class");
        for (String c : classes.split("\\s+")) {
            if (c.equals(cssClass)) {
                return true;
            }
        }

        return false;
    }

}
