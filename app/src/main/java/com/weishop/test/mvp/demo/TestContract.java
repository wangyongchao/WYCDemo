package com.weishop.test.mvp.demo;


import com.weishop.test.mvp.framework.IPresenter;
import com.weishop.test.mvp.framework.IView;
import com.weishop.test.mvp.framework.repo.IRepository;

public interface TestContract {

    interface View extends IView<Presenter> {

        /**
         * 显示loading
         */
        void startLoading();

        /**
         * 隐藏loading
         */
        void stopLoading();


    }

    interface Presenter extends IPresenter<View> {

        /**
         * 加载任务列表
         *
         * @param direction 1：下拉刷新 2:上拉加载
         */
        void loadData(int direction, boolean showLoading);

        /**
         * 加载未完成任务
         */
        void switchTasks();

        /**
         * 点击某个任务
         *
         * @param postion
         */
        void onTaskClick(int postion);
    }


    interface Repository extends IRepository {
        /**
         *
         * @param callback
         * @param request
         */
        void loadData(IRepository.DataCallBack callback,
                       String request);
    }
}
