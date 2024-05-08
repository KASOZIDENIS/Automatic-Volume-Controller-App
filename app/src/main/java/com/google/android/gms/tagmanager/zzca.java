package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;
import java.util.Set;

public abstract class zzca extends zzak {
    private static final String zzaDq = zzb.ARG0.toString();
    private static final String zzaEo = zzb.ARG1.toString();

    public zzca(String str) {
        super(str, zzaDq, zzaEo);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        for (zzd.zza zza : map.values()) {
            if (zza == zzdf.zzxW()) {
                return zzdf.zzE(false);
            }
        }
        zzd.zza zza2 = map.get(zzaDq);
        zzd.zza zza3 = map.get(zzaEo);
        return zzdf.zzE(Boolean.valueOf((zza2 == null || zza3 == null) ? false : zza(zza2, zza3, map)));
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzd.zza zza, zzd.zza zza2, Map<String, zzd.zza> map);

    public /* bridge */ /* synthetic */ String zzwS() {
        return super.zzwS();
    }

    public /* bridge */ /* synthetic */ Set zzwT() {
        return super.zzwT();
    }

    public boolean zzwn() {
        return true;
    }
}
