package com.analyzelog;

import com.analyzelog.entity.LogBean;
import com.analyzelog.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 加工数据
 */
public class FilterAnalyzer extends Analyzer {
    private List<LogBean> mLogBeans;
    private List<LogBean> mTagLogBeans = new ArrayList<>();
    private List<LogBean> mEventIdLogBeans = new ArrayList<>();
    private String mTAG;
    private String mEventId;//直播中eventid都是live_debug_message，别的业务都是eventId=tag
    private String mStartDate; //查询日期开始日期
    private String mEndDate;//查询结束日期

    public FilterAnalyzer(String tag, String eventId, String startDate, String endDate) {
        this.mTAG = tag;
        this.mEventId = eventId;
        this.mStartDate = startDate;
        this.mEndDate = endDate;
    }

    @Override
    public void startAnalyzer(Response response) {
        mLogBeans = response.getLogBeans();
        System.out.println("start FilterAnalyzer size=" + mLogBeans.size());
        filterByDateRange();
        sortByDate(mLogBeans);
        if (mTAG != null) {
            filterByTag();
        }

        if (mEventId != null) {
            filterByEventId();
        }
        response.setTagLogBeans(mTagLogBeans);
        response.setEventIdLogBeans(mEventIdLogBeans);
    }

    /**
     * 根据日期范围过滤
     */
    private void filterByDateRange() {
        Iterator<LogBean> iterator = mLogBeans.iterator();
        while (iterator.hasNext()) {
            LogBean logBean = iterator.next();
            String strDate = logBean.getData().getDate();
            Date date = Utils.formatDate(strDate);
            if (mStartDate != null && mEndDate != null) {
                Date startDate = Utils.formatDate(mStartDate);
                Date endDate = Utils.formatDate(mEndDate);
                if (date.before(startDate) || date.after(endDate)) {
                    iterator.remove();
                }
            } else if (mStartDate != null) {
                Date startDate = Utils.formatDate(mStartDate);
                if (date.before(startDate)) {
                    iterator.remove();
                }
            } else if (mEndDate != null) {
                Date endDate = Utils.formatDate(mEndDate);
                if (date.after(endDate)) {
                    iterator.remove();
                }
            }
        }

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
