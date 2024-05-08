package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzht;

class zzbe implements zzcd {
    private final long zzIj;
    private final int zzIk;
    private double zzIl;
    private long zzIm;
    private final Object zzIn = new Object();
    private final long zzaDT;
    private final zzht zznR;
    private final String zzso;

    public zzbe(int i, long j, long j2, String str, zzht zzht) {
        this.zzIk = i;
        this.zzIl = (double) this.zzIk;
        this.zzIj = j;
        this.zzaDT = j2;
        this.zzso = str;
        this.zznR = zzht;
    }

    public boolean zziU() {
        boolean z = false;
        synchronized (this.zzIn) {
            long currentTimeMillis = this.zznR.currentTimeMillis();
            if (currentTimeMillis - this.zzIm < this.zzaDT) {
                zzbg.zzan("Excessive " + this.zzso + " detected; call ignored.");
            } else {
                if (this.zzIl < ((double) this.zzIk)) {
                    double d = ((double) (currentTimeMillis - this.zzIm)) / ((double) this.zzIj);
                    if (d > 0.0d) {
                        this.zzIl = Math.min((double) this.zzIk, d + this.zzIl);
                    }
                }
                this.zzIm = currentTimeMillis;
                if (this.zzIl >= 1.0d) {
                    this.zzIl -= 1.0d;
                    z = true;
                } else {
                    zzbg.zzan("Excessive " + this.zzso + " detected; call ignored.");
                }
            }
        }
        return z;
    }
}
