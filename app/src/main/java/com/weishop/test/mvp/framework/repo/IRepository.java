package com.weishop.test.mvp.framework.repo;

/**
 * model层接口类
 */
public interface IRepository {


    interface DataCallBack<ResultValue> {

        /**
         * 接口获取成功
         *
         * @param resultValue
         */
        void onSuccess(ResultValue resultValue);

        /**
         * 接口获取失败
         *
         * @param failValue
         */
        void onFail(RequestFail failValue);

        /**
         * 取消请求接口
         */
        void onCancel();
    }
}
