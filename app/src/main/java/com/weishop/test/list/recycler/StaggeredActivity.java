
package com.weishop.test.list.recycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.weishop.test.R;

public class StaggeredActivity extends Activity implements View.OnClickListener {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        mRecyclerView = findViewById(R.id.staggered_view);


    }

    @Override
    public void onClick(View v) {

    }
}
