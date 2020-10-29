package com.weishop.test.mvp.framework.repo;

/**
 * Created by wangyongchao on 2019/12/26 16:28
 * 回调给业务层接口请求失败
 */
public class RequestFail {
    private String erroMsg;//错误信息

    public void setErroMsg(String erroMsg) {
        this.erroMsg = erroMsg;
    }

    public String getErroMsg() {
        return erroMsg;
    }
}
