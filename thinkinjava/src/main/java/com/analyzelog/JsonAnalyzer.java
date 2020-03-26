package com.analyzelog;

import com.alibaba.fastjson.JSON;
import com.analyzelog.entity.LogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析json数据
 */
public class JsonAnalyzer implements Analyzer {
    private List<String> mJonsStrings = new ArrayList<>();
    private List<LogBean> logBeans = new ArrayList<>();

    public JsonAnalyzer(List<String> strings) {
        this.mJonsStrings.addAll(strings);
    }

    @Override
    public void startAnalyzer() {
        System.out.println("开始把" + mJonsStrings.size() + "条json数据生成bean");
        logBeans.clear();
        for (int i = 0; i < mJonsStrings.size(); i++) {
            String jsonString = mJonsStrings.get(i);
            LogBean logBean = JSON.parseObject(jsonString, LogBean.class);
            logBean.setOriginalJsonStr(jsonString);
            System.out.println(logBean.toString());
            logBeans.add(logBean);
        }

    }

    public List<LogBean> getLogBeans() {
        return logBeans;
    }

}
