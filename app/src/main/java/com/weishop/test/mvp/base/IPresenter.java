package com.weishop.test.mvp.base;

public interface IPresenter<V extends IView> {

    void attachView(V v);

    void detachView(V v);

    void onResume();

    void onPause();

}
