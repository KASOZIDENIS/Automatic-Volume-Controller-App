package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.internal.zzlk;

public class zzlj implements zzlk.zza {
    private final zzlk zzayn;
    private boolean zzayo;

    public zzlj(Context context, int i) {
        this(context, i, (String) null);
    }

    public zzlj(Context context, int i, String str) {
        this(context, i, str, (String) null, true);
    }

    public zzlj(Context context, int i, String str, String str2, boolean z) {
        this.zzayn = new zzlk(context, i, str, str2, this, z, context != context.getApplicationContext() ? context.getClass().getName() : "OneTimePlayLogger");
        this.zzayo = true;
    }

    private void zzvo() {
        if (!this.zzayo) {
            throw new IllegalStateException("Cannot reuse one-time logger after sending.");
        }
    }

    public void send() {
        zzvo();
        this.zzayn.start();
        this.zzayo = false;
    }

    public void zza(String str, byte[] bArr, String... strArr) {
        zzvo();
        this.zzayn.zzb(str, bArr, strArr);
    }

    public void zzf(PendingIntent pendingIntent) {
        Log.w("OneTimePlayLogger", "logger connection failed: " + pendingIntent);
    }

    public void zzvp() {
        this.zzayn.stop();
    }

    public void zzvq() {
        Log.w("OneTimePlayLogger", "logger connection failed");
    }
}
