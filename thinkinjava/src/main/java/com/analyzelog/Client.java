package com.analyzelog;


import com.analyzelog.utils.TagUtils;

public class Client {

    public static void main(String[] args) {
        String fileName = "D:\\logfile\\2020_09_18_13072_jiangyucen@100talcom$android.txt";

        Director.Builder builder = new Director.Builder().sourcefile(fileName)
                .filterMode(Director.Mode.TAG).setTag(TagUtils.RTCLOG);
        Director director = builder.build();
        director.commond();
    }
}
