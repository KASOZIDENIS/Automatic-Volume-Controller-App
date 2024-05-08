package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzn extends zzak {
    private static final String ID = zza.CONSTANT.toString();
    private static final String VALUE = zzb.VALUE.toString();

    public zzn() {
        super(ID, VALUE);
    }

    public static String zzwq() {
        return ID;
    }

    public static String zzwr() {
        return VALUE;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return map.get(VALUE);
    }

    public boolean zzwn() {
        return true;
    }
}
