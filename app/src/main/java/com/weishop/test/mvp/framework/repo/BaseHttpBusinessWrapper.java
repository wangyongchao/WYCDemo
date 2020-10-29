package com.weishop.test.mvp.framework.repo;

import android.content.Context;

import java.util.Map;

/**
 * basehttp不是抽象的，只能采用组合的方式扩展其功能，
 * 做到隔离解耦,后续需要改造原有网络层的封装
 */
public class BaseHttpBusinessWrapper implements IHttpRequest {

    public BaseHttpBusinessWrapper(Context context) {

    }


    /**
     * 普通的HttpPost请求
     *
     * @param url               地址
     * @param httpRequestParams 传参
     * @return 请求实例
     */
    @Override
    public void sendPost(String url, Map<String, String> httpRequestParams,
                         CallBack.CommonCallback callBack) {

    }


}
