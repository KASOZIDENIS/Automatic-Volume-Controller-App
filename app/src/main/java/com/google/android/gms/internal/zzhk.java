package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.internal.zzhl;

public final class zzhk implements zzhj {

    private static class zza extends zzhh {
        private final zza.zzb<Status> zzKq;

        public zza(zza.zzb<Status> zzb) {
            this.zzKq = zzb;
        }

        public void zzaW(int i) throws RemoteException {
            this.zzKq.zzj(new Status(i));
        }
    }

    public PendingResult<Status> zzc(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zzhl.zza(googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzhm zzhm) throws RemoteException {
                ((zzho) zzhm.zzlX()).zza(new zza(this));
            }
        });
    }
}
