package com.mightyworksinc.androidapp.mightyvolume;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class Version extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.version);
        TextView text_AppVersion = (TextView) findViewById(R.id.text_appversion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((int) R.string.app_menu_version);
        actionBar.setDisplayOptions(12);
        try {
            text_AppVersion.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }
}
