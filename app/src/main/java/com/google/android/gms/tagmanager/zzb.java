package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzb extends zzak {
    private static final String ID = zza.ADVERTISER_ID.toString();
    private final zza zzaCa;

    public zzb(Context context) {
        this(zza.zzan(context));
    }

    zzb(zza zza) {
        super(ID, new String[0]);
        this.zzaCa = zza;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        String zzwj = this.zzaCa.zzwj();
        return zzwj == null ? zzdf.zzxW() : zzdf.zzE(zzwj);
    }

    public boolean zzwn() {
        return false;
    }
}
