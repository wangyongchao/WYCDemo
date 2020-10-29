package com.weishop.test.mvp.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.weishop.test.R;
import com.weishop.test.mvp.framework.BaseMvpActivity;
import com.weishop.test.mvp.framework.repo.IRepository;
import com.weishop.test.mvp.framework.repo.RequestFail;

public class MvpTestActivity extends BaseMvpActivity<TestPresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        Button getdata = findViewById(R.id.getData);
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    private void loadData() {
        mPresenter.loadData(new IRepository.DataCallBack() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onFail(RequestFail failValue) {

            }

            @Override
            public void onCancel() {

            }
        }, "");
    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(this);
    }


}
