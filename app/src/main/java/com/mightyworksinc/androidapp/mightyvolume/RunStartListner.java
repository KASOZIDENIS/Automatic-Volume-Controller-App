package com.mightyworksinc.androidapp.mightyvolume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mightyworksinc.androidapp.mightyvolume.MightyworksAnalyticsApplication;
import timber.log.Timber;

public class RunStartListner extends BroadcastReceiver {
    private int _NotiAvcStartCount = 0;
    SharedPreferences.Editor _NotiAvcStartCountEditor;
    SharedPreferences _NotiAvcStartCountValue;

    public void onReceive(Context context, Intent intent) {
        Tracker mTracker = ((MightyworksAnalyticsApplication) context.getApplicationContext()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
        this._NotiAvcStartCountValue = MightyService.mMightyService.getSharedPreferences("count", 0);
        this._NotiAvcStartCountEditor = this._NotiAvcStartCountValue.edit();
        this._NotiAvcStartCount = this._NotiAvcStartCountValue.getInt("noti_avcstart", this._NotiAvcStartCount);
        this._NotiAvcStartCount++;
        Timber.d("_NotiAvcStartCount = " + this._NotiAvcStartCount, new Object[0]);
        this._NotiAvcStartCountEditor.putInt("noti_avcstart", this._NotiAvcStartCount);
        this._NotiAvcStartCountEditor.commit();
        mTracker.send(new HitBuilders.EventBuilder().setCategory("User Action").setAction("AVC Start").setLabel("Notification").setValue(1).build());
        Timber.d("RunStartListner", new Object[0]);
        MightyService.autoRunStart();
    }
}
