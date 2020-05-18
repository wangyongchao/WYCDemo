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
public class FileAnalyzer extends Analyzer {
    private File mSourceFile;
    private int lineNume = 0;
    ArrayList<String> mJonsStrings = new ArrayList<>();

    public FileAnalyzer(File sourceFile) {
        this.mSourceFile = sourceFile;
    }

    @Override
    public void startAnalyzer(Response response) {
        System.out.println("start FileAnalyzer");
        lineNume = 0;
        mJonsStrings.clear();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(mSourceFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineNume++;
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
        response.setJsonStrings(mJonsStrings);
    }

    private void parseLineString(String line) {
        //如果是数组  String s = "[{\"s_l0\"},{\"s_l1\"}]";
        if (line.startsWith("[") && line.endsWith("]")) {
            String cusString = cutArrayString(line);
            String patternstr = ",{\"s_l0\":";

            int indexOf = cusString.indexOf(patternstr);

            while (indexOf != -1) {
                String jsonString = cusString.substring(0, indexOf);
                parseJsonStringObject(jsonString);
                cusString = cusString.substring(indexOf + 1);
                indexOf = cusString.indexOf(patternstr);
            }
            parseJsonStringObject(cusString);
        } else {
            parseJsonStringObject(line);
        }


    }

    private void parseJsonStringObject(String str) {
        str = cutString(str);
        String patternstr = "\",\"s_l[0-9]+\":\"";
        Pattern pattern = Pattern.compile(patternstr);
        List<String> strings = Splitter.on(pattern).splitToList(str);
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (i == 0) {
                String subpatternstr = "\"s_l[0-9]+\":\"";
                Pattern subpattern = Pattern.compile(subpatternstr);
                List<String> substrings = Splitter.on(subpattern).splitToList(s);
                s = substrings.get(1);
            }
//            System.out.println("-------------------"+lineNume);
//            System.out.println(s);
            mJonsStrings.add(s);
        }
    }


    private static String cutArrayString(String str) {
        String firstString = "[";
        String lastString = "]";
        int firstIndex = str.indexOf(firstString);
        int lastIndex = str.lastIndexOf(lastString);
        return str.substring(firstIndex + firstString.length(), lastIndex);

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
