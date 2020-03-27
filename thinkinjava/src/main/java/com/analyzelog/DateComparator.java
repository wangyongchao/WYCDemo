package com.analyzelog;

import com.analyzelog.entity.LogBean;
import com.analyzelog.utils.Utils;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<LogBean> {
    @Override
    public int compare(LogBean logBean1, LogBean logBean2) {
        String strDate1 = logBean1.getData().getDate();
        String strDate2 = logBean2.getData().getDate();
        Date date1 = Utils.formatDate(strDate1);
        Date date2 = Utils.formatDate(strDate2);
        return date1.compareTo(date2);
    }

}
