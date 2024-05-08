package com.google.android.gms.internal;

import com.google.android.gms.internal.zzns;
import java.io.IOException;

public abstract class zzns<M extends zzns<M>> extends zzny {
    protected zznu zzaNI;

    public final <T> T zza(zznt<M, T> zznt) {
        zznv zzjC;
        if (this.zzaNI == null || (zzjC = this.zzaNI.zzjC(zzob.zzjG(zznt.tag))) == null) {
            return null;
        }
        return zzjC.zzb(zznt);
    }

    public void zza(zznr zznr) throws IOException {
        if (this.zzaNI != null) {
            for (int i = 0; i < this.zzaNI.size(); i++) {
                this.zzaNI.zzjD(i).zza(zznr);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zznq zznq, int i) throws IOException {
        int position = zznq.getPosition();
        if (!zznq.zzjl(i)) {
            return false;
        }
        int zzjG = zzob.zzjG(i);
        zzoa zzoa = new zzoa(i, zznq.zzw(position, zznq.getPosition() - position));
        zznv zznv = null;
        if (this.zzaNI == null) {
            this.zzaNI = new zznu();
        } else {
            zznv = this.zzaNI.zzjC(zzjG);
        }
        if (zznv == null) {
            zznv = new zznv();
            this.zzaNI.zza(zzjG, zznv);
        }
        zznv.zza(zzoa);
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(M m) {
        return (this.zzaNI == null || this.zzaNI.isEmpty()) ? m.zzaNI == null || m.zzaNI.isEmpty() : this.zzaNI.equals(m.zzaNI);
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        if (this.zzaNI == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzaNI.size(); i2++) {
            i += this.zzaNI.zzjD(i2).zzc();
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public final int zzzP() {
        if (this.zzaNI == null || this.zzaNI.isEmpty()) {
            return 0;
        }
        return this.zzaNI.hashCode();
    }

    /* renamed from: zzzQ */
    public M zzzR() throws CloneNotSupportedException {
        M m = (zzns) super.clone();
        zznw.zza(this, (zzns) m);
        return m;
    }
}
