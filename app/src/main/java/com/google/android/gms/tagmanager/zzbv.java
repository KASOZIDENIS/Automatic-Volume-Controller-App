package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzd;
import java.util.Map;

abstract class zzbv extends zzca {
    public zzbv(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzd.zza zza, zzd.zza zza2, Map<String, zzd.zza> map) {
        zzde zzh = zzdf.zzh(zza);
        zzde zzh2 = zzdf.zzh(zza2);
        if (zzh == zzdf.zzxU() || zzh2 == zzdf.zzxU()) {
            return false;
        }
        return zza(zzh, zzh2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzde zzde, zzde zzde2, Map<String, zzd.zza> map);
}
