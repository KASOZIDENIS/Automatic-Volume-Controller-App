package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;

abstract class zzhl<R extends Result> extends zza.C0001zza<R, zzhm> {

    static abstract class zza extends zzhl<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status createFailedResult(Status status) {
            return status;
        }
    }

    public zzhl(GoogleApiClient googleApiClient) {
        super(zzhi.zzKh, googleApiClient);
    }
}
