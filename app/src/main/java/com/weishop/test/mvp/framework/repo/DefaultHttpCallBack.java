package com.weishop.test.mvp.framework.repo;


import com.weishop.test.mvp.framework.utils.ReflectTypeUtils;

import java.lang.reflect.Type;

public class DefaultHttpCallBack {
    private CallBack.CommonCallback callback;
    private Type resultType;

    public DefaultHttpCallBack(CallBack.CommonCallback commonCallback) {
        this.callback = commonCallback;
        if (callback instanceof CallBack.TypedCommonCallback) {
            resultType = ((CallBack.TypedCommonCallback) callback).getResultType();
        } else {
            resultType = ReflectTypeUtils.getParameterizedType(callback.getClass(),
                    CallBack.CommonCallback.class, 0);
        }
    }


}