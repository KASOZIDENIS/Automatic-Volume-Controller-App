package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzv extends zzak {
    private static final String ID = zza.CUSTOM_VAR.toString();
    private static final String NAME = zzb.NAME.toString();
    private static final String zzaDa = zzb.DEFAULT_VALUE.toString();
    private final DataLayer zzaCl;

    public zzv(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzaCl = dataLayer;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        Object obj = this.zzaCl.get(zzdf.zzg(map.get(NAME)));
        if (obj != null) {
            return zzdf.zzE(obj);
        }
        zzd.zza zza = map.get(zzaDa);
        return zza != null ? zza : zzdf.zzxW();
    }

    public boolean zzwn() {
        return false;
    }
}
