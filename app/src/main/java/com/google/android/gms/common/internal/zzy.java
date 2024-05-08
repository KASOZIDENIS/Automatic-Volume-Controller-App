package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;

public final class zzy extends zzg<zzs> {
    private static final zzy zzUc = new zzy();

    private zzy() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzb(Context context, int i, int i2) throws zzg.zza {
        return zzUc.zzc(context, i, i2);
    }

    private View zzc(Context context, int i, int i2) throws zzg.zza {
        try {
            return (View) zze.zzg(((zzs) zzX(context)).zza(zze.zzt(context), i, i2));
        } catch (Exception e) {
            throw new zzg.zza("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    /* renamed from: zzW */
    public zzs zzd(IBinder iBinder) {
        return zzs.zza.zzV(iBinder);
    }
}
