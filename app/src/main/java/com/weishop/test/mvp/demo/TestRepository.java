package com.weishop.test.mvp.demo;

import android.content.Context;

import com.weishop.test.mvp.framework.repo.BaseRepository;

public class TestRepository extends BaseRepository implements TestContract.Repository{

    public TestRepository(Context context) {
        super(context);
    }


    @Override
    public void loadData(DataCallBack callback, String request) {

    }
}
