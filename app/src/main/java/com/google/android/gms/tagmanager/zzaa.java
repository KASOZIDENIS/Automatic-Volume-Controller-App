package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzaa extends zzak {
    private static final String ID = zza.DEVICE_ID.toString();
    private final Context mContext;

    public zzaa(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        String zzap = zzap(this.mContext);
        return zzap == null ? zzdf.zzxW() : zzdf.zzE(zzap);
    }

    /* access modifiers changed from: protected */
    public String zzap(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public boolean zzwn() {
        return true;
    }
}
