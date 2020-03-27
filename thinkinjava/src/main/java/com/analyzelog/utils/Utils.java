package com.analyzelog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {


    /**
     * 日期格式化
     *
     * @param strDate
     * @return
     */
    public static Date formatDate(String strDate) {
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdft.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
