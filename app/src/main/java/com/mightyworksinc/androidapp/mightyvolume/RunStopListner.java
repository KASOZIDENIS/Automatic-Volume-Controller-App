package com.mightyworksinc.androidapp.mightyvolume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mightyworksinc.androidapp.mightyvolume.MightyworksAnalyticsApplication;
import timber.log.Timber;

public class RunStopListner extends BroadcastReceiver {
    private int _NotiAvcStopCount = 0;
    SharedPreferences.Editor _NotiAvcStopCountEditor;
    SharedPreferences _NotiAvcStopCountValue;

    public void onReceive(Context context, Intent intent) {
        Tracker mTracker = ((MightyworksAnalyticsApplication) context.getApplicationContext()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
        this._NotiAvcStopCountValue = MightyService.mMightyService.getSharedPreferences("count", 0);
        this._NotiAvcStopCountEditor = this._NotiAvcStopCountValue.edit();
        this._NotiAvcStopCount = this._NotiAvcStopCountValue.getInt("noti_avcstop", this._NotiAvcStopCount);
        this._NotiAvcStopCount++;
        Timber.d("_NotiAvcStopCount = " + this._NotiAvcStopCount, new Object[0]);
        this._NotiAvcStopCountEditor.putInt("noti_avcstop", this._NotiAvcStopCount);
        this._NotiAvcStopCountEditor.commit();
        mTracker.send(new HitBuilders.EventBuilder().setCategory("User Action").setAction("AVC Stop").setLabel("Notification").setValue(1).build());
        Timber.d("RunSopListner", new Object[0]);
        MightyService.autoRunStop();
    }
}
