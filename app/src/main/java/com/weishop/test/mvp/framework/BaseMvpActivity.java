
package com.weishop.test.mvp.framework;

import android.os.Bundle;

import com.weishop.test.mvp.framework.utils.Preconditions;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 需要使用mvp的可以继承此类，activity本身充当view角色
 *
 * @param <P>
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends AppCompatActivity implements IView<P> {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        Preconditions.checkNotNull(mPresenter);
        getLifecycle().addObserver(mPresenter);
        mPresenter.attachView(this);

    }

    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
        getLifecycle().removeObserver(mPresenter);
    }
}
