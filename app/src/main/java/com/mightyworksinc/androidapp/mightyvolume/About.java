package com.mightyworksinc.androidapp.mightyvolume;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class About extends ActionBarActivity implements View.OnClickListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.about);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((int) R.string.app_menu_about);
        actionBar.setDisplayOptions(12);
        ((Button) findViewById(R.id.btn_homepage)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_moving)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_facebook)).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_homepage /*2131624003*/:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.mightyworks.co.kr/?m=1")));
                return;
            case R.id.btn_moving /*2131624007*/:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.mightyworksinc.androidapp.mightyrecorder")));
                return;
            case R.id.btn_facebook /*2131624009*/:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/thevolumeapp")));
                return;
            default:
                return;
        }
    }
}
