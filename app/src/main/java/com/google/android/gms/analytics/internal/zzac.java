package com.google.android.gms.analytics.internal;

public class zzac {
    private final long zzIj;
    private final int zzIk;
    private double zzIl;
    private long zzIm;
    private final Object zzIn;
    private final String zzso;

    public zzac(int i, long j, String str) {
        this.zzIn = new Object();
        this.zzIk = i;
        this.zzIl = (double) this.zzIk;
        this.zzIj = j;
        this.zzso = str;
    }

    public zzac(String str) {
        this(60, 2000, str);
    }

    public boolean zziU() {
        boolean z;
        synchronized (this.zzIn) {
            long currentTimeMillis = System.currentTimeMillis();
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
                zzad.zzan("Excessive " + this.zzso + " detected; call ignored.");
                z = false;
            }
        }
        return z;
    }
}
