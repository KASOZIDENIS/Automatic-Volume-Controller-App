package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzv;

public abstract class zzg<T> {
    private final String zzacr;
    private T zzacs;

    public static class zza extends Exception {
        public zza(String str) {
            super(str);
        }

        public zza(String str, Throwable th) {
            super(str, th);
        }
    }

    protected zzg(String str) {
        this.zzacr = str;
    }

    /* access modifiers changed from: protected */
    public final T zzX(Context context) throws zza {
        if (this.zzacs == null) {
            zzv.zzr(context);
            Context remoteContext = GooglePlayServicesUtil.getRemoteContext(context);
            if (remoteContext == null) {
                throw new zza("Could not get remote context.");
            }
            try {
                this.zzacs = zzd((IBinder) remoteContext.getClassLoader().loadClass(this.zzacr).newInstance());
            } catch (ClassNotFoundException e) {
                throw new zza("Could not load creator class.", e);
            } catch (InstantiationException e2) {
                throw new zza("Could not instantiate creator.", e2);
            } catch (IllegalAccessException e3) {
                throw new zza("Could not access creator.", e3);
            }
        }
        return this.zzacs;
    }

    /* access modifiers changed from: protected */
    public abstract T zzd(IBinder iBinder);
}
