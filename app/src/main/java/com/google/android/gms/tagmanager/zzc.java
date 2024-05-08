package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzc extends zzak {
    private static final String ID = zza.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzaCa;

    public zzc(Context context) {
        this(zza.zzan(context));
    }

    zzc(zza zza) {
        super(ID, new String[0]);
        this.zzaCa = zza;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(Boolean.valueOf(!this.zzaCa.isLimitAdTrackingEnabled()));
    }

    public boolean zzwn() {
        return false;
    }
}
