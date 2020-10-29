package com.weishop.test.mvp.framework.repo;

/**
 * 解析请求结果数据基类
 * @param <T>
 */
public interface IParser<T> {
    T parse(String result) throws Exception;
}
