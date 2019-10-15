package com.weishop.test.mvp;

import com.weishop.test.list.ListData;
import com.weishop.test.mvp.base.BasePresenter;
import com.weishop.test.util.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class TestPresenter extends BasePresenter<TestView> {
    private List<ListData> mData = new ArrayList<ListData>();

    public void testData() {
        initData();
    }

    private void initData() {
        for (int i = 0; i < TestUtils.imageUrls.length; i++) {
            ListData listData = new ListData("item" + i, TestUtils.imageUrls[i]);
            mData.add(listData);
        }
        getView().test(mData);

    }

}
