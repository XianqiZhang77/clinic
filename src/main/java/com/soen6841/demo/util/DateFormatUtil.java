package com.soen6841.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static String DateUtil(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(date);
    }
}
