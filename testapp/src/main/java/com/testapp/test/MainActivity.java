package com.testapp.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.testapp.test.Label;
import com.testapp.test.ListViewAdapter;
import com.testapp.test.activitycharacter.AActivity;
import com.testapp.test.handler.HandlerActivity;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ListView listView;
    ListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        initData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Label label = mAdapter.getData().get(position);
                clickType(label.getType());

            }
        });

    }

    private void clickType(int type) {
        switch (type) {
            case 1:
                startActivityTest();
                break;
            case 2:
                startHandlerTest();
                break;
            case 3:
                break;
            case 4:
                break;
        }


    }

    private void startHandlerTest() {
        startActivity(new Intent(this, HandlerActivity.class));

    }

    private void startActivityTest() {
        startActivity(new Intent(this, AActivity.class));
    }

    private void initData() {
        mAdapter = new ListViewAdapter(this);
        listView.setAdapter(mAdapter);

        ArrayList<Label> labels = new ArrayList<>();
        Label label = new Label(getResources().getString(R.string.start_activity_test), 1);
        labels.add(label);
        label = new Label(getResources().getString(R.string.handler_test), 2);
        labels.add(label);
        label = new Label(getResources().getString(R.string.view_touch_draw_test), 3);
        labels.add(label);

        mAdapter.setData(labels);


    }
}
