package com.weishop.test.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> implements IPresenter<V> {

    protected Reference<V> weakReference;


    @Override
    public void attachView(V v) {
        weakReference = new WeakReference(v);
    }

    protected V getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }


    @Override
    public void detachView(V v) {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }

    }


    protected void onStart() {
    }

    protected void onRestart() {
    }

    protected void onResume() {
    }

    protected void onPause() {
    }

    protected void onStop() {
    }


}
