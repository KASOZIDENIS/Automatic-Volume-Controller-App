package com.google.android.gms.common.api;

import android.os.Looper;

public class zzk extends AbstractPendingResult<Status> {
    public zzk(Looper looper) {
        super(looper);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzb */
    public Status createFailedResult(Status status) {
        return status;
    }
}
