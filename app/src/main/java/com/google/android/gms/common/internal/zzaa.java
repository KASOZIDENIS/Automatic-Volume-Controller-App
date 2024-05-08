package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public class zzaa<T extends IInterface> extends zzi<T> {
    private final Api.zzd<T> zzUd;

    public zzaa(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zze zze, Api.zzd zzd) {
        super(context, looper, i, connectionCallbacks, onConnectionFailedListener, zze);
        this.zzUd = zzd;
    }

    /* access modifiers changed from: protected */
    public T zzD(IBinder iBinder) {
        return this.zzUd.zzD(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeq() {
        return this.zzUd.zzeq();
    }

    /* access modifiers changed from: protected */
    public String zzer() {
        return this.zzUd.zzer();
    }
}
