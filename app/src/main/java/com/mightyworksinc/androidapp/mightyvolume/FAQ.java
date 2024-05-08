package com.mightyworksinc.androidapp.mightyvolume;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;

public class FAQ extends ActionBarActivity {
    BaseExpandableAdapter mBaseExpandableAdapter = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_menu_faq);
        actionBar.setDisplayOptions(12);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.elv_list);
        String _FAQ1 = getString(R.string.faqtitle1);
        String _FAQ2 = getString(R.string.faqtitle2);
        String _FAQ3 = getString(R.string.faqtitle3);
        String _FAQ4 = getString(R.string.faqtitle4);
        String _FAQ5 = getString(R.string.faqtitle5);
        String _FAQ6 = getString(R.string.faqtitle6);
        ArrayList<String> groupList = new ArrayList<>();
        ArrayList<ArrayList<String>> childList = new ArrayList<>();
        ArrayList<String> childListContent1 = new ArrayList<>();
        ArrayList<String> childListContent2 = new ArrayList<>();
        ArrayList<String> childListContent3 = new ArrayList<>();
        ArrayList<String> childListContent4 = new ArrayList<>();
        ArrayList<String> childListContent5 = new ArrayList<>();
        ArrayList<String> childListContent6 = new ArrayList<>();
        groupList.add(_FAQ1);
        groupList.add(_FAQ2);
        groupList.add(_FAQ3);
        groupList.add(_FAQ4);
        groupList.add(_FAQ5);
        groupList.add(_FAQ6);
        childListContent1.add("");
        childListContent2.add("");
        childListContent3.add("");
        childListContent4.add("");
        childListContent5.add("");
        childListContent6.add("");
        childList.add(childListContent1);
        childList.add(childListContent2);
        childList.add(childListContent3);
        childList.add(childListContent4);
        childList.add(childListContent5);
        childList.add(childListContent6);
        this.mBaseExpandableAdapter = new BaseExpandableAdapter(this, groupList, childList);
        this.mBaseExpandableAdapter.notifyDataSetChanged();
        listView.setAdapter(this.mBaseExpandableAdapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            public void onGroupCollapse(int groupPosition) {
            }
        });
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
            }
        });
    }
}
