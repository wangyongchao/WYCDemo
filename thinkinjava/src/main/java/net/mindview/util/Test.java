package net.mindview.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by wangyongchao on 16/11/15.
 */

public class Test {

    public static void main(String[] args) {
        SimpleDateFormat format=new SimpleDateFormat("MMM d,yyyy", Locale.getDefault());
        String format1 = format.format(new Date(System.currentTimeMillis()));
        System.out.println(format1);
    }

    public static long getDateZeroZone(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        // 设置为时区无关的
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        // 设置0点
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
