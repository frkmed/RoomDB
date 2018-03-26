package com.marwa.chat.Util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import java.text.SimpleDateFormat;

public class Util {
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static String getTimeStamp() {
        return new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.FRANCE).format(new Date());
    }


    public static String getShordDate(Date date) {
        DateFormat formatter = new SimpleDateFormat(getTimeStamp());
        String today = formatter.format(date);
        return today;
    }
}
