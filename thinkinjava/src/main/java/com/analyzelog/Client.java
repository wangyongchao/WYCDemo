package com.analyzelog;

public class Client {

    public static void main(String[] args) {
        String fileName = "D:/logfile/2020_03_23_20758941_15912403790$android.txt";
        String strDate = "2020-03-23 19:35:11";
        String endDate = "2020-03-23 19:35:13";

        Director.Builder builder = new Director.Builder().sourcefile(fileName)
                .filterMode(Director.Mode.EVNENTID).setEventId("live_debug_message").dateRange(strDate, endDate);
        Director director = builder.build();
        director.commond();
    }
}
