package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zznv implements Cloneable {
    private zznt<?, ?> zzaNP;
    private Object zzaNQ;
    private List<zzoa> zzaNR = new ArrayList();

    zznv() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzc()];
        zza(zznr.zzw(bArr));
        return bArr;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof zznv)) {
            return false;
        }
        zznv zznv = (zznv) o;
        if (this.zzaNQ == null || zznv.zzaNQ == null) {
            if (this.zzaNR != null && zznv.zzaNR != null) {
                return this.zzaNR.equals(zznv.zzaNR);
            }
            try {
                return Arrays.equals(toByteArray(), zznv.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzaNP != zznv.zzaNP) {
            return false;
        } else {
            if (!this.zzaNP.zzaNJ.isArray()) {
                return this.zzaNQ.equals(zznv.zzaNQ);
            }
            if (this.zzaNQ instanceof byte[]) {
                return Arrays.equals((byte[]) this.zzaNQ, (byte[]) zznv.zzaNQ);
            }
            if (this.zzaNQ instanceof int[]) {
                return Arrays.equals((int[]) this.zzaNQ, (int[]) zznv.zzaNQ);
            }
            if (this.zzaNQ instanceof long[]) {
                return Arrays.equals((long[]) this.zzaNQ, (long[]) zznv.zzaNQ);
            }
            if (this.zzaNQ instanceof float[]) {
                return Arrays.equals((float[]) this.zzaNQ, (float[]) zznv.zzaNQ);
            }
            if (this.zzaNQ instanceof double[]) {
                return Arrays.equals((double[]) this.zzaNQ, (double[]) zznv.zzaNQ);
            }
            return this.zzaNQ instanceof boolean[] ? Arrays.equals((boolean[]) this.zzaNQ, (boolean[]) zznv.zzaNQ) : Arrays.deepEquals((Object[]) this.zzaNQ, (Object[]) zznv.zzaNQ);
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zznr zznr) throws IOException {
        if (this.zzaNQ != null) {
            this.zzaNP.zza(this.zzaNQ, zznr);
            return;
        }
        for (zzoa zza : this.zzaNR) {
            zza.zza(zznr);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzoa zzoa) {
        this.zzaNR.add(zzoa);
    }

    /* access modifiers changed from: package-private */
    public <T> T zzb(zznt<?, T> zznt) {
        if (this.zzaNQ == null) {
            this.zzaNP = zznt;
            this.zzaNQ = zznt.zzy(this.zzaNR);
            this.zzaNR = null;
        } else if (this.zzaNP != zznt) {
            throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
        }
        return this.zzaNQ;
    }

    /* access modifiers changed from: package-private */
    public int zzc() {
        int i = 0;
        if (this.zzaNQ != null) {
            return this.zzaNP.zzM(this.zzaNQ);
        }
        Iterator<zzoa> it = this.zzaNR.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = it.next().zzc() + i2;
        }
    }

    /* renamed from: zzzT */
    public final zznv clone() {
        zznv zznv = new zznv();
        try {
            zznv.zzaNP = this.zzaNP;
            if (this.zzaNR == null) {
                zznv.zzaNR = null;
            } else {
                zznv.zzaNR.addAll(this.zzaNR);
            }
            if (this.zzaNQ != null) {
                if (this.zzaNQ instanceof zzny) {
                    zznv.zzaNQ = ((zzny) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof byte[]) {
                    zznv.zzaNQ = ((byte[]) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.zzaNQ;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zznv.zzaNQ = bArr2;
                    for (int i = 0; i < bArr.length; i++) {
                        bArr2[i] = (byte[]) bArr[i].clone();
                    }
                } else if (this.zzaNQ instanceof boolean[]) {
                    zznv.zzaNQ = ((boolean[]) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof int[]) {
                    zznv.zzaNQ = ((int[]) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof long[]) {
                    zznv.zzaNQ = ((long[]) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof float[]) {
                    zznv.zzaNQ = ((float[]) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof double[]) {
                    zznv.zzaNQ = ((double[]) this.zzaNQ).clone();
                } else if (this.zzaNQ instanceof zzny[]) {
                    zzny[] zznyArr = (zzny[]) this.zzaNQ;
                    zzny[] zznyArr2 = new zzny[zznyArr.length];
                    zznv.zzaNQ = zznyArr2;
                    for (int i2 = 0; i2 < zznyArr.length; i2++) {
                        zznyArr2[i2] = zznyArr[i2].clone();
                    }
                }
            }
            return zznv;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
