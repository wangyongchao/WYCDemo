package com.example.myapplication;

import java.io.FileWriter;

/**
 * Created by wangyongchao on 17/6/1.
 */

public class Test {
    public static void main(String[] args) throws Exception {
        String name;
        FileWriter fw = null;
        for (int i = 0; i < 10; i++) {
            fw = new FileWriter("demo" + i + ".java");
            for (int j = 6554 * i; j <= 6554 * (i + 1); j++) {
                fw.write("public void me" + j + "(){ }\r\n");
                fw.flush();
            }
        }

        fw.close();
    }
}
