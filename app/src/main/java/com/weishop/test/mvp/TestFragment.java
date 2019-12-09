package com.weishop.test.mvp;

import com.weishop.test.list.ListData;
import com.weishop.test.mvp.base.BaseMvpFragment;

import java.util.List;

public class TestFragment extends BaseMvpFragment<TestContract.Presenter> implements TestContract.View {

    @Override
    protected TestContract.Presenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(List<ListData> data) {

    }
}
