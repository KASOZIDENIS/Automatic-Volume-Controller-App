package com.mightyworksinc.androidapp.mightyvolume;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class BaseExpandableAdapter extends BaseExpandableListAdapter {
    String _FAQ1 = MightyService.mMightyService.getString(R.string.faq1);
    String _FAQ2 = MightyService.mMightyService.getString(R.string.faq2);
    String _FAQ3 = MightyService.mMightyService.getString(R.string.faq3);
    String _FAQ4 = MightyService.mMightyService.getString(R.string.faq4);
    String _FAQ5 = MightyService.mMightyService.getString(R.string.faq5);
    String _FAQ6 = MightyService.mMightyService.getString(R.string.faq6);
    private ArrayList<ArrayList<String>> childList = null;
    String color = "#212830";
    View conView;
    private ArrayList<String> groupList = null;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;

    public BaseExpandableAdapter(Context c, ArrayList<String> groupList2, ArrayList<ArrayList<String>> childList2) {
        this.inflater = LayoutInflater.from(c);
        this.groupList = groupList2;
        this.childList = childList2;
    }

    public String getGroup(int groupPosition) {
        return this.groupList.get(groupPosition);
    }

    public int getGroupCount() {
        return this.groupList.size();
    }

    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            this.viewHolder = new ViewHolder();
            v = this.inflater.inflate(R.layout.list_row, parent, false);
            this.viewHolder.tv_groupName = (TextView) v.findViewById(R.id.tv_group);
            this.viewHolder.iv_image = (ImageView) v.findViewById(R.id.iv_image);
            v.setTag(this.viewHolder);
        } else {
            this.viewHolder = (ViewHolder) v.getTag();
        }
        if (isExpanded) {
            this.viewHolder.iv_image.setImageResource(R.drawable.icon_arrow_bottom);
        } else {
            this.viewHolder.iv_image.setImageResource(R.drawable.icon_arrow_right);
        }
        this.viewHolder.tv_groupName.setText(getGroup(groupPosition));
        this.conView = convertView;
        return v;
    }

    public String getChild(int groupPosition, int childPosition) {
        return (String) this.childList.get(groupPosition).get(childPosition);
    }

    public int getChildrenCount(int groupPosition) {
        return this.childList.get(groupPosition).size();
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long) childPosition;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            this.viewHolder = new ViewHolder();
            v = this.inflater.inflate(R.layout.list_row, (ViewGroup) null);
            this.viewHolder.tv_childName = (TextView) v.findViewById(R.id.tv_child);
            v.setBackgroundColor(Color.parseColor(this.color));
            v.setTag(this.viewHolder);
        } else {
            this.viewHolder = (ViewHolder) v.getTag();
        }
        if (groupPosition == 0) {
            this.viewHolder.tv_childName.setText(Html.fromHtml(this._FAQ1));
        } else if (groupPosition == 1) {
            this.viewHolder.tv_childName.setText(Html.fromHtml(this._FAQ2));
        } else if (groupPosition == 2) {
            this.viewHolder.tv_childName.setText(Html.fromHtml(this._FAQ3));
        } else if (groupPosition == 3) {
            this.viewHolder.tv_childName.setText(Html.fromHtml(this._FAQ4));
        } else if (groupPosition == 4) {
            this.viewHolder.tv_childName.setText(Html.fromHtml(this._FAQ5));
        } else if (groupPosition == 5) {
            this.viewHolder.tv_childName.setText(Html.fromHtml(this._FAQ6));
        }
        return v;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolder {
        public ImageView iv_image;
        public TextView tv_childName;
        public TextView tv_groupName;

        ViewHolder() {
        }
    }
}
