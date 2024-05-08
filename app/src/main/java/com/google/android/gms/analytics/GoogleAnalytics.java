package com.google.android.gms.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzac;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzaj;
import com.google.android.gms.analytics.internal.zzak;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzx;
import com.google.android.gms.common.internal.zzv;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzEw = new ArrayList();
    private boolean zzEA;
    private volatile boolean zzEB;
    private boolean zzEC;
    private boolean zzEx;
    private Set<zza> zzEy = new HashSet();
    private boolean zzEz;
    private boolean zznz;

    interface zza {
        void zzn(Activity activity);

        void zzo(Activity activity);
    }

    class zzb implements Application.ActivityLifecycleCallbacks {
        zzb() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zzl(activity);
        }

        public void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzm(activity);
        }
    }

    public GoogleAnalytics(zze context) {
        super(context);
    }

    public static GoogleAnalytics getInstance(Context context) {
        return zze.zzJ(context).zzgV();
    }

    public static void zzfX() {
        synchronized (GoogleAnalytics.class) {
            if (zzEw != null) {
                for (Runnable run : zzEw) {
                    run.run();
                }
                zzEw = null;
            }
        }
    }

    private com.google.android.gms.analytics.internal.zzb zzfZ() {
        return zzfO().zzfZ();
    }

    private zzam zzga() {
        return zzfO().zzga();
    }

    public void dispatchLocalHits() {
        zzfZ().zzgw();
    }

    public void enableAutoActivityReports(Application application) {
        if (Build.VERSION.SDK_INT >= 14 && !this.zzEz) {
            application.registerActivityLifecycleCallbacks(new zzb());
            this.zzEz = true;
        }
    }

    public boolean getAppOptOut() {
        return this.zzEB;
    }

    public String getClientId() {
        zzv.zzbJ("getClientId can not be called from the main thread");
        return zzfO().zzgY().zzhI();
    }

    @Deprecated
    public Logger getLogger() {
        return zzad.getLogger();
    }

    public boolean isDryRunEnabled() {
        return this.zzEA;
    }

    public boolean isInitialized() {
        return this.zznz && !this.zzEx;
    }

    public Tracker newTracker(int configResId) {
        Tracker tracker;
        zzak zzak;
        synchronized (this) {
            tracker = new Tracker(zzfO(), (String) null, (zzac) null);
            if (configResId > 0 && (zzak = (zzak) new zzaj(zzfO()).zzS(configResId)) != null) {
                tracker.zza(zzak);
            }
            tracker.zzfV();
        }
        return tracker;
    }

    public Tracker newTracker(String trackingId) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzfO(), trackingId, (zzac) null);
            tracker.zzfV();
        }
        return tracker;
    }

    public void reportActivityStart(Activity activity) {
        if (!this.zzEz) {
            zzl(activity);
        }
    }

    public void reportActivityStop(Activity activity) {
        if (!this.zzEz) {
            zzm(activity);
        }
    }

    public void setAppOptOut(boolean optOut) {
        this.zzEB = optOut;
        if (this.zzEB) {
            zzfZ().zzgv();
        }
    }

    public void setDryRun(boolean dryRun) {
        this.zzEA = dryRun;
    }

    public void setLocalDispatchPeriod(int dispatchPeriodInSeconds) {
        zzfZ().setLocalDispatchPeriod(dispatchPeriodInSeconds);
    }

    @Deprecated
    public void setLogger(Logger logger) {
        zzad.setLogger(logger);
        if (!this.zzEC) {
            Log.i(zzx.zzHf.get(), "GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + zzx.zzHf.get() + " DEBUG");
            this.zzEC = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zza zza2) {
        this.zzEy.add(zza2);
        Context context = zzfO().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zza zza2) {
        this.zzEy.remove(zza2);
    }

    public void zzfV() {
        zzfW();
        this.zznz = true;
    }

    /* access modifiers changed from: package-private */
    public void zzfW() {
        Logger logger;
        zzam zzga = zzga();
        if (zzga.zziH()) {
            getLogger().setLogLevel(zzga.getLogLevel());
        }
        if (zzga.zziL()) {
            setDryRun(zzga.zziM());
        }
        if (zzga.zziH() && (logger = zzad.getLogger()) != null) {
            logger.setLogLevel(zzga.getLogLevel());
        }
    }

    /* access modifiers changed from: package-private */
    public void zzfY() {
        zzfZ().zzgx();
    }

    /* access modifiers changed from: package-private */
    public void zzl(Activity activity) {
        for (zza zzn : this.zzEy) {
            zzn.zzn(activity);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzm(Activity activity) {
        for (zza zzo : this.zzEy) {
            zzo.zzo(activity);
        }
    }
}
