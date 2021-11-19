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
        StringBuffer stringBuffer = new StringBuffer();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(mSourceFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
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
        response.setJsonStrings(compress(stringBuffer.toString()));

    }

    /**
     * 压缩json<br/>
     * 将格式化的json字符串压缩为一行，去掉空格、tab，并把换行符改为显式的\r\n <br/>
     * ！！！只能处理正确json字符串，不对json字符串做校验
     * @param json
     * @return
     */
    public static String compress(String json)
    {
        if (json == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean skip = true;// true 允许截取(表示未进入string双引号)
        boolean escaped = false;// 转义符
        for (int i = 0; i < json.length(); i++)
        {
            char c = json.charAt(i);
            if (!escaped && c == '\\')
            {
                escaped = true;
            }
            else
            {
                escaped = false;
            }
            if (skip)
            {
                if (c == ' ' || c == '\r' || c == '\n' || c == '\t')
                {
                    continue;
                }
            }
            sb.append(c);
            if (c == '"' && !escaped)
            {
                skip = !skip;
            }
        }
        return sb.toString().replaceAll("\r\n", "\\\\r\\\\n");
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
