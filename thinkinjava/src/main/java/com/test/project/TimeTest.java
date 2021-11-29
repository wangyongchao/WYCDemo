package com.test.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeTest {


    public enum Source {
        LIST("list"), EXE("exe");
        private String mSource;

        Source(String source) {
            this.mSource = source;
        }
    }


    public static void main(String[] args) {
        getTime2(1637306270847100l);// startup
        firebaseTime();


    }

    private static void firebaseTime() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sf.parse("2021-11-08 04:06:23");
            long time = date.getTime() * 1000;
            System.out.println(time);
//            getTime2(1631637626279111l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 微秒转日期
     *
     * @param mms
     */
    private static void getTime2(long mms) {
        Date date = new Date(mms / 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        System.out.println("time is=" + format.format(date));

    }

    /**
     * 微秒转日期
     *
     * @param mms
     */
    private static void getTime(long mms) {
        long sec = mms / 1000;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(sec);
        DateFormat df1 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        System.out.println("time is=" + df1.format(instance.getTime()));

    }

    private static void getAmericaTime() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1627517912521012l);
        DateFormat df1 = DateFormat.getDateTimeInstance();
        System.out.println("America Los_Angeles time is=" + df1.format(instance.getTime()));
    }

    private static void testTimezone() {
        Date date = new Date();
        DateFormat dateFormat;

        dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("中国当前日期时间：" + dateFormat.format(date));

        dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        System.out.println("英国当前日期时间：" + dateFormat.format(date));


        dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println("美国当前日期时间：" + dateFormat.format(date));
    }


    private static void currentDate() {
        Date date = new Date();
        Locale locale = Locale.CHINA;
        DateFormat shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        shortDf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));//Asia/Chongqing
        System.out.println(TimeZone.getDefault().getID());
        System.out.println("中国当前日期时间：" + shortDf.format(date));

        locale = Locale.ENGLISH;
        shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        shortDf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        System.out.println("英国当前日期时间：" + shortDf.format(date));

    }


    private static void formatDate() {
        Date date = new Date();
        Locale locale = Locale.CHINA;
        DateFormat shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        System.out.println("中国格式：" + shortDf.format(date));

        locale = Locale.ENGLISH;
        shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        System.out.println("英国格式：" + shortDf.format(date));


        locale = Locale.US;
        shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        System.out.println("美国格式：" + shortDf.format(date));

        locale = Locale.GERMANY;
        shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
        System.out.println("德国格式：" + shortDf.format(date));
    }
}
