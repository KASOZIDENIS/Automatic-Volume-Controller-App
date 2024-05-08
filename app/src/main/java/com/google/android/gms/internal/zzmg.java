package com.google.android.gms.internal;

public final class zzmg {
    private final String zzEm;
    private final boolean zzaGE;
    private final boolean zzaGF;
    private final String zzaGG;

    public static final class zza {
        /* access modifiers changed from: private */
        public String zzEm;
        /* access modifiers changed from: private */
        public boolean zzaGE = true;
        /* access modifiers changed from: private */
        public boolean zzaGF = false;
        /* access modifiers changed from: private */
        public final String zzaGG;

        public zza(String str) {
            this.zzaGG = str;
        }

        public zza zzak(boolean z) {
            this.zzaGE = z;
            return this;
        }

        public zza zzal(boolean z) {
            this.zzaGF = z;
            return this;
        }

        public zza zzek(String str) {
            this.zzEm = str;
            return this;
        }

        public zzmg zzyc() {
            return new zzmg(this);
        }
    }

    private zzmg(zza zza2) {
        this.zzaGG = zza2.zzaGG;
        this.zzaGE = zza2.zzaGE;
        this.zzaGF = zza2.zzaGF;
        this.zzEm = zza2.zzEm;
    }

    public String zzjs() {
        return this.zzEm;
    }

    public String zzxZ() {
        return this.zzaGG;
    }

    public boolean zzya() {
        return this.zzaGE;
    }

    public boolean zzyb() {
        return this.zzaGF;
    }
}
