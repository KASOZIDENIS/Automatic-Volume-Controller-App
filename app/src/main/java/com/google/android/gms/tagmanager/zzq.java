package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzq extends zzak {
    private static final String ID = zza.CONTAINER_VERSION.toString();
    private final String zzacK;

    public zzq(String str) {
        super(ID, new String[0]);
        this.zzacK = str;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return this.zzacK == null ? zzdf.zzxW() : zzdf.zzE(this.zzacK);
    }

    public boolean zzwn() {
        return true;
    }
}
