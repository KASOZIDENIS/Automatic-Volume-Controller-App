package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzcg extends zzak {
    private static final String ID = zza.RESOLUTION.toString();
    private final Context mContext;

    public zzcg(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        return zzdf.zzE(i + "x" + displayMetrics.heightPixels);
    }

    public boolean zzwn() {
        return true;
    }
}
