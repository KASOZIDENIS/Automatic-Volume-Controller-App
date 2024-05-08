package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzmg;
import com.google.android.gms.tagmanager.Container;

class zzmf {
    private final Context mContext;
    private final zzmg zznw;

    static class zza implements zzko.zza {
        private final Tracker zzEt;

        zza(Tracker tracker) {
            this.zzEt = tracker;
        }

        public void zza(zzkv zzkv) {
            this.zzEt.setScreenName(zzkv.zzuL());
            HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
            screenViewBuilder.set("&a", String.valueOf(zzkv.zzaJ()));
            this.zzEt.send(screenViewBuilder.build());
        }

        public void zza(zzkv zzkv, Activity activity) {
        }
    }

    public zzmf(Context context, Container container, zzmg zzmg) {
        this.mContext = context;
        this.zznw = zza(container, zzmg);
        zzxY();
    }

    static zzmg zza(Container container, zzmg zzmg) {
        if (container == null || container.isDefault()) {
            return zzmg;
        }
        zzmg.zza zza2 = new zzmg.zza(zzmg.zzxZ());
        zza2.zzek(container.getString("trackingId")).zzak(container.getBoolean("trackScreenViews")).zzal(container.getBoolean("collectAdIdentifiers"));
        return zza2.zzyc();
    }

    private void zzxY() {
        if (this.zznw.zzya() && !TextUtils.isEmpty(this.zznw.zzjs())) {
            Tracker zzej = zzej(this.zznw.zzjs());
            zzej.enableAdvertisingIdCollection(this.zznw.zzyb());
            zzb(new zza(zzej));
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzko.zza zza2) {
        zzv.zzr(zza2);
        zzko zzal = zzko.zzal(this.mContext);
        zzal.zzaa(true);
        zzal.zza(zza2);
    }

    /* access modifiers changed from: package-private */
    public Tracker zzej(String str) {
        return GoogleAnalytics.getInstance(this.mContext).newTracker(str);
    }

    public zzmg zzxX() {
        return this.zznw;
    }
}
