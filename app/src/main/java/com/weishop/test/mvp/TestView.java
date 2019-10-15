package com.weishop.test.mvp;

import com.weishop.test.list.ListData;
import com.weishop.test.mvp.base.BaseView;

import java.util.List;

public interface TestView extends BaseView {
    void test(List<ListData> data);
}
