package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.common.internal.zzv;

public class CampaignTrackingService extends Service {
    private static Boolean zzEf;
    private Handler mHandler;

    private Handler getHandler() {
        Handler handler = this.mHandler;
        if (handler != null) {
            return handler;
        }
        Handler handler2 = new Handler(getMainLooper());
        this.mHandler = handler2;
        return handler2;
    }

    public static boolean zzI(Context context) {
        zzv.zzr(context);
        if (zzEf != null) {
            return zzEf.booleanValue();
        }
        boolean zza = zzal.zza(context, (Class<? extends Service>) CampaignTrackingService.class);
        zzEf = Boolean.valueOf(zza);
        return zza;
    }

    private void zzfQ() {
        try {
            synchronized (CampaignTrackingReceiver.zznu) {
                PowerManager.WakeLock wakeLock = CampaignTrackingReceiver.zzEd;
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
        zze.zzJ(this).zzgH().zzaF("CampaignTrackingService is starting up");
    }

    public void onDestroy() {
        zze.zzJ(this).zzgH().zzaF("CampaignTrackingService is shutting down");
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, final int startId) {
        zzfQ();
        zze zzJ = zze.zzJ(this);
        final zzae zzgH = zzJ.zzgH();
        String str = null;
        if (zzJ.zzgI().zzhP()) {
            zzgH.zzaJ("Unexpected installation campaign (package side)");
        } else {
            str = intent.getStringExtra("referrer");
        }
        final Handler handler = getHandler();
        if (TextUtils.isEmpty(str)) {
            if (!zzJ.zzgI().zzhP()) {
                zzgH.zzaI("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
            }
            zzJ.zzgJ().zze((Runnable) new Runnable() {
                public void run() {
                    CampaignTrackingService.this.zza(zzgH, handler, startId);
                }
            });
        } else {
            int zzhT = zzJ.zzgI().zzhT();
            if (str.length() > zzhT) {
                zzgH.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(str.length()), Integer.valueOf(zzhT));
                str = str.substring(0, zzhT);
            }
            zzgH.zza("CampaignTrackingService called. startId, campaign", Integer.valueOf(startId), str);
            zzJ.zzfZ().zza(str, new Runnable() {
                public void run() {
                    CampaignTrackingService.this.zza(zzgH, handler, startId);
                }
            });
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public void zza(final zzae zzae, Handler handler, final int i) {
        handler.post(new Runnable() {
            public void run() {
                boolean stopSelfResult = CampaignTrackingService.this.stopSelfResult(i);
                if (stopSelfResult) {
                    zzae.zza("Install campaign broadcast processed", Boolean.valueOf(stopSelfResult));
                }
            }
        });
    }
}
