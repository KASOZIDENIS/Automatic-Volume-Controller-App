package com.google.android.gms.common.api;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.api.zzg;

public class zzd implements zzh {
    private final zzg zzPQ;

    public zzd(zzg zzg) {
        this.zzPQ = zzg;
    }

    private <A extends Api.zza> void zza(zzg.zze<A> zze) throws DeadObjectException {
        this.zzPQ.zzb(zze);
        A zza = this.zzPQ.zza(zze.zzkF());
        if (zza.isConnected() || !this.zzPQ.zzQA.containsKey(zze.zzkF())) {
            zze.zzb(zza);
        } else {
            zze.zzk(new Status(17));
        }
    }

    public void begin() {
        while (!this.zzPQ.zzQt.isEmpty()) {
            try {
                zza(this.zzPQ.zzQt.remove());
            } catch (DeadObjectException e) {
                Log.w("GoogleApiClientConnected", "Service died while flushing queue", e);
            }
        }
    }

    public void connect() {
    }

    public String getName() {
        return "CONNECTED";
    }

    public void onConnected(Bundle connectionHint) {
    }

    public <A extends Api.zza, R extends Result, T extends zza.C0001zza<R, A>> T zza(T t) {
        return zzb(t);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public void zzas(int i) {
        boolean z = i == -1;
        if (z) {
            this.zzPQ.zzkX();
            this.zzPQ.zzQA.clear();
        } else {
            for (zzg.zze<?> forceFailureUnlessReady : this.zzPQ.zzQF) {
                forceFailureUnlessReady.forceFailureUnlessReady(new Status(8, "The connection to Google Play services was lost"));
            }
        }
        this.zzPQ.zzg((ConnectionResult) null);
        if (!z) {
            this.zzPQ.zzQs.zzaP(i);
        }
        this.zzPQ.zzQs.zzmf();
    }

    public <A extends Api.zza, T extends zza.C0001zza<? extends Result, A>> T zzb(T t) {
        try {
            zza(t);
        } catch (DeadObjectException e) {
            zzas(1);
        }
        return t;
    }
}
