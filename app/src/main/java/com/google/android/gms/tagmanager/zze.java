package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zze extends zzak {
    private static final String ID = zza.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzaCb = zzb.COMPONENT.toString();
    private static final String zzaCc = zzb.CONVERSION_ID.toString();
    private final Context zzmH;

    public zze(Context context) {
        super(ID, zzaCc);
        this.zzmH = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        zzd.zza zza = map.get(zzaCc);
        if (zza == null) {
            return zzdf.zzxW();
        }
        String zzg = zzdf.zzg(zza);
        zzd.zza zza2 = map.get(zzaCb);
        String zzf = zzax.zzf(this.zzmH, zzg, zza2 != null ? zzdf.zzg(zza2) : null);
        return zzf != null ? zzdf.zzE(zzf) : zzdf.zzxW();
    }

    public boolean zzwn() {
        return true;
    }
}
