package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.playlog.internal.LogEvent;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.gms.playlog.internal.zzd;
import com.google.android.gms.playlog.internal.zzf;
import java.util.Collection;
import java.util.Map;

public class zzlk {
    private final zzf zzayp;
    private PlayLoggerContext zzayq;

    public interface zza {
        void zzf(PendingIntent pendingIntent);

        void zzvp();

        void zzvq();
    }

    public zzlk(Context context, int i, String str, String str2, zza zza2, boolean z, String str3) {
        String packageName = context.getPackageName();
        int i2 = 0;
        try {
            i2 = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("PlayLogger", "This can't happen.");
        }
        this.zzayq = new PlayLoggerContext(packageName, i2, i, str, str2, z);
        this.zzayp = new zzf(context, context.getMainLooper(), new zzd(zza2), new zze((Account) null, (Collection<Scope>) null, (Map<Api<?>, zze.zza>) null, 49, (View) null, packageName, str3, (zzme) null));
    }

    public void start() {
        this.zzayp.start();
    }

    public void stop() {
        this.zzayp.stop();
    }

    public void zza(long j, String str, byte[] bArr, String... strArr) {
        this.zzayp.zzb(this.zzayq, new LogEvent(j, str, bArr, strArr));
    }

    public void zzb(String str, byte[] bArr, String... strArr) {
        zza(System.currentTimeMillis(), str, bArr, strArr);
    }
}
