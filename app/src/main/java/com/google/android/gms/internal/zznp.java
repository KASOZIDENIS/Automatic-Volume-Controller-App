package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;

public class zznp {
    private final byte[] zzaNv = new byte[256];
    private int zzaNw;
    private int zzaNx;

    public zznp(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzaNv[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b = (b + this.zzaNv[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = this.zzaNv[i2];
            this.zzaNv[i2] = this.zzaNv[b];
            this.zzaNv[b] = b2;
        }
        this.zzaNw = 0;
        this.zzaNx = 0;
    }

    public void zzu(byte[] bArr) {
        int i = this.zzaNw;
        int i2 = this.zzaNx;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & MotionEventCompat.ACTION_MASK;
            i2 = (i2 + this.zzaNv[i]) & MotionEventCompat.ACTION_MASK;
            byte b = this.zzaNv[i];
            this.zzaNv[i] = this.zzaNv[i2];
            this.zzaNv[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzaNv[(this.zzaNv[i] + this.zzaNv[i2]) & MotionEventCompat.ACTION_MASK]);
        }
        this.zzaNw = i;
        this.zzaNx = i2;
    }
}
