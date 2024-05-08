package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzbi extends zzak {
    private static final String ID = zza.LOWERCASE_STRING.toString();
    private static final String zzaDq = zzb.ARG0.toString();

    public zzbi() {
        super(ID, zzaDq);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(zzdf.zzg(map.get(zzaDq)).toLowerCase());
    }

    public boolean zzwn() {
        return true;
    }
}
