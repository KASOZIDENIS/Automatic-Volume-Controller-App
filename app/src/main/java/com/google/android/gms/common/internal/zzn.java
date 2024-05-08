package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.internal.zzlj;

public final class zzn {
    private final String zzTY;

    public zzn(String str) {
        this.zzTY = (String) zzv.zzr(str);
    }

    public void zza(Context context, String str, String str2, Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < stackTrace.length && i < 2) {
            sb.append(stackTrace[i].toString());
            sb.append("\n");
            i++;
        }
        zzlj zzlj = new zzlj(context, 10);
        zzlj.zza("GMS_WTF", (byte[]) null, "GMS_WTF", sb.toString());
        zzlj.send();
        if (zzaQ(7)) {
            Log.e(str, str2, th);
            Log.wtf(str, str2, th);
        }
    }

    public void zza(String str, String str2, Throwable th) {
        if (zzaQ(4)) {
            Log.i(str, str2, th);
        }
    }

    public boolean zzaQ(int i) {
        return Log.isLoggable(this.zzTY, i);
    }

    public void zzb(String str, String str2, Throwable th) {
        if (zzaQ(5)) {
            Log.w(str, str2, th);
        }
    }

    public void zzc(String str, String str2, Throwable th) {
        if (zzaQ(6)) {
            Log.e(str, str2, th);
        }
    }

    public void zzs(String str, String str2) {
        if (zzaQ(3)) {
            Log.d(str, str2);
        }
    }

    public void zzt(String str, String str2) {
        if (zzaQ(5)) {
            Log.w(str, str2);
        }
    }

    public void zzu(String str, String str2) {
        if (zzaQ(6)) {
            Log.e(str, str2);
        }
    }
}
