package com.google.android.gms.internal;

import java.io.IOException;

class zzp implements zzn {
    private zznr zzlT;
    private byte[] zzlU;
    private final int zzlV;

    public zzp(int i) {
        this.zzlV = i;
        reset();
    }

    public void reset() {
        this.zzlU = new byte[this.zzlV];
        this.zzlT = zznr.zzw(this.zzlU);
    }

    public byte[] zzD() throws IOException {
        int zzzN = this.zzlT.zzzN();
        if (zzzN < 0) {
            throw new IOException();
        } else if (zzzN == 0) {
            return this.zzlU;
        } else {
            byte[] bArr = new byte[(this.zzlU.length - zzzN)];
            System.arraycopy(this.zzlU, 0, bArr, 0, bArr.length);
            return bArr;
        }
    }

    public void zzb(int i, long j) throws IOException {
        this.zzlT.zzb(i, j);
    }

    public void zzb(int i, String str) throws IOException {
        this.zzlT.zzb(i, str);
    }
}
