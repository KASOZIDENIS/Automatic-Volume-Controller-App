package com.mightyworksinc.androidapp.mightyvolume;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import timber.log.Timber;

public class StartupIntentReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            ComponentName comp = new ComponentName(context.getPackageName(), MightyService.class.getName());
            if (context.startService(new Intent().setComponent(comp)) == null) {
                Timber.e("Could not start service " + comp.toString(), new Object[0]);
            }
        }
    }
}
