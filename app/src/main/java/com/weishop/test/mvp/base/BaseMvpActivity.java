
package com.weishop.test.mvp.base;

import android.os.Bundle;
import android.support.v4.util.Preconditions;

/**
 * 需要使用mvp的可以继承此类，activity本身充当view角色
 *
 * @param <P>
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity implements IView<P> {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        Preconditions.checkNotNull(mPresenter);
        mPresenter.attachView(this);

    }

    protected abstract P createPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(this);
    }
}
