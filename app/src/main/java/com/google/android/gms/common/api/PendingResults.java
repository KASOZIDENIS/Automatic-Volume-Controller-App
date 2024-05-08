package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzv;

public final class PendingResults {

    private static final class zza<R extends Result> extends AbstractPendingResult<R> {
        private final R zzQS;

        public zza(R r) {
            super(Looper.getMainLooper());
            this.zzQS = r;
        }

        /* access modifiers changed from: protected */
        public R createFailedResult(Status status) {
            if (status.getStatusCode() == this.zzQS.getStatus().getStatusCode()) {
                return this.zzQS;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private static final class zzb<R extends Result> extends AbstractPendingResult<R> {
        public zzb() {
            super(Looper.getMainLooper());
        }

        /* access modifiers changed from: protected */
        public R createFailedResult(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private PendingResults() {
    }

    public static PendingResult<Status> canceledPendingResult() {
        zzk zzk = new zzk(Looper.getMainLooper());
        zzk.cancel();
        return zzk;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R result) {
        zzv.zzb(result, (Object) "Result must not be null");
        zzv.zzb(result.getStatus().getStatusCode() == 16, (Object) "Status code must be CommonStatusCodes.CANCELED");
        zza zza2 = new zza(result);
        zza2.cancel();
        return zza2;
    }

    public static <R extends Result> PendingResult<R> immediatePendingResult(R result) {
        zzv.zzb(result, (Object) "Result must not be null");
        zzb zzb2 = new zzb();
        zzb2.setResult(result);
        return zzb2;
    }

    public static PendingResult<Status> immediatePendingResult(Status result) {
        zzv.zzb(result, (Object) "Result must not be null");
        zzk zzk = new zzk(Looper.getMainLooper());
        zzk.setResult(result);
        return zzk;
    }
}
