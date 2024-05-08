package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzv;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final Status zzKr;
    private final PendingResult<?>[] zzPu;

    BatchResult(Status status, PendingResult<?>[] pendingResults) {
        this.zzKr = status;
        this.zzPu = pendingResults;
    }

    public Status getStatus() {
        return this.zzKr;
    }

    public <R extends Result> R take(BatchResultToken<R> resultToken) {
        zzv.zzb(resultToken.mId < this.zzPu.length, (Object) "The result token does not belong to this batch");
        return this.zzPu[resultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
