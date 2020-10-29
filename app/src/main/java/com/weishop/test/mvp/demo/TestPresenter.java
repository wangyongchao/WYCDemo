package com.weishop.test.mvp.demo;

import android.content.Context;

import com.weishop.test.mvp.framework.BasePresenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

public class TestPresenter extends BasePresenter<TestView, TestRepository> implements TestContract.Repository{


    public TestPresenter(Context context) {
        super(context);
    }

    @Override
    protected TestRepository createRepository() {
        return new TestRepository(mContext);
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void loadData(DataCallBack callback, String request) {
        mRepository.loadData(callback,request);

    }
}
