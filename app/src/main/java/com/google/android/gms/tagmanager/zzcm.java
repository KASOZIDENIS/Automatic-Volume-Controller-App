package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzc;
import com.google.android.gms.tagmanager.zzp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class zzcm implements zzp.zze {
    private boolean mClosed;
    /* access modifiers changed from: private */
    public final Context mContext;
    private String zzaCH;
    /* access modifiers changed from: private */
    public final String zzaCk;
    private zzbf<zzc.zzj> zzaEG;
    private zzs zzaEH;
    private final ScheduledExecutorService zzaEJ;
    private final zza zzaEK;
    private ScheduledFuture<?> zzaEL;

    interface zza {
        zzcl zza(zzs zzs);
    }

    interface zzb {
        ScheduledExecutorService zzxs();
    }

    public zzcm(Context context, String str, zzs zzs) {
        this(context, str, zzs, (zzb) null, (zza) null);
    }

    zzcm(Context context, String str, zzs zzs, zzb zzb2, zza zza2) {
        this.zzaEH = zzs;
        this.mContext = context;
        this.zzaCk = str;
        this.zzaEJ = (zzb2 == null ? new zzb() {
            public ScheduledExecutorService zzxs() {
                return Executors.newSingleThreadScheduledExecutor();
            }
        } : zzb2).zzxs();
        if (zza2 == null) {
            this.zzaEK = new zza() {
                public zzcl zza(zzs zzs) {
                    return new zzcl(zzcm.this.mContext, zzcm.this.zzaCk, zzs);
                }
            };
        } else {
            this.zzaEK = zza2;
        }
    }

    private zzcl zzdU(String str) {
        zzcl zza2 = this.zzaEK.zza(this.zzaEH);
        zza2.zza(this.zzaEG);
        zza2.zzdE(this.zzaCH);
        zza2.zzdT(str);
        return zza2;
    }

    private synchronized void zzxr() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    public synchronized void release() {
        zzxr();
        if (this.zzaEL != null) {
            this.zzaEL.cancel(false);
        }
        this.zzaEJ.shutdown();
        this.mClosed = true;
    }

    public synchronized void zza(zzbf<zzc.zzj> zzbf) {
        zzxr();
        this.zzaEG = zzbf;
    }

    public synchronized void zzdE(String str) {
        zzxr();
        this.zzaCH = str;
    }

    public synchronized void zzf(long j, String str) {
        zzbg.zzam("loadAfterDelay: containerId=" + this.zzaCk + " delay=" + j);
        zzxr();
        if (this.zzaEG == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.zzaEL != null) {
            this.zzaEL.cancel(false);
        }
        this.zzaEL = this.zzaEJ.schedule(zzdU(str), j, TimeUnit.MILLISECONDS);
    }
}
