
package com.weishop.test.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.list.draglistview.ListviewActivity;
import com.weishop.test.list.recycler.RecyclerViewActivity;

public class ListTestActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_listtest);

        findViewById(R.id.start_listview).setOnClickListener(this);
        findViewById(R.id.start_recyclerview).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_recyclerview) {
            startActivity(new Intent(this,RecyclerViewActivity.class));

        } else if (id == R.id.start_listview) {
            startActivity(new Intent(this,ListviewActivity.class));
        }

    }
}
