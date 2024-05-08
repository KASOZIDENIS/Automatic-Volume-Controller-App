package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.internal.zzv;

public abstract class zzc {
    protected final DataHolder zzPy;
    protected int zzRw;
    private int zzRx;

    public zzc(DataHolder dataHolder, int i) {
        this.zzPy = (DataHolder) zzv.zzr(dataHolder);
        zzaB(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzu.equal(Integer.valueOf(zzc.zzRw), Integer.valueOf(this.zzRw)) && zzu.equal(Integer.valueOf(zzc.zzRx), Integer.valueOf(this.zzRx)) && zzc.zzPy == this.zzPy;
    }

    /* access modifiers changed from: protected */
    public boolean getBoolean(String column) {
        return this.zzPy.zze(column, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public byte[] getByteArray(String column) {
        return this.zzPy.zzg(column, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public float getFloat(String column) {
        return this.zzPy.zzf(column, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public int getInteger(String column) {
        return this.zzPy.zzc(column, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public long getLong(String column) {
        return this.zzPy.zzb(column, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public String getString(String column) {
        return this.zzPy.zzd(column, this.zzRw, this.zzRx);
    }

    public int hashCode() {
        return zzu.hashCode(Integer.valueOf(this.zzRw), Integer.valueOf(this.zzRx), this.zzPy);
    }

    public boolean isDataValid() {
        return !this.zzPy.isClosed();
    }

    /* access modifiers changed from: protected */
    public void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.zzPy.zza(str, this.zzRw, this.zzRx, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    public void zzaB(int i) {
        zzv.zzP(i >= 0 && i < this.zzPy.getCount());
        this.zzRw = i;
        this.zzRx = this.zzPy.zzaD(this.zzRw);
    }

    public boolean zzbF(String str) {
        return this.zzPy.zzbF(str);
    }

    /* access modifiers changed from: protected */
    public Uri zzbG(String str) {
        return this.zzPy.zzh(str, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public boolean zzbH(String str) {
        return this.zzPy.zzi(str, this.zzRw, this.zzRx);
    }

    /* access modifiers changed from: protected */
    public int zzlp() {
        return this.zzRw;
    }
}
