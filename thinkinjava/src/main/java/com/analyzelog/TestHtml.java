package com.analyzelog;

import com.analyzelog.utils.Html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestHtml {

    public static void main(String[] args) {

        System.out.println();

        String fileName = "D:/logfile/a.html";
        File file = new File(fileName);
        output(file, 100);

    }

    private static void output(File file, int num) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Html.header);
            bufferedWriter.flush();

            for (int j = 0; j < num; j++) {
                bufferedWriter.write(String.format(Html.pre_content, "id" + j));
            }

            bufferedWriter.write(Html.scirpt1);
            bufferedWriter.newLine();

            for (int i = 0; i < num; i++) {
                String format = "\"userid\":\"20758941\",\"logid\":\"\",\"xesid\":\"\"";
                String date = "2018-23-33";
                String output = format;
                bufferedWriter.write(String.format(Html.data, "var" + i, output));
                bufferedWriter.newLine();
                bufferedWriter.write(String.format(Html.result, "id" + i, date + ":", "var" + i));
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            bufferedWriter.write(Html.last);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void output(File file, String string) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
