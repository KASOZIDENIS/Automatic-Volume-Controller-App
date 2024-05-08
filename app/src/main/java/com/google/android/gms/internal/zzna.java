package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.tagmanager.zzbg;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class zzna {
    private boolean mClosed;
    private String zzaCO;
    private final ScheduledExecutorService zzaEJ;
    private ScheduledFuture<?> zzaEL;

    public zzna() {
        this(Executors.newSingleThreadScheduledExecutor());
    }

    public zzna(String str) {
        this(Executors.newSingleThreadScheduledExecutor());
        this.zzaCO = str;
    }

    zzna(ScheduledExecutorService scheduledExecutorService) {
        this.zzaEL = null;
        this.zzaCO = null;
        this.zzaEJ = scheduledExecutorService;
        this.mClosed = false;
    }

    public void zza(Context context, zzmo zzmo, long j, zzmy zzmy) {
        synchronized (this) {
            zzbg.zzam("ResourceLoaderScheduler: Loading new resource.");
            if (this.zzaEL == null) {
                this.zzaEL = this.zzaEJ.schedule(this.zzaCO != null ? new zzmz(context, zzmo, zzmy, this.zzaCO) : new zzmz(context, zzmo, zzmy), j, TimeUnit.MILLISECONDS);
            }
        }
    }
}
