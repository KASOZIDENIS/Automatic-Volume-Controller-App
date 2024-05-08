package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzai extends zzak {
    private static final String ID = zza.EVENT.toString();
    private final zzcp zzaCm;

    public zzai(zzcp zzcp) {
        super(ID, new String[0]);
        this.zzaCm = zzcp;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        String zzxv = this.zzaCm.zzxv();
        return zzxv == null ? zzdf.zzxW() : zzdf.zzE(zzxv);
    }

    public boolean zzwn() {
        return false;
    }
}
