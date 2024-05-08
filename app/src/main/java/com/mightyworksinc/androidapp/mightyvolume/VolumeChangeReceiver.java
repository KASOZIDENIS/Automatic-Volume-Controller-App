package com.mightyworksinc.androidapp.mightyvolume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class VolumeChangeReceiver extends BroadcastReceiver {
    final int MEDIATYPEVALUE = 3;
    SharedPreferences.Editor minMediaGain;
    SharedPreferences minMediaValue;

    public void onReceive(Context context, Intent intent) {
        this.minMediaValue = context.getSharedPreferences("minmediagain", 0);
        this.minMediaGain = this.minMediaValue.edit();
        if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3) {
            int newVolume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
            int oldVolume = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);
            if (MightyService.isAVCRunning() && newVolume != MightyService.getServiceMediaGain()) {
                AutoVolumeControl.setMinMediaVolumeLevel(AutoVolumeControl.getMinMediaVolumeLevel() + (newVolume - oldVolume));
                this.minMediaGain.putInt("mingain", AutoVolumeControl.getMinMediaVolumeLevel());
                this.minMediaGain.commit();
            }
        }
    }
}
