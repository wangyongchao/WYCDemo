package com.weishop.test.mvp.framework.repo;


import com.weishop.test.mvp.framework.utils.ReflectTypeUtils;

import java.lang.reflect.Type;

/**
 * 网络请求回调给业务层
 */
public interface CallBack<T> {

    interface CommonCallback<T> {

        /**
         * 网络请求成功回调
         *
         * @param t
         */
        void onSuccess(T t);

        /**
         * 网络请求失败
         *
         * @param msg
         */
        void onFailure(String msg);

        /**
         * 取消请求
         */
        void onCancel();
    }

    class DefaultCommonCallback<T> implements CommonCallback<T> {

        @Override
        public void onSuccess(T t) {

        }

        @Override
        public void onFailure(String msg) {

        }

        @Override
        public void onCancel() {

        }
    }

    interface TypedCommonCallback<ResultType> extends CommonCallback<ResultType> {
        Type getResultType();
    }

    /**
     * 由于泛型的擦除，需要使用内部类才能获取到想要的数据类型
     *
     * @param <T>
     */
    class DefaultTypedCommonCallback<T> implements TypedCommonCallback<T> {

        protected CommonCallback<T> mCallback;

        public DefaultTypedCommonCallback(CommonCallback<T> callback) {
            this.mCallback = callback;
        }


        @Override
        public Type getResultType() {
            return mCallback == null ? String.class :
                    ReflectTypeUtils.getParameterizedType(mCallback.getClass(),
                            CommonCallback.class, 0);
        }

        @Override
        public void onSuccess(T result) {
            onRequestSuccess(result, mCallback);
        }

        @Override
        public void onFailure(String msg) {
            onRequestFail(msg, mCallback);
        }

        @Override
        public void onCancel() {
            onRequestCancel(mCallback);
        }

        private void onRequestSuccess(T result, CommonCallback callback) {

            if (callback == null) {
                return;
            }

            callback.onSuccess(result);
        }

        private static void onRequestFail(String erroMsg, CommonCallback callback) {

            if (callback == null) {
                return;
            }

            callback.onFailure(erroMsg);
        }

        private static void onRequestCancel(CommonCallback callback) {

            if (callback == null) {
                return;
            }

            callback.onCancel();
        }
    }


}
