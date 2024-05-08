package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzcy extends zzcz {
    private static final String ID = zza.STARTS_WITH.toString();

    public zzcy() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(String str, String str2, Map<String, zzd.zza> map) {
        return str.startsWith(str2);
    }
}
