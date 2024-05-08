package com.google.android.gms.analytics.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzv;

abstract class zzs {
    private static volatile Handler zzGW;
    /* access modifiers changed from: private */
    public final zze zzFD;
    /* access modifiers changed from: private */
    public volatile long zzGX;
    /* access modifiers changed from: private */
    public boolean zzGY;
    private final Runnable zznB = new Runnable() {
        public void run() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                zzs.this.zzFD.zzgJ().zze((Runnable) this);
                return;
            }
            boolean zzaK = zzs.this.zzaK();
            long unused = zzs.this.zzGX = 0;
            if (zzaK && !zzs.this.zzGY) {
                zzs.this.run();
            }
        }
    };

    zzs(zze zze) {
        zzv.zzr(zze);
        this.zzFD = zze;
    }

    private Handler getHandler() {
        Handler handler;
        if (zzGW != null) {
            return zzGW;
        }
        synchronized (zzs.class) {
            if (zzGW == null) {
                zzGW = new Handler(this.zzFD.getContext().getMainLooper());
            }
            handler = zzGW;
        }
        return handler;
    }

    public void cancel() {
        this.zzGX = 0;
        getHandler().removeCallbacks(this.zznB);
    }

    public abstract void run();

    public boolean zzaK() {
        return this.zzGX != 0;
    }

    public long zziw() {
        if (this.zzGX == 0) {
            return 0;
        }
        return Math.abs(this.zzFD.zzgG().currentTimeMillis() - this.zzGX);
    }

    public void zzr(long j) {
        cancel();
        if (j >= 0) {
            this.zzGX = this.zzFD.zzgG().currentTimeMillis();
            if (!getHandler().postDelayed(this.zznB, j)) {
                this.zzFD.zzgH().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public void zzs(long j) {
        long j2 = 0;
        if (zzaK()) {
            if (j < 0) {
                cancel();
                return;
            }
            long abs = j - Math.abs(this.zzFD.zzgG().currentTimeMillis() - this.zzGX);
            if (abs >= 0) {
                j2 = abs;
            }
            getHandler().removeCallbacks(this.zznB);
            if (!getHandler().postDelayed(this.zznB, j2)) {
                this.zzFD.zzgH().zze("Failed to adjust delayed post. time", Long.valueOf(j2));
            }
        }
    }
}
