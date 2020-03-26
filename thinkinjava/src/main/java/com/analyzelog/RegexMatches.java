package com.analyzelog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {


    public static void main(String args[]) {
        String INPUT = "{\"s_l145\":\"{\"userid\":\"58215\",\"logid\":\"\",\"xesid\":\"\",";
        System.out.println(INPUT);
        String REGEX = "\"s_l[0-9]+\"";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
        }
    }
}