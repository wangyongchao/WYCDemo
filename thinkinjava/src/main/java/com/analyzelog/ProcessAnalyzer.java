package com.analyzelog;

import com.analyzelog.entity.LogBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 加工数据
 */
public class ProcessAnalyzer implements Analyzer {
    private List<LogBean> mLogBeans = new ArrayList<>();
    private List<LogBean> mTagLogBeans = new ArrayList<>();
    private List<LogBean> mEventIdLogBeans = new ArrayList<>();
    private String mTAG;
    private String mEventId;//直播中eventid都是live_debug_message，别的业务都是eventId=tag


    public ProcessAnalyzer(List<LogBean> logBeans) {
        this.mLogBeans.addAll(logBeans);
    }

    public List<LogBean> getLogBeans() {
        return mLogBeans;
    }


    public List<LogBean> getTagLogBeans() {
        return mTagLogBeans;
    }

    public List<LogBean> getEventIdLogBeans() {
        return mEventIdLogBeans;
    }

    @Override
    public void startAnalyzer() {
        System.out.println("开始处理" + mLogBeans.size() + "条bean中的数据");
        sortByDate(mLogBeans);
        if (mTAG != null) {
            filterByTag();
        }

        if (mEventId != null) {
            filterByEventId();
        }
    }

    public String getTAG() {
        return mTAG;
    }

    public void setTAG(String mTAG) {
        this.mTAG = mTAG;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String mEventId) {
        this.mEventId = mEventId;
    }

    /**
     * 根据tag过滤
     */
    private void filterByTag() {
        mTagLogBeans.clear();
        for (int i = 0; i < mLogBeans.size(); i++) {
            LogBean logBean = mLogBeans.get(i);
            String tag = logBean.getData().getTag();
            if (mTAG != null && mTAG.equals(tag)) {
                mTagLogBeans.add(logBean);
            }
        }

    }

    /**
     * 根据eventId过滤
     */
    private void filterByEventId() {
        mEventIdLogBeans.clear();
        for (int i = 0; i < mLogBeans.size(); i++) {
            LogBean logBean = mLogBeans.get(i);
            String eventid = logBean.getData().getEventid();
            if (mEventId != null && mEventId.equals(eventid)) {
                mEventIdLogBeans.add(logBean);
            }
        }


    }

    /**
     * 根据日期排序
     */
    private void sortByDate(List<LogBean> logBeans) {
        Collections.sort(logBeans, new DateComparator());
    }


}
