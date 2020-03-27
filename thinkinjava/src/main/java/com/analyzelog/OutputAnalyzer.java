package com.analyzelog;

import com.analyzelog.entity.LogBean;
import com.analyzelog.utils.Html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 输出数据
 */
public class OutputAnalyzer extends Analyzer {
    private final Director.Mode mMode;
    private List<LogBean> mLogBeans;
    private List<LogBean> mTagLogBeans;
    private List<LogBean> mEventIdLogBeans;
    private File mDestFile;


    public OutputAnalyzer(File destFile, Director.Mode mode) {
        this.mDestFile = destFile;
        this.mMode = mode;
    }

    @Override
    public void startAnalyzer(Response response) {
        System.out.println("start OutputAnalyzer mode=" + mMode);
        mLogBeans = response.getLogBeans();
        mTagLogBeans = response.getTagLogBeans();
        mEventIdLogBeans = response.getEventIdLogBeans();
        if (Director.Mode.EVNENTID == mMode) {
            output(mEventIdLogBeans);
        } else if (Director.Mode.TAG == mMode) {
            output(mTagLogBeans);
        } else {
            output(mLogBeans);
        }
        System.out.println("over-------------------------------");

    }

    private void output(List<LogBean> logBeans) {
        if (logBeans == null || logBeans.isEmpty()) {
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

            for (int i = 0; i < logBeans.size(); i++) {
                LogBean logBean = logBeans.get(i);
                String format = logBean.getOriginalJsonStr();
                String date = logBean.getData().getDate();
                bufferedWriter.write(String.format(Html.data, "var" + i, format));
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


}
