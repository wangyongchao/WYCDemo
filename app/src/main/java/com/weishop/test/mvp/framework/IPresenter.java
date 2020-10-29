package com.weishop.test.mvp.framework;

import androidx.lifecycle.DefaultLifecycleObserver;

public interface IPresenter<V extends IView> extends DefaultLifecycleObserver {

    void attachView(V v);

    void detachView(V v);


}
