package com.google.android.gms.analytics.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.common.internal.zzv;

public class zzu extends zzd {
    private boolean zzHa;
    private boolean zzHb;
    private AlarmManager zzHc = ((AlarmManager) getContext().getSystemService("alarm"));

    protected zzu(zze zze) {
        super(zze);
    }

    private PendingIntent zziB() {
        Intent intent = new Intent(getContext(), AnalyticsReceiver.class);
        intent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public void cancel() {
        zzgR();
        this.zzHb = false;
        this.zzHc.cancel(zziB());
    }

    public boolean zzaK() {
        return this.zzHb;
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        ActivityInfo receiverInfo;
        try {
            this.zzHc.cancel(zziB());
            if (zzgI().zzhY() > 0 && (receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), AnalyticsReceiver.class), 2)) != null && receiverInfo.enabled) {
                zzaF("Receiver registered. Using alarm for local dispatch.");
                this.zzHa = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    public void zziA() {
        zzgR();
        zzv.zza(zziz(), (Object) "Receiver not registered");
        long zzhY = zzgI().zzhY();
        if (zzhY > 0) {
            cancel();
            long elapsedRealtime = zzgG().elapsedRealtime() + zzhY;
            this.zzHb = true;
            this.zzHc.setInexactRepeating(2, elapsedRealtime, 0, zziB());
        }
    }

    public boolean zziz() {
        return this.zzHa;
    }
}
