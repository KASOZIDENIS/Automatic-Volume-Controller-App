package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.common.internal.zzv;

public final class AnalyticsService extends Service {
    private static Boolean zzEf;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();

    public static boolean zzI(Context context) {
        zzv.zzr(context);
        if (zzEf != null) {
            return zzEf.booleanValue();
        }
        boolean zza = zzal.zza(context, (Class<? extends Service>) AnalyticsService.class);
        zzEf = Boolean.valueOf(zza);
        return zza;
    }

    private void zzfQ() {
        try {
            synchronized (AnalyticsReceiver.zznu) {
                PowerManager.WakeLock wakeLock = AnalyticsReceiver.zzEd;
                if (wakeLock != null && wakeLock.isHeld()) {
                    wakeLock.release();
                }
            }
        } catch (SecurityException e) {
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        zze zzJ = zze.zzJ(this);
        zzae zzgH = zzJ.zzgH();
        if (zzJ.zzgI().zzhP()) {
            zzgH.zzaF("Device AnalyticsService is starting up");
        } else {
            zzgH.zzaF("Local AnalyticsService is starting up");
        }
    }

    public void onDestroy() {
        zze zzJ = zze.zzJ(this);
        zzae zzgH = zzJ.zzgH();
        if (zzJ.zzgI().zzhP()) {
            zzgH.zzaF("Device AnalyticsService is shutting down");
        } else {
            zzgH.zzaF("Local AnalyticsService is shutting down");
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, final int startId) {
        zzfQ();
        final zze zzJ = zze.zzJ(this);
        final zzae zzgH = zzJ.zzgH();
        String action = intent.getAction();
        if (zzJ.zzgI().zzhP()) {
            zzgH.zza("Device AnalyticsService called. startId, action", Integer.valueOf(startId), action);
        } else {
            zzgH.zza("Local AnalyticsService called. startId, action", Integer.valueOf(startId), action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzJ.zzfZ().zza((com.google.android.gms.analytics.internal.zzv) new com.google.android.gms.analytics.internal.zzv() {
                public void zzc(Throwable th) {
                    AnalyticsService.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (!AnalyticsService.this.stopSelfResult(startId)) {
                                return;
                            }
                            if (zzJ.zzgI().zzhP()) {
                                zzgH.zzaF("Device AnalyticsService processed last dispatch request");
                            } else {
                                zzgH.zzaF("Local AnalyticsService processed last dispatch request");
                            }
                        }
                    });
                }
            });
        }
        return 2;
    }
}
