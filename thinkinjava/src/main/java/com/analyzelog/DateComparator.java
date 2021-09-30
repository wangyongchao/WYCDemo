package com.analyzelog;

import com.analyzelog.entity.LogBean;

import java.util.Comparator;

public class DateComparator implements Comparator<LogBean> {
    @Override
    public int compare(LogBean logBean1, LogBean logBean2) {
        return Long.valueOf(logBean1.event_timestamp).compareTo(Long.valueOf(logBean2.event_timestamp));
    }

}
