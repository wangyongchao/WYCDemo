package com.test.pratice;


import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by wangyongchao on 16/11/23.
 */

public class Test1 {


    public static void main(String[] args) {


    }


    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    private void test() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>(0, 0.75f, true);
        linkedHashMap.put(1, "1");
        linkedHashMap.put(2, "2");
        linkedHashMap.put(3, "3");
        linkedHashMap.put(4, "4");
        print(linkedHashMap);


        linkedHashMap.get(1);
        linkedHashMap.get(1);

        linkedHashMap.put(2, "222");

        linkedHashMap.get(4);

        linkedHashMap.get(3);

        //put ,get都算使用，最近使用的放入队列的末尾，队列首部都是最近最少使用的

        print(linkedHashMap);

    }

    private static void print(HashMap<Integer, String> map) {
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            Integer key = next.getKey();
            System.out.print(",key=" + key);
        }
        System.out.println();
    }

}
