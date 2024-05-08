package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.internal.zzho;

public class zzhm extends zzi<zzho> {
    public zzhm(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzX */
    public zzho zzD(IBinder iBinder) {
        return zzho.zza.zzZ(iBinder);
    }

    public String zzeq() {
        return "com.google.android.gms.common.service.START";
    }

    /* access modifiers changed from: protected */
    public String zzer() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }
}
