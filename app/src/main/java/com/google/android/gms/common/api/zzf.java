package com.google.android.gms.common.api;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzg;

public class zzf implements zzh {
    private final zzg zzPQ;

    public zzf(zzg zzg) {
        this.zzPQ = zzg;
    }

    public void begin() {
        this.zzPQ.zzkY();
    }

    public void connect() {
        this.zzPQ.zzkZ();
    }

    public String getName() {
        return "DISCONNECTED";
    }

    public void onConnected(Bundle connectionHint) {
    }

    public <A extends Api.zza, R extends Result, T extends zza.C0001zza<R, A>> T zza(T t) {
        this.zzPQ.zzQt.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public void zzas(int i) {
        if (i == -1) {
            for (zzg.zze cancel : this.zzPQ.zzQt) {
                cancel.cancel();
            }
            this.zzPQ.zzQt.clear();
            this.zzPQ.zzkX();
            this.zzPQ.zzQA.clear();
        }
    }

    public <A extends Api.zza, T extends zza.C0001zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
