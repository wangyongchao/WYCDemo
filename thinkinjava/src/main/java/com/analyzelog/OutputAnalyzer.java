package com.analyzelog;

import com.alibaba.fastjson.JSON;
import com.analyzelog.entity.LogBean;
import com.analyzelog.utils.Html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 输出数据
 */
public class OutputAnalyzer extends Analyzer {
    private final Director.FilterMode mMode;
    private File mDestFile;


    public OutputAnalyzer(File destFile, Director.FilterMode mode) {
        this.mDestFile = destFile;
        this.mMode = mode;
    }

    @Override
    public void startAnalyzer(Response response) {
        System.out.println("start OutputAnalyzer mode=" + mMode);
        if (Director.FilterMode.CRASH == mMode) {
            output(response.getCrashLogBeans());
        } else if (Director.FilterMode.ANR == mMode) {
        } else {
        }
        System.out.println("over-------------------------------");

    }

    private void output(List<LogBean> logBeans) {
        if (logBeans == null || logBeans.isEmpty()) {
            System.out.println("no log mode=" + mMode);
            return;
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(mDestFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format(Html.header, mDestFile.getName()));
            bufferedWriter.flush();

            for (int j = 0; j < logBeans.size(); j++) {
                bufferedWriter.write(String.format(Html.pre_content, "id" + j));
            }

            bufferedWriter.write(Html.scirpt1);
            bufferedWriter.newLine();


            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
            for (int i = 0; i < logBeans.size(); i++) {
                LogBean logBean = logBeans.get(i);
                Date date = new Date(Long.valueOf(logBean.event_timestamp) / 1000);
                bufferedWriter.write(String.format(Html.data, "var" + i, JSON.toJSONString(logBean) ));
                bufferedWriter.newLine();
                bufferedWriter.write(String.format(Html.result, "id" + i, format.format(date) + ":", "var" + i));
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


}
