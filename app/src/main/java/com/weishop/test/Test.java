package com.weishop.test;

import java.util.Calendar;

/**
 * Created by wangyongchao on 2018/6/5.
 */

public class Test {
    public static void main(String[] args) {

        getTimeTip(System.currentTimeMillis());
        getTimeTip(1569600000000l);
    }

    public static void getTimeTip(long time) {
        Calendar pre = Calendar.getInstance();
        pre.setTimeInMillis(time);

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(System.currentTimeMillis());

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);


        }

    }
}
