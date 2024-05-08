package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzf extends zzak {
    private static final String ID = zza.APP_ID.toString();
    private final Context mContext;

    public zzf(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        return zzdf.zzE(this.mContext.getPackageName());
    }

    public boolean zzwn() {
        return true;
    }
}
