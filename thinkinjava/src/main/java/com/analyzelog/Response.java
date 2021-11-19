package com.analyzelog;

import com.analyzelog.entity.LogBean;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private String jsonString;
    private List<LogBean> logBeans;
    private List<LogBean> mCrashLogBeans = new ArrayList<>();
    private List<LogBean> mEventIdLogBeans = new ArrayList<>();

    public void setJsonStrings(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getJsonStrings() {
        return jsonString;
    }

    public void setLogBeans(List<LogBean> logBeans) {
        this.logBeans = logBeans;
    }

    public List<LogBean> getLogBeans() {
        return logBeans;
    }


    public List<LogBean> getCrashLogBeans() {
        return mCrashLogBeans;
    }

    public void setCrashLogBeans(List<LogBean> crashLogBeans) {
        this.mCrashLogBeans = crashLogBeans;
    }

    public List<LogBean> getEventIdLogBeans() {
        return mEventIdLogBeans;
    }

    public void setEventIdLogBeans(List<LogBean> eventIdLogBeans) {
        this.mEventIdLogBeans = eventIdLogBeans;
    }

    public void release() {

        if (logBeans != null) {
            logBeans.clear();
            logBeans = null;
        }

        if (mCrashLogBeans != null) {
            mCrashLogBeans.clear();
            mCrashLogBeans = null;
        }
        if (mEventIdLogBeans != null) {
            mEventIdLogBeans.clear();
            mEventIdLogBeans = null;
        }
    }
}
