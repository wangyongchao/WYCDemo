package com.analyzelog;

import java.io.File;
import java.util.Map;

public class Director {
    private FilterMode mMode; //过滤模式
    private String mStartDate; //查询日期开始日期
    private String mEndDate;//查询结束日期
    private String mEventName;
    File mSourceFile; //源文件
    File mDesFile; //输出文件
    private Analyzer analyzer;
    private Response response;
    Map<String, String> mParams;

    public Director(Builder builder) {
        this.mStartDate = builder.mStartDate;
        this.mEndDate = builder.mEndDate;
        this.mEventName = builder.mEventName;
        this.mMode = builder.mMode;
        this.mDesFile = builder.mDesFile;
        this.mSourceFile = builder.mSourceFile;
        this.mParams = builder.mParams;

        FileAnalyzer fileAnalyzer = new FileAnalyzer(mSourceFile);
        this.analyzer = fileAnalyzer;
        response = new Response();

        JsonAnalyzer jsonAnalyzer = new JsonAnalyzer();
        fileAnalyzer.nextAnalyzer(jsonAnalyzer);

        FilterAnalyzer processAnalyzer = new FilterAnalyzer(mMode,mEventName, mParams, mStartDate, mEndDate);
        jsonAnalyzer.nextAnalyzer(processAnalyzer);

        if (mDesFile == null) {
            defaultDesFile();
        }
        OutputAnalyzer outputAnalyzer = new OutputAnalyzer(mDesFile, mMode);
        processAnalyzer.nextAnalyzer(outputAnalyzer);
    }

    private void defaultDesFile() {
        if (FilterMode.CRASH == mMode) {
            String name = mSourceFile.getName();
            String formatName = name.substring(0, name.lastIndexOf(".")) + "_crash.html";
            mDesFile = new File(mSourceFile.getParent(), formatName);
        } else if (FilterMode.ANR == mMode) {
            String name = mSourceFile.getName();
            String formatName = name.substring(0, name.lastIndexOf(".")) + "_anr.html";
            mDesFile = new File(mSourceFile.getParent(), formatName);
        } else if (FilterMode.BEHAVIOR == mMode) {
            String name = mSourceFile.getName();
            String formatName = name.substring(0, name.lastIndexOf(".")) + mEventName + ".html";
            mDesFile = new File(mSourceFile.getParent(), formatName);
        } else {
            String name = mSourceFile.getName();
            String formatName = name.substring(0, name.lastIndexOf(".")) + "_all.html";
            mDesFile = new File(mSourceFile.getParent(), formatName);
        }
    }

    public void commond() {
        analyzer.begin(response);
    }

    public final static class Builder {
        FilterMode mMode; //过滤模式
        File mSourceFile; //源文件
        File mDesFile; //输出文件
        String mStartDate; //查询日期开始日期
        String mEndDate;//查询结束日期
        String mEventName;
        Map<String, String> mParams;

        public Builder filterMode(FilterMode mode) {
            this.mMode = mode;
            return this;
        }

        public Builder sourceFile(File sourceFile) {
            this.mSourceFile = sourceFile;
            return this;
        }

        public Builder sourcefile(String sourceFile) {
            this.mSourceFile = new File(sourceFile);
            return this;
        }

        public Builder destFile(File destFile) {
            this.mDesFile = destFile;
            return this;
        }


        public Builder setEventName(String eventName) {
            this.mEventName = eventName;
            return this;
        }

        public Builder setParams(Map<String, String> params) {
            this.mParams = params;
            return this;
        }


        /**
         * 日期格式 YYYY-MM-DD HH:MM:SS 2020-03-23 19:35:11
         *
         * @param startDate 2020-03-23 19:35:11
         * @param endDate
         */
        public Builder dateRange(String startDate, String endDate) {
            this.mStartDate = startDate;
            this.mEndDate = endDate;
            return this;
        }

        public Director build() {
            return new Director(this);
        }

    }

    public enum FilterMode {
        ALL, CRASH, ANR, BEHAVIOR
    }

}
