package com.google.android.gms.common.api;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zza;

public interface zzh {
    void begin();

    void connect();

    String getName();

    void onConnected(Bundle bundle);

    <A extends Api.zza, R extends Result, T extends zza.C0001zza<R, A>> T zza(T t);

    void zza(ConnectionResult connectionResult, Api<?> api, int i);

    void zzas(int i);

    <A extends Api.zza, T extends zza.C0001zza<? extends Result, A>> T zzb(T t);
}
