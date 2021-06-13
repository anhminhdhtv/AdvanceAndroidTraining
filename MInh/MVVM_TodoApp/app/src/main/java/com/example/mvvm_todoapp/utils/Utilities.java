package com.example.mvvm_todoapp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static String convertDateToString(LocalDate date) {
        if (date == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        return date.format(formatter);
    }

    public static LocalDate convertStringToDate(String strDate) {
        if (strDate == null || strDate.isEmpty()) {
            return LocalDate.now();
        }
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
        return LocalDate.parse(strDate, dtf);
    }
}
