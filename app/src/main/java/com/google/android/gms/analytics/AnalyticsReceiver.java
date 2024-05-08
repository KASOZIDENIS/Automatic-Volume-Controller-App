package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.common.internal.zzv;

public final class AnalyticsReceiver extends BroadcastReceiver {
    static PowerManager.WakeLock zzEd;
    static Boolean zzEe;
    static Object zznu = new Object();

    public static boolean zzH(Context context) {
        zzv.zzr(context);
        if (zzEe != null) {
            return zzEe.booleanValue();
        }
        boolean zza = zzal.zza(context, (Class<? extends BroadcastReceiver>) AnalyticsReceiver.class, false);
        zzEe = Boolean.valueOf(zza);
        return zza;
    }

    public void onReceive(Context context, Intent intent) {
        zze zzJ = zze.zzJ(context);
        zzae zzgH = zzJ.zzgH();
        String action = intent.getAction();
        if (zzJ.zzgI().zzhP()) {
            zzgH.zza("Device AnalyticsReceiver got", action);
        } else {
            zzgH.zza("Local AnalyticsReceiver got", action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zzI = AnalyticsService.zzI(context);
            Intent intent2 = new Intent(context, AnalyticsService.class);
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (zznu) {
                context.startService(intent2);
                if (zzI) {
                    try {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (zzEd == null) {
                            zzEd = powerManager.newWakeLock(1, "Analytics WakeLock");
                            zzEd.setReferenceCounted(false);
                        }
                        zzEd.acquire(1000);
                    } catch (SecurityException e) {
                        zzgH.zzaI("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                    return;
                }
                return;
            }
        }
        return;
    }
}
