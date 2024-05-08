package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzk {
    private static final Object zzTK = new Object();
    private static zzk zzTL;

    public static zzk zzU(Context context) {
        synchronized (zzTK) {
            if (zzTL == null) {
                zzTL = new zzl(context.getApplicationContext());
            }
        }
        return zzTL;
    }

    public abstract boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract boolean zza(String str, ServiceConnection serviceConnection, String str2);

    public abstract void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract void zzb(String str, ServiceConnection serviceConnection, String str2);
}
