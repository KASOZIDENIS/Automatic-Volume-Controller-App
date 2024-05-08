package com.google.android.gms.tagmanager;

class zzcs implements zzcd {
    private final long zzIj;
    private final int zzIk;
    private double zzIl;
    private final Object zzIn;
    private long zzaFo;

    public zzcs() {
        this(60, 2000);
    }

    public zzcs(int i, long j) {
        this.zzIn = new Object();
        this.zzIk = i;
        this.zzIl = (double) this.zzIk;
        this.zzIj = j;
    }

    public boolean zziU() {
        boolean z;
        synchronized (this.zzIn) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.zzIl < ((double) this.zzIk)) {
                double d = ((double) (currentTimeMillis - this.zzaFo)) / ((double) this.zzIj);
                if (d > 0.0d) {
                    this.zzIl = Math.min((double) this.zzIk, d + this.zzIl);
                }
            }
            this.zzaFo = currentTimeMillis;
            if (this.zzIl >= 1.0d) {
                this.zzIl -= 1.0d;
                z = true;
            } else {
                zzbg.zzan("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
