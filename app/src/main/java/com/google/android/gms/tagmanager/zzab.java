package com.google.android.gms.tagmanager;

import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzab extends zzak {
    private static final String ID = zza.DEVICE_NAME.toString();

    public zzab() {
        super(ID, new String[0]);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (!str2.startsWith(str) && !str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            str2 = str + " " + str2;
        }
        return zzdf.zzE(str2);
    }

    public boolean zzwn() {
        return true;
    }
}
