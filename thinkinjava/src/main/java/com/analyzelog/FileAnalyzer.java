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
    ArrayList<String> mJonsStrings = new ArrayList<>();

    public FileAnalyzer(File sourceFile) {
        this.mSourceFile = sourceFile;
    }

    @Override
    public void startAnalyzer(Response response) {
        System.out.println("start FileAnalyzer");
        mJonsStrings.clear();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(mSourceFile);
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
        response.setJsonStrings(mJonsStrings);
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
