package com.weishop.test.mvp;

import com.weishop.test.list.ListData;
import com.weishop.test.mvp.base.IPresenter;
import com.weishop.test.mvp.base.IView;

import java.util.List;

public interface TestContract {

    interface View extends IView<Presenter> {

        void showLoading();

        void hideLoading();

        void showData(List<ListData> data);

    }

    interface Presenter extends IPresenter<View> {

        void initData();

    }


}
