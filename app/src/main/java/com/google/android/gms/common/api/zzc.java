package com.google.android.gms.common.api;

import com.google.android.gms.common.data.DataHolder;

public abstract class zzc implements Releasable, Result {
    protected final Status zzKr;
    protected final DataHolder zzPy;

    protected zzc(DataHolder dataHolder) {
        this(dataHolder, new Status(dataHolder.getStatusCode()));
    }

    protected zzc(DataHolder dataHolder, Status status) {
        this.zzKr = status;
        this.zzPy = dataHolder;
    }

    public Status getStatus() {
        return this.zzKr;
    }

    public void release() {
        if (this.zzPy != null) {
            this.zzPy.close();
        }
    }
}
