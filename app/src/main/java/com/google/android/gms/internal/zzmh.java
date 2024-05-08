package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.TagManager;
import java.util.HashSet;
import java.util.Set;

public class zzmh {
    private static zzmh zzaGH;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mStarted;
    private final Set<zza> zzaGI = new HashSet();
    private TagManager zzaGJ = null;
    /* access modifiers changed from: private */
    public zzmg zznw;

    public interface zza {
        void zzaI();
    }

    zzmh(Context context, TagManager tagManager) {
        this.mContext = context;
        this.zzaGJ = tagManager;
    }

    public static zzmh zzat(Context context) {
        zzv.zzr(context);
        if (zzaGH == null) {
            synchronized (zzmh.class) {
                if (zzaGH == null) {
                    zzaGH = new zzmh(context, TagManager.getInstance(context.getApplicationContext()));
                }
            }
        }
        return zzaGH;
    }

    /* access modifiers changed from: private */
    public void zzfm() {
        synchronized (this) {
            for (zza zzaI : this.zzaGI) {
                zzaI.zzaI();
            }
        }
    }

    public void start() throws IllegalStateException {
        synchronized (this) {
            if (this.mStarted) {
                throw new IllegalStateException("Method start() has already been called");
            } else if (this.zznw == null) {
                throw new IllegalStateException("No settings configured");
            } else {
                this.mStarted = true;
                this.zzaGJ.zzc(this.zznw.zzxZ(), -1, "admob").setResultCallback(new ResultCallback<ContainerHolder>() {
                    /* renamed from: zza */
                    public void onResult(ContainerHolder containerHolder) {
                        zzmg unused = zzmh.this.zznw = new zzmf(zzmh.this.mContext, containerHolder.getStatus().isSuccess() ? containerHolder.getContainer() : null, zzmh.this.zzyd()).zzxX();
                        zzmh.this.zzfm();
                    }
                });
            }
        }
    }

    public void zza(zzmg zzmg) {
        synchronized (this) {
            if (this.mStarted) {
                throw new IllegalStateException("Settings can't be changed after TagManager has been started");
            }
            this.zznw = zzmg;
        }
    }

    public void zza(zza zza2) {
        synchronized (this) {
            this.zzaGI.add(zza2);
        }
    }

    public zzmg zzyd() {
        zzmg zzmg;
        synchronized (this) {
            zzmg = this.zznw;
        }
        return zzmg;
    }
}
