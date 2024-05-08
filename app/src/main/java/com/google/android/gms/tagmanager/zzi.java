package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzi extends zzak {
    private static final String ID = zza.APP_VERSION_NAME.toString();
    private final Context mContext;

    public zzi(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        try {
            return zzdf.zzE(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            zzbg.zzak("Package name " + this.mContext.getPackageName() + " not found. " + e.getMessage());
            return zzdf.zzxW();
        }
    }

    public boolean zzwn() {
        return true;
    }
}
