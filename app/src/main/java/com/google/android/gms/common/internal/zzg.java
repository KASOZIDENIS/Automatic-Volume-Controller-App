package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class zzg implements DialogInterface.OnClickListener {
    private final Intent mIntent;
    private final Fragment zzTb;
    private final int zzTc;
    private final Activity zzpf;

    public zzg(Activity activity, Intent intent, int i) {
        this.zzpf = activity;
        this.zzTb = null;
        this.mIntent = intent;
        this.zzTc = i;
    }

    public zzg(Fragment fragment, Intent intent, int i) {
        this.zzpf = null;
        this.zzTb = fragment;
        this.mIntent = intent;
        this.zzTc = i;
    }

    public void onClick(DialogInterface dialog, int which) {
        try {
            if (this.mIntent != null && this.zzTb != null) {
                this.zzTb.startActivityForResult(this.mIntent, this.zzTc);
            } else if (this.mIntent != null) {
                this.zzpf.startActivityForResult(this.mIntent, this.zzTc);
            }
            dialog.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
