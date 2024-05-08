package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.common.internal.zzv;

public class CampaignTrackingReceiver extends BroadcastReceiver {
    static PowerManager.WakeLock zzEd;
    static Boolean zzEe;
    static Object zznu = new Object();

    public static boolean zzH(Context context) {
        zzv.zzr(context);
        if (zzEe != null) {
            return zzEe.booleanValue();
        }
        boolean zza = zzal.zza(context, (Class<? extends BroadcastReceiver>) CampaignTrackingReceiver.class, true);
        zzEe = Boolean.valueOf(zza);
        return zza;
    }

    public void onReceive(Context context, Intent intent) {
        zze zzJ = zze.zzJ(context);
        zzae zzgH = zzJ.zzgH();
        String stringExtra = intent.getStringExtra("referrer");
        String action = intent.getAction();
        zzgH.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty(stringExtra)) {
            zzgH.zzaI("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        boolean zzI = CampaignTrackingService.zzI(context);
        if (!zzI) {
            zzgH.zzaI("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzaw(stringExtra);
        if (zzJ.zzgI().zzhP()) {
            zzgH.zzaJ("Received unexpected installation campaign on package side");
            return;
        }
        Class<? extends CampaignTrackingService> zzfS = zzfS();
        zzv.zzr(zzfS);
        Intent intent2 = new Intent(context, zzfS);
        intent2.putExtra("referrer", stringExtra);
        synchronized (zznu) {
            context.startService(intent2);
            if (zzI) {
                try {
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    if (zzEd == null) {
                        zzEd = powerManager.newWakeLock(1, "Analytics campaign WakeLock");
                        zzEd.setReferenceCounted(false);
                    }
                    zzEd.acquire(1000);
                } catch (SecurityException e) {
                    zzgH.zzaI("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzaw(String str) {
    }

    /* access modifiers changed from: protected */
    public Class<? extends CampaignTrackingService> zzfS() {
        return CampaignTrackingService.class;
    }
}
