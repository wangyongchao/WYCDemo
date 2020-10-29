package com.weishop.test.mvp.framework;

import android.os.Bundle;

import com.weishop.test.mvp.framework.utils.Preconditions;

public abstract class BaseMvpFragment<P extends IPresenter> extends BaseFragment implements IView<P> {

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        Preconditions.checkNotNull(mPresenter);
        mPresenter.attachView(this);

    }

    protected abstract P createPresenter();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }


}
