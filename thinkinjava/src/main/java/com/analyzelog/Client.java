package com.analyzelog;


import com.analyzelog.utils.EventNameUtils;

public class Client {

    public static void main(String[] args) {
//        String fileName = "D:\\logfile\\2020_09_18_13072_jiangyucen@100talcom$android.txt";
        String fileName = "/Users/wangyongchao/datum-zj/LoseBellyFat/log/results-20210930-184304.json";

        Director.Builder builder = new Director.Builder().sourcefile(fileName)
                .filterMode(Director.FilterMode.CRASH).setEventName(EventNameUtils.SELECT_CONTENT);
        Director director = builder.build();
        director.commond();
    }
}
