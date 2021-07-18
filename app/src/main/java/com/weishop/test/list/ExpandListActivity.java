
package com.weishop.test.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.weishop.test.R;
import com.weishop.test.list.data.Group;
import com.weishop.test.list.draglistview.ListviewActivity;
import com.weishop.test.list.recycler.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

public class ExpandListActivity extends Activity implements View.OnClickListener {

    private ExpandableListView expandableListView;
    List<Group> groups = new ArrayList<>();
    private ExpandAdapter expandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_expand);
        findViewById(R.id.start_listview).setOnClickListener(this);
        initData();
        expandableListView = findViewById(R.id.expandlist);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        expandAdapter = new ExpandAdapter(groups, this);


    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Group group = new Group();
            group.name = i + "";
            List<String> childs = new ArrayList<>();
            group.childs = childs;
            for (int j = 0; j < 5; j++) {
                childs.add(i + "-" + j);
            }
            groups.add(group);
        }

    }


    @Override
    public void onClick(View v) {
        expandableListView.setAdapter(expandAdapter);
        for (int i=0;i<groups.size();i++){
            expandableListView.expandGroup(i);
        }
    }
}
