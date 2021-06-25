package com.test;

import com.alibaba.fastjson.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

public class Client {
    public static final String obj1 = "obj1";
    public static final String obj2 = "obj2";
    public static boolean condition = false;
    public String a;
    public String b;

    public static void main(String[] ars) {
        List<Long> longs = new ArrayList<>();
        longs.add(1234567879l);
        longs.add(123456787l);
        Collections.sort(longs, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return Long.compare(o1, o2);
            }
        });
        System.out.println(longs);


    }

    private static String namePrefix(String name) {
        return name.substring(name.lastIndexOf("_")+1);
    }

    public static int generateIdByCreateTime(long createTime) {
        return (int) (createTime % Integer.MAX_VALUE);
    }

    public static long getDateZeroZone(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        // 设置为时区无关的
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return calendar.getTimeInMillis();
    }

    public static boolean isValidUUID(String uuid) {
        // UUID校验
        if (uuid == null) {
            System.out.println("uuid is null");
        }
        String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
        if (uuid.matches(regex)) {
            return true;
        }
        return false;
    }

}