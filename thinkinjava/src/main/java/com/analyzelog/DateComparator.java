package com.analyzelog;

import com.analyzelog.entity.LogBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<LogBean> {
    @Override
    public int compare(LogBean logBean1, LogBean logBean2) {
        String strDate1 = logBean1.getData().getDate();
        String strDate2 = logBean2.getData().getDate();
        Date date1 = formatDate(strDate1);
        Date date2 = formatDate(strDate2);
        return date1.compareTo(date2);
    }

    private Date formatDate(String strDate) {
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
