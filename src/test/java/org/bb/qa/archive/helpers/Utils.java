package org.bb.qa.archive.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    /**
     * Two strings should have the same length
     **/
    public static boolean comp2StringArrays(String[] first, String[] second) {
        if (first.length == second.length) {
            for (int i = first.length - 1; i > 0; i--) {
                System.out.println(first[i] = first[i].trim());
                System.out.println(second[i] = second[i].trim());
                if (!first[i].equals(second[i]))
                    return false;
            }
        }
        return true;
    }


    public static String convertToDateFromLabel(String value) {
        LocalDate dateName = LocalDate.parse(value.trim(), DateTimeFormatter.ofPattern("d MMM yyyy"));
        return dateName.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
