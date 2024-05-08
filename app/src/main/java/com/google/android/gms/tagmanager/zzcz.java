package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzd;
import java.util.Map;

abstract class zzcz extends zzca {
    public zzcz(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzd.zza zza, zzd.zza zza2, Map<String, zzd.zza> map) {
        String zzg = zzdf.zzg(zza);
        String zzg2 = zzdf.zzg(zza2);
        if (zzg == zzdf.zzxV() || zzg2 == zzdf.zzxV()) {
            return false;
        }
        return zza(zzg, zzg2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(String str, String str2, Map<String, zzd.zza> map);
}
