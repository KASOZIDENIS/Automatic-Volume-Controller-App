package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzda extends zzak {
    private static final String ID = zza.TIME.toString();

    public zzda() {
        super(ID, new String[0]);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(Long.valueOf(System.currentTimeMillis()));
    }

    public boolean zzwn() {
        return false;
    }
}
