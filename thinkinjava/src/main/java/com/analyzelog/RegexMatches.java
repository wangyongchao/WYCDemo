package com.analyzelog;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.regex.Pattern;

public class RegexMatches {


    public static void main(String args[]) {
        test();
    }

    private static void test() {
        String s =
                "[{\"s_l0\":\"{fadsdsaf}\"},{\"s_l0\":\"{aaa}\"},{\"s_l0\":\"{bbb}\"}," +
                        "{\"s_l0\":\"{ccc}\"},{\"s_l0\":\"{ffff}\"}]";

        if (s.startsWith("[") && s.endsWith("]")) {
            String cusString = cutArrayString(s);
            String patternstr = ",{\"s_l0\":";

            int indexOf = cusString.indexOf(patternstr);

            while (indexOf != -1) {
                String jsonString = cusString.substring(0, indexOf);
                System.out.println("jsonString=" + jsonString);
                cusString = cusString.substring(indexOf + 1);
                indexOf = cusString.indexOf(patternstr);
                System.out.println(cusString);
            }
        }
    }

    private static void split(String str) {


    }

    private static String cutArrayString(String str) {
        String firstString = "[";
        String lastString = "]";
        int firstIndex = str.indexOf(firstString);
        int lastIndex = str.lastIndexOf(lastString);
        return str.substring(firstIndex + firstString.length(), lastIndex);

    }


    private static void parseLineString(String line) {
        line = cutString(line);
        String patternstr = "\",\"s_l[0-9]+\":\"";
        Pattern pattern = Pattern.compile(patternstr);
        List<String> strings = Splitter.on(pattern).splitToList(line);
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (i == 0) {
                String subpatternstr = "\"s_l[0-9]+\":\"";
                Pattern subpattern = Pattern.compile(subpatternstr);
                List<String> substrings = Splitter.on(subpattern).splitToList(s);
                s = substrings.get(1);
            }
            System.out.println(s);
        }
    }

    private static String cutString(String str) {
        String firstString = "{";
        String lastString = "\"}";
        int firstIndex = str.indexOf(firstString);
        int lastIndex = str.lastIndexOf(lastString);
        return str.substring(firstIndex + firstString.length(), lastIndex);
    }
}