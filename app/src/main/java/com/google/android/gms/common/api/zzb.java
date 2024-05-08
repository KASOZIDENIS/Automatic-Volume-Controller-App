package com.google.android.gms.common.api;

import com.google.android.gms.common.api.zzi;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzb<L> implements zzi.zzb<L> {
    private final DataHolder zzPy;

    protected zzb(DataHolder dataHolder) {
        this.zzPy = dataHolder;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(L l, DataHolder dataHolder);

    public final void zzk(L l) {
        zza(l, this.zzPy);
    }

    public void zzkJ() {
        if (this.zzPy != null) {
            this.zzPy.close();
        }
    }
}
