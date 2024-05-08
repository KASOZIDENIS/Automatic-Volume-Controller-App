package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzcr extends zzak {
    private static final String ID = zza.SDK_VERSION.toString();

    public zzcr() {
        super(ID, new String[0]);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(Integer.valueOf(Build.VERSION.SDK_INT));
    }

    public boolean zzwn() {
        return true;
    }
}
