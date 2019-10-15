package com.weishop.test.mvp.base;

public interface IPresenter<V extends IView> {

    public void attachView(V v);

    public void detachView(V v);

}
