package com.analyzelog;

import com.alibaba.fastjson.JSON;
import com.analyzelog.entity.LogBean;

import java.util.List;

/**
 * 解析json数据
 */
public class JsonAnalyzer extends Analyzer {


    @Override
    public void startAnalyzer(Response response) {
        String jsonString = response.getJsonStrings();
        System.out.println("start JsonAnalyzer");
        List<LogBean> logBeans = JSON.parseArray(jsonString, LogBean.class);
        response.setLogBeans(logBeans);
        System.out.println("end JsonAnalyzer size=" + logBeans.size());

    }

}
