package com.analyzelog;


import com.analyzelog.utils.TagUtils;

public class Client {

    public static void main(String[] args) {
        String fileName = "D:\\logfile\\2020_08_20_56387_zhongmengmeng@100talcom$android.txt";

        Director.Builder builder = new Director.Builder().sourcefile(fileName)
                .filterMode(Director.Mode.TAG).setTag(TagUtils.LIVEVOTE);
        Director director = builder.build();
        director.commond();
    }
}
