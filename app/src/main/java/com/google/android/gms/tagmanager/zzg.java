package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzg extends zzak {
    private static final String ID = zza.APP_NAME.toString();
    private final Context mContext;

    public zzg(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return zzdf.zzE(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        } catch (PackageManager.NameNotFoundException e) {
            zzbg.zzb("App name is not found.", e);
            return zzdf.zzxW();
        }
    }

    public boolean zzwn() {
        return true;
    }
}
