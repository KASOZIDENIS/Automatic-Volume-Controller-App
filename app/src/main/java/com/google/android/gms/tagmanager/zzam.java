package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzam extends zzbv {
    private static final String ID = zza.GREATER_EQUALS.toString();

    public zzam() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzde zzde, zzde zzde2, Map<String, zzd.zza> map) {
        return zzde.compareTo(zzde2) >= 0;
    }
}
