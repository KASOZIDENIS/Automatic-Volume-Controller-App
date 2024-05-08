package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzdh extends zzak {
    private static final String ID = zza.UPPERCASE_STRING.toString();
    private static final String zzaDq = zzb.ARG0.toString();

    public zzdh() {
        super(ID, zzaDq);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(zzdf.zzg(map.get(zzaDq)).toUpperCase());
    }

    public boolean zzwn() {
        return true;
    }
}
