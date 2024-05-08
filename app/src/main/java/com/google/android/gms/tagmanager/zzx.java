package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.List;
import java.util.Map;

class zzx extends zzdd {
    private static final String ID = zza.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzb.VALUE.toString();
    private static final String zzaDl = zzb.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzaCl;

    public zzx(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzaCl = dataLayer;
    }

    private void zza(zzd.zza zza) {
        String zzg;
        if (zza != null && zza != zzdf.zzxQ() && (zzg = zzdf.zzg(zza)) != zzdf.zzxV()) {
            this.zzaCl.zzdF(zzg);
        }
    }

    private void zzb(zzd.zza zza) {
        if (zza != null && zza != zzdf.zzxQ()) {
            Object zzl = zzdf.zzl(zza);
            if (zzl instanceof List) {
                for (Object next : (List) zzl) {
                    if (next instanceof Map) {
                        this.zzaCl.push((Map) next);
                    }
                }
            }
        }
    }

    public void zzF(Map<String, zzd.zza> map) {
        zzb(map.get(VALUE));
        zza(map.get(zzaDl));
    }
}
