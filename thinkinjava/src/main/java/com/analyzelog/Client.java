package com.analyzelog;

import com.analyzelog.utils.EventIdUtils;
import com.analyzelog.utils.TagUtils;

public class Client {

    public static void main(String[] args) {
        String fileName = "D:\\logfile\\2020_05_16_43059819_13793827889$android.txt";

        Director.Builder builder = new Director.Builder().sourcefile(fileName)
                .filterMode(Director.Mode.EVNENTID).setEventId(EventIdUtils.INTERFACE_DATA);
        Director director = builder.build();
        director.commond();
    }
}
