package com.weishop.test.mvp.framework.repo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 把json字符串解析为对象
 */
public class GsonParser<T> implements IParser {

    protected Class<T> mClass;

    public GsonParser(Class<T> aClass) {
        this.mClass = aClass;
    }

    @Override
    public T parse(String result) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(result, mClass);
    }
}
