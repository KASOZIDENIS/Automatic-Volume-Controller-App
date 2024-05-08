package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

class zzcu extends zzct {
    private static zzcu zzaFA;
    /* access modifiers changed from: private */
    public static final Object zzaFp = new Object();
    /* access modifiers changed from: private */
    public boolean connected = true;
    /* access modifiers changed from: private */
    public Handler handler;
    private Context zzaFq;
    /* access modifiers changed from: private */
    public zzau zzaFr;
    private volatile zzas zzaFs;
    /* access modifiers changed from: private */
    public int zzaFt = 1800000;
    private boolean zzaFu = true;
    private boolean zzaFv = false;
    private boolean zzaFw = true;
    private zzav zzaFx = new zzav() {
        public void zzai(boolean z) {
            zzcu.this.zzd(z, zzcu.this.connected);
        }
    };
    private zzbl zzaFy;
    /* access modifiers changed from: private */
    public boolean zzaFz = false;

    private zzcu() {
    }

    public static zzcu zzxF() {
        if (zzaFA == null) {
            zzaFA = new zzcu();
        }
        return zzaFA;
    }

    private void zzxG() {
        this.zzaFy = new zzbl(this);
        this.zzaFy.zzar(this.zzaFq);
    }

    private void zzxH() {
        this.handler = new Handler(this.zzaFq.getMainLooper(), new Handler.Callback() {
            public boolean handleMessage(Message msg) {
                if (1 == msg.what && zzcu.zzaFp.equals(msg.obj)) {
                    zzcu.this.dispatch();
                    if (zzcu.this.zzaFt > 0 && !zzcu.this.zzaFz) {
                        zzcu.this.handler.sendMessageDelayed(zzcu.this.handler.obtainMessage(1, zzcu.zzaFp), (long) zzcu.this.zzaFt);
                    }
                }
                return true;
            }
        });
        if (this.zzaFt > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, zzaFp), (long) this.zzaFt);
        }
    }

    public synchronized void dispatch() {
        if (!this.zzaFv) {
            zzbg.zzam("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzaFu = true;
        } else {
            this.zzaFs.zzf(new Runnable() {
                public void run() {
                    zzcu.this.zzaFr.dispatch();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void zza(Context context, zzas zzas) {
        if (this.zzaFq == null) {
            this.zzaFq = context.getApplicationContext();
            if (this.zzaFs == null) {
                this.zzaFs = zzas;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzaj(boolean z) {
        zzd(this.zzaFz, z);
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzd(boolean z, boolean z2) {
        if (!(this.zzaFz == z && this.connected == z2)) {
            if (z || !z2) {
                if (this.zzaFt > 0) {
                    this.handler.removeMessages(1, zzaFp);
                }
            }
            if (!z && z2 && this.zzaFt > 0) {
                this.handler.sendMessageDelayed(this.handler.obtainMessage(1, zzaFp), (long) this.zzaFt);
            }
            zzbg.zzam("PowerSaveMode " + ((z || !z2) ? "initiated." : "terminated."));
            this.zzaFz = z;
            this.connected = z2;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzgA() {
        if (!this.zzaFz && this.connected && this.zzaFt > 0) {
            this.handler.removeMessages(1, zzaFp);
            this.handler.sendMessage(this.handler.obtainMessage(1, zzaFp));
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized zzau zzxI() {
        if (this.zzaFr == null) {
            if (this.zzaFq == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzaFr = new zzby(this.zzaFx, this.zzaFq);
        }
        if (this.handler == null) {
            zzxH();
        }
        this.zzaFv = true;
        if (this.zzaFu) {
            dispatch();
            this.zzaFu = false;
        }
        if (this.zzaFy == null && this.zzaFw) {
            zzxG();
        }
        return this.zzaFr;
    }
}
