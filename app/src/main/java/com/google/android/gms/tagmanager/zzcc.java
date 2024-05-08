package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzcc extends zzak {
    private static final String ID = zza.RANDOM.toString();
    private static final String zzaEy = zzb.MIN.toString();
    private static final String zzaEz = zzb.MAX.toString();

    public zzcc() {
        super(ID, new String[0]);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        double d;
        double d2;
        zzd.zza zza = map.get(zzaEy);
        zzd.zza zza2 = map.get(zzaEz);
        if (!(zza == null || zza == zzdf.zzxW() || zza2 == null || zza2 == zzdf.zzxW())) {
            zzde zzh = zzdf.zzh(zza);
            zzde zzh2 = zzdf.zzh(zza2);
            if (!(zzh == zzdf.zzxU() || zzh2 == zzdf.zzxU())) {
                double doubleValue = zzh.doubleValue();
                d = zzh2.doubleValue();
                if (doubleValue <= d) {
                    d2 = doubleValue;
                    return zzdf.zzE(Long.valueOf(Math.round(((d - d2) * Math.random()) + d2)));
                }
            }
        }
        d = 2.147483647E9d;
        d2 = 0.0d;
        return zzdf.zzE(Long.valueOf(Math.round(((d - d2) * Math.random()) + d2)));
    }

    public boolean zzwn() {
        return false;
    }
}
