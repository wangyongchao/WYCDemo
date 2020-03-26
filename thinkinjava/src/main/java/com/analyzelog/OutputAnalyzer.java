package com.analyzelog;

import com.analyzelog.entity.LogBean;
import com.analyzelog.utils.Html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 输出数据
 */
public class OutputAnalyzer implements Analyzer {
    private List<LogBean> mLogBeans = new ArrayList<>();
    private List<LogBean> mTagLogBeans = new ArrayList<>();
    private List<LogBean> mEventIdLogBeans = new ArrayList<>();
    private String mFileName;
    private String mTAG;
    private String mEventId;//直播中eventid都是live_debug_message，别的业务都是eventId=tag


    public OutputAnalyzer(List<LogBean> logBeans, List<LogBean> tagBeans,
                          List<LogBean> eventBeans, String fileName, String tag, String eventId) {
        this.mLogBeans.addAll(logBeans);
        this.mTagLogBeans.addAll(tagBeans);
        this.mEventIdLogBeans.addAll(eventBeans);
        this.mFileName = fileName;
        this.mTAG = tag;
        this.mEventId = eventId;
    }

    @Override
    public void startAnalyzer() {
        outputAllData();
        if (mTAG != null) {
            outputByTag();
        }
        if (mEventId != null) {
            outputByEventId();
        }
        System.out.println("over-------------------------------");

    }

    /**
     * 根据Tag
     */
    private void outputByTag() {
        File file = new File(mFileName);
        String name = file.getName();
        String formatName = name.substring(0, name.lastIndexOf(".")) + "_" + mTAG + ".html";
        File formatFile = new File(file.getParent(), formatName);
        output(formatFile, mTagLogBeans,formatName);
    }

    /**
     * 根据eventId
     */
    private void outputByEventId() {
        File file = new File(mFileName);
        String name = file.getName();
        String formatName = name.substring(0, name.lastIndexOf(".")) + "_" + mEventId + ".html";
        File formatFile = new File(file.getParent(), formatName);
        output(formatFile, mEventIdLogBeans,formatName);
    }

    /**
     * 输出所有的日志 按照时间排列
     */
    private void outputAllData() {
        File file = new File(mFileName);
        String name = file.getName();
        String formatName = name.substring(0, name.lastIndexOf(".")) + "_all.html";
        File formatFile = new File(file.getParent(), formatName);
        output(formatFile, mLogBeans,formatName);

    }

    private static void output(File file, List<LogBean> logBeans,String h5Title) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format(Html.header,h5Title));
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
