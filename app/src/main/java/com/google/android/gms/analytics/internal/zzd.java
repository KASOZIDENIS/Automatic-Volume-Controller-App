package com.google.android.gms.analytics.internal;

public abstract class zzd extends zzc {
    private boolean zzFE;
    private boolean zzFF;

    protected zzd(zze zze) {
        super(zze);
    }

    public boolean isInitialized() {
        return this.zzFE && !this.zzFF;
    }

    public void zzfV() {
        zzgb();
        this.zzFE = true;
    }

    /* access modifiers changed from: protected */
    public void zzgR() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzgb();
}
