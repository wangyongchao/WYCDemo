package com.weishop.test.mvp.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.util.Preconditions;

public abstract class BaseMvpFragment<P extends IPresenter> extends Fragment implements IView<P> {

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
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }


}
