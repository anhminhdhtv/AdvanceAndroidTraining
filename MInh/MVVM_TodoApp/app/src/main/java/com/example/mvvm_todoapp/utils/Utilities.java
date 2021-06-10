package com.example.mvvm_todoapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    public static String convertDateToString(Date date){
        if(date == null) return "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        return dateFormat.format(date);
    }

    public static Date convertStringToDate(String strDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        return dateFormat.parse(strDate);
    }
}
