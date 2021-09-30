package com.analyzelog;

import com.analyzelog.entity.LogBean;
import com.analyzelog.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * 加工数据
 */
public class FilterAnalyzer extends Analyzer {
    private final Map<String, String> mParams;
    private List<LogBean> mLogBeans;
    private String mEventName;
    private String mStartDate; //查询日期开始日期
    private String mEndDate;//查询结束日期
    private Director.FilterMode mMode;
    private int itemCount = 1000;

    public FilterAnalyzer(Director.FilterMode mode, String eventName, Map<String, String> params, String startDate, String endDate) {
        this.mEventName = eventName;
        this.mParams = params;
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mMode = mode;
    }

    @Override
    public void startAnalyzer(Response response) {
        mLogBeans = response.getLogBeans();
        sortByDate(mLogBeans);
        System.out.println("start FilterAnalyzer");
        if (mMode == Director.FilterMode.ALL) {


        } else if (mMode == Director.FilterMode.CRASH) {
            List<LogBean> logBeans = filterByCrash();
            response.setCrashLogBeans(logBeans);
            System.out.println("FilterAnalyzer end="+logBeans.size());

        } else if (mMode == Director.FilterMode.ANR) {

        }
    }

    /**
     * 根据日期范围过滤
     */
    private void filterByDateRange() {
        Iterator<LogBean> iterator = mLogBeans.iterator();
        while (iterator.hasNext()) {
            LogBean logBean = iterator.next();
            String strDate = null;
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
     * 0,1,2
     * 筛选crash 1,2,3,4,5
     */
    private List<LogBean> filterByCrash() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < mLogBeans.size(); i++) {
            LogBean logBean = mLogBeans.get(i);
            if ("app_exception".equals(logBean.event_name)) {
                result.add(i);
                break;
            }
        }
        if (!result.isEmpty()) {
            int size = result.size();
            Integer integer = result.get(0);
            if(integer-100<0){
                integer=0;
            }else {
                integer=integer-100;
            }
            System.out.println("filterByCrash ="+integer);
            return mLogBeans.subList(integer, mLogBeans.size());
        }
        return null;
    }


    /**
     * 根据日期排序
     */
    private void sortByDate(List<LogBean> logBeans) {
        Collections.sort(logBeans, new DateComparator());
    }


}
