package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzkj;
import java.util.ArrayList;
import java.util.List;

public abstract class zzkj<T extends zzkj> {
    private final zzkk zzawh;
    protected final zzkg zzawi;
    private final List<zzkh> zzawj = new ArrayList();

    protected zzkj(zzkk zzkk, zzht zzht) {
        zzv.zzr(zzkk);
        this.zzawh = zzkk;
        zzkg zzkg = new zzkg(this, zzht);
        zzkg.zzuj();
        this.zzawi = zzkg;
    }

    /* access modifiers changed from: protected */
    public void zza(zzkg zzkg) {
    }

    /* access modifiers changed from: protected */
    public void zzd(zzkg zzkg) {
        for (zzkh zza : this.zzawj) {
            zza.zza(this, zzkg);
        }
    }

    public zzkg zzfP() {
        zzkg zztZ = this.zzawi.zztZ();
        zzd(zztZ);
        return zztZ;
    }

    /* access modifiers changed from: protected */
    public zzkk zzuh() {
        return this.zzawh;
    }

    public zzkg zzuk() {
        return this.zzawi;
    }

    public List<zzkm> zzul() {
        return this.zzawi.zzub();
    }
}
