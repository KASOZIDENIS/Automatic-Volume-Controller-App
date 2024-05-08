package com.google.android.gms.internal;

import android.support.v7.internal.widget.ActivityChooserView;
import java.io.IOException;

public final class zznq {
    private final byte[] buffer;
    private int zzaNA;
    private int zzaNB;
    private int zzaNC;
    private int zzaND = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int zzaNE;
    private int zzaNF = 64;
    private int zzaNG = 67108864;
    private int zzaNy;
    private int zzaNz;

    private zznq(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzaNy = i;
        this.zzaNz = i + i2;
        this.zzaNB = i;
    }

    public static long zzU(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public static zznq zza(byte[] bArr, int i, int i2) {
        return new zznq(bArr, i, i2);
    }

    public static int zzjm(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static zznq zzv(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    private void zzzJ() {
        this.zzaNz += this.zzaNA;
        int i = this.zzaNz;
        if (i > this.zzaND) {
            this.zzaNA = i - this.zzaND;
            this.zzaNz -= this.zzaNA;
            return;
        }
        this.zzaNA = 0;
    }

    public int getPosition() {
        return this.zzaNB - this.zzaNy;
    }

    public byte[] readBytes() throws IOException {
        int zzzF = zzzF();
        if (zzzF > this.zzaNz - this.zzaNB || zzzF <= 0) {
            return zzjq(zzzF);
        }
        byte[] bArr = new byte[zzzF];
        System.arraycopy(this.buffer, this.zzaNB, bArr, 0, zzzF);
        this.zzaNB = zzzF + this.zzaNB;
        return bArr;
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(zzzI());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(zzzH());
    }

    public String readString() throws IOException {
        int zzzF = zzzF();
        if (zzzF > this.zzaNz - this.zzaNB || zzzF <= 0) {
            return new String(zzjq(zzzF), "UTF-8");
        }
        String str = new String(this.buffer, this.zzaNB, zzzF, "UTF-8");
        this.zzaNB = zzzF + this.zzaNB;
        return str;
    }

    public void zza(zzny zzny) throws IOException {
        int zzzF = zzzF();
        if (this.zzaNE >= this.zzaNF) {
            throw zznx.zzAa();
        }
        int zzjn = zzjn(zzzF);
        this.zzaNE++;
        zzny.zzb(this);
        zzjk(0);
        this.zzaNE--;
        zzjo(zzjn);
    }

    public void zza(zzny zzny, int i) throws IOException {
        if (this.zzaNE >= this.zzaNF) {
            throw zznx.zzAa();
        }
        this.zzaNE++;
        zzny.zzb(this);
        zzjk(zzob.zzC(i, 4));
        this.zzaNE--;
    }

    public void zzjk(int i) throws zznx {
        if (this.zzaNC != i) {
            throw zznx.zzzY();
        }
    }

    public boolean zzjl(int i) throws IOException {
        switch (zzob.zzjF(i)) {
            case 0:
                zzzB();
                return true;
            case 1:
                zzzI();
                return true;
            case 2:
                zzjr(zzzF());
                return true;
            case 3:
                zzzz();
                zzjk(zzob.zzC(zzob.zzjG(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                zzzH();
                return true;
            default:
                throw zznx.zzzZ();
        }
    }

    public int zzjn(int i) throws zznx {
        if (i < 0) {
            throw zznx.zzzV();
        }
        int i2 = this.zzaNB + i;
        int i3 = this.zzaND;
        if (i2 > i3) {
            throw zznx.zzzU();
        }
        this.zzaND = i2;
        zzzJ();
        return i3;
    }

    public void zzjo(int i) {
        this.zzaND = i;
        zzzJ();
    }

    public void zzjp(int i) {
        if (i > this.zzaNB - this.zzaNy) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzaNB - this.zzaNy));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzaNB = this.zzaNy + i;
        }
    }

    public byte[] zzjq(int i) throws IOException {
        if (i < 0) {
            throw zznx.zzzV();
        } else if (this.zzaNB + i > this.zzaND) {
            zzjr(this.zzaND - this.zzaNB);
            throw zznx.zzzU();
        } else if (i <= this.zzaNz - this.zzaNB) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, this.zzaNB, bArr, 0, i);
            this.zzaNB += i;
            return bArr;
        } else {
            throw zznx.zzzU();
        }
    }

    public void zzjr(int i) throws IOException {
        if (i < 0) {
            throw zznx.zzzV();
        } else if (this.zzaNB + i > this.zzaND) {
            zzjr(this.zzaND - this.zzaNB);
            throw zznx.zzzU();
        } else if (i <= this.zzaNz - this.zzaNB) {
            this.zzaNB += i;
        } else {
            throw zznx.zzzU();
        }
    }

    public byte[] zzw(int i, int i2) {
        if (i2 == 0) {
            return zzob.zzaOc;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzaNy + i, bArr, 0, i2);
        return bArr;
    }

    public long zzzA() throws IOException {
        return zzzG();
    }

    public int zzzB() throws IOException {
        return zzzF();
    }

    public boolean zzzC() throws IOException {
        return zzzF() != 0;
    }

    public int zzzD() throws IOException {
        return zzjm(zzzF());
    }

    public long zzzE() throws IOException {
        return zzU(zzzG());
    }

    public int zzzF() throws IOException {
        byte zzzM = zzzM();
        if (zzzM >= 0) {
            return zzzM;
        }
        byte b = zzzM & Byte.MAX_VALUE;
        byte zzzM2 = zzzM();
        if (zzzM2 >= 0) {
            return b | (zzzM2 << 7);
        }
        byte b2 = b | ((zzzM2 & Byte.MAX_VALUE) << 7);
        byte zzzM3 = zzzM();
        if (zzzM3 >= 0) {
            return b2 | (zzzM3 << 14);
        }
        byte b3 = b2 | ((zzzM3 & Byte.MAX_VALUE) << 14);
        byte zzzM4 = zzzM();
        if (zzzM4 >= 0) {
            return b3 | (zzzM4 << 21);
        }
        byte b4 = b3 | ((zzzM4 & Byte.MAX_VALUE) << 21);
        byte zzzM5 = zzzM();
        byte b5 = b4 | (zzzM5 << 28);
        if (zzzM5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzzM() >= 0) {
                return b5;
            }
        }
        throw zznx.zzzW();
    }

    public long zzzG() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzzM = zzzM();
            j |= ((long) (zzzM & Byte.MAX_VALUE)) << i;
            if ((zzzM & 128) == 0) {
                return j;
            }
        }
        throw zznx.zzzW();
    }

    public int zzzH() throws IOException {
        return (zzzM() & 255) | ((zzzM() & 255) << 8) | ((zzzM() & 255) << 16) | ((zzzM() & 255) << 24);
    }

    public long zzzI() throws IOException {
        byte zzzM = zzzM();
        byte zzzM2 = zzzM();
        return ((((long) zzzM2) & 255) << 8) | (((long) zzzM) & 255) | ((((long) zzzM()) & 255) << 16) | ((((long) zzzM()) & 255) << 24) | ((((long) zzzM()) & 255) << 32) | ((((long) zzzM()) & 255) << 40) | ((((long) zzzM()) & 255) << 48) | ((((long) zzzM()) & 255) << 56);
    }

    public int zzzK() {
        if (this.zzaND == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzaND - this.zzaNB;
    }

    public boolean zzzL() {
        return this.zzaNB == this.zzaNz;
    }

    public byte zzzM() throws IOException {
        if (this.zzaNB == this.zzaNz) {
            throw zznx.zzzU();
        }
        byte[] bArr = this.buffer;
        int i = this.zzaNB;
        this.zzaNB = i + 1;
        return bArr[i];
    }

    public int zzzy() throws IOException {
        if (zzzL()) {
            this.zzaNC = 0;
            return 0;
        }
        this.zzaNC = zzzF();
        if (this.zzaNC != 0) {
            return this.zzaNC;
        }
        throw zznx.zzzX();
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void zzzz() throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.zzzy()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.zzjl(r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zznq.zzzz():void");
    }
}
