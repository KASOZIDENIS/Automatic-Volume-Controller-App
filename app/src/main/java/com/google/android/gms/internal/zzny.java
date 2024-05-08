package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzny {
    protected volatile int zzaNT = -1;

    public static final <T extends zzny> T zza(T t, byte[] bArr) throws zznx {
        return zzb(t, bArr, 0, bArr.length);
    }

    public static final void zza(zzny zzny, byte[] bArr, int i, int i2) {
        try {
            zznr zzb = zznr.zzb(bArr, i, i2);
            zzny.zza(zzb);
            zzb.zzzO();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzny> T zzb(T t, byte[] bArr, int i, int i2) throws zznx {
        try {
            zznq zza = zznq.zza(bArr, i, i2);
            t.zzb(zza);
            zza.zzjk(0);
            return t;
        } catch (zznx e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] zzf(zzny zzny) {
        byte[] bArr = new byte[zzny.zzAc()];
        zza(zzny, bArr, 0, bArr.length);
        return bArr;
    }

    public String toString() {
        return zznz.zzg(this);
    }

    public int zzAb() {
        if (this.zzaNT < 0) {
            zzAc();
        }
        return this.zzaNT;
    }

    public int zzAc() {
        int zzc = zzc();
        this.zzaNT = zzc;
        return zzc;
    }

    public void zza(zznr zznr) throws IOException {
    }

    public abstract zzny zzb(zznq zznq) throws IOException;

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    /* renamed from: zzzR */
    public zzny clone() throws CloneNotSupportedException {
        return (zzny) super.clone();
    }
}
