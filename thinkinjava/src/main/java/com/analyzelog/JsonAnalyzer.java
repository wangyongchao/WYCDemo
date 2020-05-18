package com.analyzelog;

import com.alibaba.fastjson.JSON;
import com.analyzelog.entity.LogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析json数据
 */
public class JsonAnalyzer extends Analyzer {


    @Override
    public void startAnalyzer(Response response) {
        ArrayList<String> jsonStrings = response.getJsonStrings();
        System.out.println("start JsonAnalyzer size=" + jsonStrings.size() + "change bean");
        List<LogBean> logBeans = new ArrayList<>(jsonStrings.size());
        for (int i = 0; i < jsonStrings.size(); i++) {
            String jsonString;
            try {
                jsonString = jsonStrings.get(i);
                System.out.println(jsonString);
                LogBean logBean = JSON.parseObject(jsonString, LogBean.class);
                logBean.setOriginalJsonStr(jsonString);
                logBeans.add(logBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setLogBeans(logBeans);
        jsonStrings.clear();
        System.out.println("end JsonAnalyzer size=" + logBeans.size());

    }

}
