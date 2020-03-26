package com.analyzelog;

import com.google.common.base.Splitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString {

    public static void main(String[] args) {

        String strDate = "2020-03-23 14:46:51";

        String strDate2 = "2020-03-23 15:46:50";

        String strDate3 = "2020-03-23 14:46:50";

        Date date1 = formatDate(strDate);
        Date date2 = formatDate(strDate2);
        Date date3 = formatDate(strDate3);
        List<Date> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        dates.add(date3);
        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date date, Date t1) {
                return date.compareTo(t1);
            }
        });
        System.out.println(dates);


    }

    private static Date formatDate(String strDate) {
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdft.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     */
    public static void testRegex2() {
        String line = "{\"s_l0\":\"{\"userid\":\"58215\",\"logid\":\"\",\"xesid\":\"\"," +
                "\"pageid\":\"MallHomeActivity\",\"sessid\":\"a13a83957fef4ccf\"," +
                "\"clits\":1585108446898,\"appid\":\"1305801\",\"devid\":\"8\"," +
                "\"clientid\":\"9\",\"loglevel\":\"debug\",\"data\":{\"version\":\"7.11.01\"," +
                "\"systemVersion\":\"10\",\"date\":\"2020-03-25 11:54:06\"," +
                "\"devicename\":\"HUAWEI VOG-AL00\",\"tinkerid\":\"7110101\"," +
                "\"processcreatetime\":1585108418520,\"usinglogindex\":\"102\"," +
                "\"result\":\"{\\\"code\\\":0,\\\"stat\\\":1,\\\"msg\\\":\\\"success\\\"," +
                "\\\"data\\\":{\\\"dnf\\\":{\\\"adslot_id\\\":16,\\\"grade\\\":22}," +
                "\\\"extra\\\":{},\\\"adverts\\\":null}}\"," +
                "\"request_startTime\":\"1585108445967\"," +
                "\"eventid\":\"debug_http_response_log_v1\",\"logtype\":\"pmSuccess\"," +
                "\"request_time\":\"851\",\"rq_hr_param\":\"{\\\"Cookie\\\":\\\"tal_token" +
                "=tal173R1Enyfvhcw5GOrAIQHFAbrYZp" +
                "-FNWvXA2F0tHmTmc5cfXVsEtbPpPWYXu0NQKNGFMw0Va9ORbTWJ5F4wTBma2A5td63w6HX" +
                "-vGzWsIgVCOj5QPGCF2UWH1hv_" +
                "-XB8STSNKWyyQRCOmGF75tSeS1Xn6Y65LQTyEQoV8QNVg5htAgeapKt8zaA6BLpnc4_QzN0YTKqhLF_ZIXrZC61GcMjKVdMk_XHozAWt0nkszLNhsnEELnUvuYZSMHSu3wteN0phs-yrpcBJI7aHazwsHYG3mMeerBoM6rBaxPoiGLpDscSs;\\\",\\\"traceId\\\":\\\"aae2acd3f2c13932\\\",\\\"prelogid\\\":\\\"613cc24fb373c5d96243d14292a07fd8\\\",\\\"X-Region\\\":\\\"1\\\",\\\"rpcId\\\":\\\"1\\\",\\\"deviceModel\\\":\\\"VOG-AL00\\\",\\\"subAppVersionNumber\\\":\\\"\\\",\\\"X-Grade\\\":\\\"8\\\"}\",\"rq_param\":\"{\\\"identifierForClient\\\":\\\"10334810-31bf-49f0-ac40-480967c600a3\\\",\\\"appVersion\\\":\\\"7.11.01\\\",\\\"pageid\\\":\\\"HomeActivity\\\",\\\"systemVersion\\\":\\\"10\\\",\\\"appChannel\\\":\\\"xesmarket\\\",\\\"adslot_id\\\":\\\"16\\\",\\\"systemName\\\":\\\"android\\\",\\\"user_id\\\":\\\"58215\\\",\\\"device_token\\\":\\\"10334810-31bf-49f0-ac40-480967c600a3\\\",\\\"client\\\":\\\"{\\\\\\\"is_first_login\\\\\\\":0,\\\\\\\"net\\\\\\\":\\\\\\\"wifi\\\\\\\",\\\\\\\"os\\\\\\\":\\\\\\\"android\\\\\\\",\\\\\\\"version\\\\\\\":\\\\\\\"71101\\\\\\\"}\\\",\\\"datalogid\\\":\\\"aae2acd3f2c13932\\\",\\\"appVersionNumber\\\":\\\"71101\\\",\\\"is_first_register\\\":\\\"0\\\",\\\"device\\\":\\\"8\\\",\\\"talaccsdkDeviceId\\\":\\\"ceca05dd4c1ab46230a0cc0a6461d09d\\\",\\\"info\\\":\\\"{\\\\\\\"grade\\\\\\\":7}\\\"}\",\"clientIdentifier\":\"10334810-31bf-49f0-ac40-480967c600a3\",\"url\":\"https:\\/\\/ads.xueersi.com\\/v1\\/search\",\"GMT\":\"GMT+08:00\"}}\",\"s_l1\":\"{\"userid\":\"58215\",\"logid\":\"\",\"xesid\":\"\",\"pageid\":\"MallHomeActivity\",\"sessid\":\"a13a83957fef4ccf\",\"clits\":1585108446865,\"appid\":\"1305801\",\"devid\":\"8\",\"clientid\":\"9\",\"loglevel\":\"debug\",\"data\":{\"version\":\"7.11.01\",\"systemVersion\":\"10\",\"date\":\"2020-03-25 11:54:06\",\"devicename\":\"HUAWEI VOG-AL00\",\"tinkerid\":\"7110101\",\"processcreatetime\":1585108418520,\"usinglogindex\":\"100\",\"eventid\":\"debug_http_response_business\",\"clientIdentifier\":\"10334810-31bf-49f0-ac40-480967c600a3\",\"message\":\"httpMonitor:requestUrl:https:\\/\\/ads.xueersi.com\\/v1\\/search 16:0.000-callStart;0.000-dnsStart;0.001-dnsEnd;0.001-connectStart;0.035-secureConnectStart;0.726-secureConnectEnd;0.726-connectEnd;0.726-connectionAcquired;0.727-requestHeadersStart;0.727-requestHeadersEnd;0.727-requestBodyStart;0.727-requestBodyEnd;0.727-responseHeadersStart;0.846-responseHeadersEnd;0.846-responseBodyStart;0.846-responseBodyEnd;0.846-connectionReleased;0.846-callEnd;\",\"GMT\":\"GMT+08:00\"}}\"}";
//        String patternstr = "\"s_l[0-9]+\":\"[\\s\\S]*\"";
//        String patternstr = "s_[1-9]\\d*\":";
        // 第一种 "s_l0":"    第二种 ","s_l1":"        第三种"}
        String substring = line.substring(1, line.length() - 2);
        String patternstr = "\",\"s_l[0-9]+\":\"";
        Pattern pattern = Pattern.compile(patternstr);
        List<String> strings = Splitter.on(pattern).splitToList(substring);
        ArrayList<String> finalStrings = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (i == 0) {
                String subpatternstr = "\"s_l[0-9]+\":\"";
                Pattern subpattern = Pattern.compile(subpatternstr);
                List<String> substrings = Splitter.on(subpattern).splitToList(s);
                finalStrings.add(substrings.get(1));
            } else {
                finalStrings.add(s);
            }
        }

        for (String finalstr :
                finalStrings) {

            System.out.println(finalstr);
        }


    }

    /**
     * 还有一个特殊的组（group(0)），它总是代表整个表达式。该组不包括在 groupCount 的返回值中。
     */
    public static void testRegex() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        System.out.println(m.groupCount());
        if (m.find()) {
            System.out.println(m.matches());
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void testPlitter() {
//        String s = "{\"s_10:\"" +
//                "\"{\"userid:\":\"33333\",data:{\"name\":\"张胜男\"}" +
//                "}";
//
//        List<String> strings = Splitter.on(Pattern.compile("\\|")).splitToList(s);
    }

}
