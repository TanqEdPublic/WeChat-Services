/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

    // date pattern that is used for conversion
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // the date formatter
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_PATTERN);

    
    public static String format(LocalDateTime date){
        if(date == null){
            return null;
        }
        return DATE_FORMATTER.format(date);
    }
    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static LocalDateTime parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDateTime::from);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    
    public static boolean validDate(String dateString){
        // try to parse string
        return DateUtil.parse(dateString) != null;
    }
}
