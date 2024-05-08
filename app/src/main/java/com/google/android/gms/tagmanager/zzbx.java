package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzbx extends zzak {
    private static final String ID = zza.OS_VERSION.toString();

    public zzbx() {
        super(ID, new String[0]);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(Build.VERSION.RELEASE);
    }

    public boolean zzwn() {
        return true;
    }
}
