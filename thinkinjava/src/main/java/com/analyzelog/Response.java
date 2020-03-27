package com.analyzelog;

import com.analyzelog.entity.LogBean;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private ArrayList<String> jsonStrings;
    private List<LogBean> logBeans;
    private List<LogBean> mTagLogBeans = new ArrayList<>();
    private List<LogBean> mEventIdLogBeans = new ArrayList<>();

    public void setJsonStrings(ArrayList<String> jsonStrings) {
        this.jsonStrings = jsonStrings;
    }

    public ArrayList<String> getJsonStrings() {
        return jsonStrings;
    }

    public void setLogBeans(List<LogBean> logBeans) {
        this.logBeans = logBeans;
    }

    public List<LogBean> getLogBeans() {
        return logBeans;
    }


    public List<LogBean> getTagLogBeans() {
        return mTagLogBeans;
    }

    public void setTagLogBeans(List<LogBean> tagLogBeans) {
        this.mTagLogBeans = tagLogBeans;
    }

    public List<LogBean> getEventIdLogBeans() {
        return mEventIdLogBeans;
    }

    public void setEventIdLogBeans(List<LogBean> eventIdLogBeans) {
        this.mEventIdLogBeans = eventIdLogBeans;
    }

    public void release() {
        if (jsonStrings != null) {
            jsonStrings.clear();
            jsonStrings = null;
        }

        if (logBeans != null) {
            logBeans.clear();
            logBeans = null;
        }

        if (mTagLogBeans != null) {
            mTagLogBeans.clear();
            mTagLogBeans = null;
        }

        if (mEventIdLogBeans != null) {
            mEventIdLogBeans.clear();
            mEventIdLogBeans = null;
        }
    }
}
