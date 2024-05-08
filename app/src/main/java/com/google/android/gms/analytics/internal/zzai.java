package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzht;

class zzai {
    private long zzIG;
    private final zzht zznR;

    public zzai(zzht zzht) {
        zzv.zzr(zzht);
        this.zznR = zzht;
    }

    public zzai(zzht zzht, long j) {
        zzv.zzr(zzht);
        this.zznR = zzht;
        this.zzIG = j;
    }

    public void clear() {
        this.zzIG = 0;
    }

    public void start() {
        this.zzIG = this.zznR.elapsedRealtime();
    }

    public boolean zzt(long j) {
        return this.zzIG == 0 || this.zznR.elapsedRealtime() - this.zzIG > j;
    }
}
