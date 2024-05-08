package com.google.android.gms.internal;

import android.os.Build;

public class zzmx {
    /* access modifiers changed from: package-private */
    public int zzwp() {
        return Build.VERSION.SDK_INT;
    }

    public zzmw zzyM() {
        return zzwp() < 8 ? new zzmu() : new zzmv();
    }
}
