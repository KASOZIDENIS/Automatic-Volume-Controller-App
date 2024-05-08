package com.google.android.gms.internal;

import android.os.SystemClock;

public final class zzhv implements zzht {
    private static zzhv zzVC;

    public static synchronized zzht zznd() {
        zzhv zzhv;
        synchronized (zzhv.class) {
            if (zzVC == null) {
                zzVC = new zzhv();
            }
            zzhv = zzVC;
        }
        return zzhv;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
