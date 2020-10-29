package com.weishop.test.mvp.framework.repo;


import java.util.Map;

public interface IHttpRequest {
    /**
     * 发送post请求
     *
     * @param url
     * @param httpRequestParams
     * @param callBack
     */
    void sendPost(String url, final Map<String,String> httpRequestParams,
                  CallBack.CommonCallback callBack);
}
