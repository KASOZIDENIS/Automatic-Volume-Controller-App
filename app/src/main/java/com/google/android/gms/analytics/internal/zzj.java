package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzkp;

public class zzj extends zzd {
    private final zzkp zzGq = new zzkp();

    zzj(zze zze) {
        super(zze);
    }

    public void zzfW() {
        zzam zzga = zzga();
        String zziE = zzga.zziE();
        if (zziE != null) {
            this.zzGq.setAppName(zziE);
        }
        String zziG = zzga.zziG();
        if (zziG != null) {
            this.zzGq.setAppVersion(zziG);
        }
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        zzgJ().zzum().zza(this.zzGq);
        zzfW();
    }

    public zzkp zzhq() {
        zzgR();
        return this.zzGq;
    }
}
