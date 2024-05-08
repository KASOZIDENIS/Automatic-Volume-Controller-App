package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.zzv;
import java.util.HashMap;
import java.util.Map;

public final class zzky implements Application.ActivityLifecycleCallbacks {
    private final zzko zzawY;
    private final Map<Activity, zzkv> zzawZ = new HashMap();

    public zzky(zzko zzko) {
        zzv.zzr(zzko);
        this.zzawY = zzko;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Bundle bundle;
        if (savedInstanceState != null && (bundle = savedInstanceState.getBundle("com.google.android.gms.measurement.screen_view")) != null) {
            int i = bundle.getInt("id");
            if (i <= 0) {
                Log.w("com.google.android.gms.measurement.internal.ActivityLifecycleTracker", "Invalid screenId in saved activity state");
                return;
            }
            zzkv zza = zza(activity, i);
            zza.setScreenName(bundle.getString("name"));
            zza.zzgJ(bundle.getInt("referrer_id"));
            zza.zzdd(bundle.getString("referrer_name"));
            zza.zzad(bundle.getBoolean("interstitial"));
            zza.zzuP();
        }
    }

    public void onActivityDestroyed(Activity activity) {
        this.zzawZ.remove(activity);
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        zzkv zzkv;
        if (outState != null && (zzkv = this.zzawZ.get(activity)) != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", zzkv.zzaJ());
            bundle.putString("name", zzkv.zzuL());
            bundle.putInt("referrer_id", zzkv.zzuM());
            bundle.putString("referrer_name", zzkv.zzuN());
            bundle.putBoolean("interstitial", zzkv.zzuQ());
            outState.putBundle("com.google.android.gms.measurement.screen_view", bundle);
        }
    }

    public void onActivityStarted(Activity activity) {
        this.zzawY.zzb(zza(activity, 0), activity);
    }

    public void onActivityStopped(Activity activity) {
    }

    /* access modifiers changed from: package-private */
    public zzkv zza(Activity activity, int i) {
        zzv.zzr(activity);
        zzkv zzkv = this.zzawZ.get(activity);
        if (zzkv == null) {
            zzkv = i == 0 ? new zzkv(true) : new zzkv(true, i);
            zzkv.setScreenName(activity.getClass().getCanonicalName());
            this.zzawZ.put(activity, zzkv);
        }
        return zzkv;
    }
}
