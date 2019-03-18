package com.example.qzz.mvcdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    private static final SimpleDateFormat DATE_TIME_IN_CN = new SimpleDateFormat("EEE MMM dd HH:mm yyyy", Locale.CHINA);


    public static String convertTimeIntoStr(long mills){
        Date date = new Date(mills);
        return DATE_TIME_IN_CN.format(date);
    }
}
