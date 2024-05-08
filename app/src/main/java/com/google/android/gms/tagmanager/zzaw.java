package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzaw extends zzak {
    private static final String ID = zza.INSTALL_REFERRER.toString();
    private static final String zzaCb = zzb.COMPONENT.toString();
    private final Context zzmH;

    public zzaw(Context context) {
        super(ID, new String[0]);
        this.zzmH = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        String zzi = zzax.zzi(this.zzmH, map.get(zzaCb) != null ? zzdf.zzg(map.get(zzaCb)) : null);
        return zzi != null ? zzdf.zzE(zzi) : zzdf.zzxW();
    }

    public boolean zzwn() {
        return true;
    }
}
