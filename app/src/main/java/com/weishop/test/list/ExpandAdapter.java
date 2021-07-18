package com.weishop.test.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.weishop.test.list.data.Group;

import java.util.ArrayList;
import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {
    List<Group> groups = new ArrayList<>();
    Context context;

    public ExpandAdapter(List<Group> groups, Context context) {
        this.groups = groups;
        this.context = context;
        this.groups.addAll(groups);
    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).childs.size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, android.R.layout.simple_expandable_list_item_1, null);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(getGroup(groupPosition).name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, android.R.layout.simple_expandable_list_item_1, null);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(getChild(groupPosition,childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
