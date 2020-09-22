
package com.weishop.test.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.weishop.test.R;
import com.weishop.test.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class EventConflictActivity extends Activity implements View.OnClickListener {
    private ListView mListView;
    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_conflict);
//        ScrollView scrollView = findViewById(R.id.scrollview);
//        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX,
//                                       int oldScrollY) {
//                LogUtils.d("scrollView scrollY="+scrollY+",oldScrollY="+oldScrollY);
//            }
//        });
        mListView = (ListView) findViewById(R.id.listview);

        String[] data = getResources().getStringArray(R.array.names);

        initData();

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mData);
        mListView.setAdapter(arrayAdapter);
    }


    @Override
    public void onClick(View v) {


    }

    private void initData() {
        for (int i = 0; i < 200; i++) {
            mData.add(
                    "itemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitemitem"
                            + i);
        }

    }


}
