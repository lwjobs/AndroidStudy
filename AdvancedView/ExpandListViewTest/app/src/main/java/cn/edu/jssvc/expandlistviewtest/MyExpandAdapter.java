package cn.edu.jssvc.expandlistviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyExpandAdapter extends BaseExpandableListAdapter {
    private String[] groupName;
    private String[][] childName;
    private int[] groupPic;
    private int[][] childPic;

    LayoutInflater inflater;

    public MyExpandAdapter(Context context, String[] groupName, String[][] childName, int[] groupPic, int[][] childPic) {
        this.inflater = LayoutInflater.from(context);
        this.groupName = groupName;
        this.childName = childName;
        this.groupPic = groupPic;
        this.childPic = childPic;
    }

    @Override
    public int getGroupCount() {
        return groupName.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childName[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupName[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childName[groupPosition][childPosition];
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
        convertView = inflater.inflate(R.layout.group_item, null);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(getGroup(groupPosition).toString());
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(groupPic[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.child_item, null);
        TextView nickTextView = convertView.findViewById(R.id.textView2);
        nickTextView.setText(getChild(groupPosition, childPosition).toString());
        ImageView imageView = convertView.findViewById(R.id.imageView2);
        imageView.setImageResource(childPic[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
