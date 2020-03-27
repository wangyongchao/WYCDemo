package com.analyzelog;

import java.io.File;

public class Director {
    private Mode mMode; //过滤模式
    private String mStartDate; //查询日期开始日期
    private String mEndDate;//查询结束日期
    private String mEventId;
    private String mTag;
    File mSourceFile; //源文件
    File mDesFile; //输出文件
    private Analyzer analyzer;
    private Response response;

    public Director(Builder builder) {
        this.mStartDate = builder.mStartDate;
        this.mEndDate = builder.mEndDate;
        this.mEventId = builder.mEventId;
        this.mTag = builder.mTag;
        this.mMode = builder.mMode;
        this.mDesFile = builder.mDesFile;
        this.mSourceFile = builder.mSourceFile;
        FileAnalyzer fileAnalyzer = new FileAnalyzer(mSourceFile);
        this.analyzer = fileAnalyzer;
        response = new Response();

        JsonAnalyzer jsonAnalyzer = new JsonAnalyzer();
        fileAnalyzer.nextAnalyzer(jsonAnalyzer);

        FilterAnalyzer processAnalyzer = new FilterAnalyzer(mTag, mEventId,mStartDate,mEndDate);
        jsonAnalyzer.nextAnalyzer(processAnalyzer);

        if (mDesFile == null) {
            defaultDesFile();
        }
        OutputAnalyzer outputAnalyzer = new OutputAnalyzer(mDesFile, mMode);
        processAnalyzer.nextAnalyzer(outputAnalyzer);
    }

    private void defaultDesFile() {
        if (Mode.EVNENTID == mMode) {
            String name = mSourceFile.getName();
            String formatName = name.substring(0, name.lastIndexOf(".")) + "_" + mEventId + ".html";
            mDesFile = new File(mSourceFile.getParent(), formatName);
        } else if (Mode.TAG == mMode) {
            String name = mSourceFile.getName();
            String formatName = name.substring(0, name.lastIndexOf(".")) + "_" + mTag + ".html";
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
        Mode mMode; //过滤模式
        File mSourceFile; //源文件
        File mDesFile; //输出文件
        String mStartDate; //查询日期开始日期
        String mEndDate;//查询结束日期
        String mEventId;
        String mTag;

        public Builder filterMode(Mode mode) {
            this.mMode = mode;
            return this;
        }

        public Builder sourcefile(File sourceFile) {
            this.mSourceFile = sourceFile;
            return this;
        }

        public Builder sourcefile(String sourceFile) {
            this.mSourceFile = new File(sourceFile);
            return this;
        }

        public Builder destfile(File destFile) {
            this.mDesFile = destFile;
            return this;
        }

        public Builder destfile(String destFile) {
            this.mDesFile = new File(destFile);
            return this;
        }

        public Builder setTag(String tag) {
            this.mTag = tag;
            return this;
        }

        public Builder setEventId(String eventId) {
            this.mEventId = eventId;
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

    public enum Mode {
        ALL, TAG, EVNENTID
    }

}
