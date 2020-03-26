package com.analyzelog;

import com.google.common.base.Splitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 日志分析程序
 */
public class FileAnalyzer implements Analyzer{
    private String mFileName = "D:/logfile/demo_ceshi.txt";
    private ArrayList<String> mJonsStrings = new ArrayList<>();

    public FileAnalyzer(String fileName) {
        this.mFileName = fileName;
    }

    @Override
    public void startAnalyzer() {
        mJonsStrings.clear();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(mFileName));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parseLineString(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void parseLineString(String line) {
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
                mJonsStrings.add(substrings.get(1));
            } else {
                mJonsStrings.add(s);
            }
        }
    }


    private String cutString(String str) {
        String firstString = "{";
        String lastString = "\"}";
        int firstIndex = str.indexOf(firstString);
        int lastIndex = str.lastIndexOf(lastString);
        return str.substring(firstIndex + firstString.length(), lastIndex);
    }


    public ArrayList<String> getJonsStrings() {
        return mJonsStrings;
    }
}
