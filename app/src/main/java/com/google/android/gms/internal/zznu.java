package com.google.android.gms.internal;

class zznu implements Cloneable {
    private static final zznv zzaNL = new zznv();
    private int mSize;
    private boolean zzaNM;
    private int[] zzaNN;
    private zznv[] zzaNO;

    public zznu() {
        this(10);
    }

    public zznu(int i) {
        this.zzaNM = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzaNN = new int[idealIntArraySize];
        this.zzaNO = new zznv[idealIntArraySize];
        this.mSize = 0;
    }

    private void gc() {
        int i = this.mSize;
        int[] iArr = this.zzaNN;
        zznv[] zznvArr = this.zzaNO;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            zznv zznv = zznvArr[i3];
            if (zznv != zzaNL) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    zznvArr[i2] = zznv;
                    zznvArr[i3] = null;
                }
                i2++;
            }
        }
        this.zzaNM = false;
        this.mSize = i2;
    }

    private int idealByteArraySize(int need) {
        for (int i = 4; i < 32; i++) {
            if (need <= (1 << i) - 12) {
                return (1 << i) - 12;
            }
        }
        return need;
    }

    private int idealIntArraySize(int need) {
        return idealByteArraySize(need * 4) / 4;
    }

    private boolean zza(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean zza(zznv[] zznvArr, zznv[] zznvArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!zznvArr[i2].equals(zznvArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int zzjE(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzaNN[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof zznu)) {
            return false;
        }
        zznu zznu = (zznu) o;
        if (size() != zznu.size()) {
            return false;
        }
        return zza(this.zzaNN, zznu.zzaNN, this.mSize) && zza(this.zzaNO, zznu.zzaNO, this.mSize);
    }

    public int hashCode() {
        if (this.zzaNM) {
            gc();
        }
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzaNN[i2]) * 31) + this.zzaNO[i2].hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        if (this.zzaNM) {
            gc();
        }
        return this.mSize;
    }

    public void zza(int i, zznv zznv) {
        int zzjE = zzjE(i);
        if (zzjE >= 0) {
            this.zzaNO[zzjE] = zznv;
            return;
        }
        int i2 = zzjE ^ -1;
        if (i2 >= this.mSize || this.zzaNO[i2] != zzaNL) {
            if (this.zzaNM && this.mSize >= this.zzaNN.length) {
                gc();
                i2 = zzjE(i) ^ -1;
            }
            if (this.mSize >= this.zzaNN.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zznv[] zznvArr = new zznv[idealIntArraySize];
                System.arraycopy(this.zzaNN, 0, iArr, 0, this.zzaNN.length);
                System.arraycopy(this.zzaNO, 0, zznvArr, 0, this.zzaNO.length);
                this.zzaNN = iArr;
                this.zzaNO = zznvArr;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.zzaNN, i2, this.zzaNN, i2 + 1, this.mSize - i2);
                System.arraycopy(this.zzaNO, i2, this.zzaNO, i2 + 1, this.mSize - i2);
            }
            this.zzaNN[i2] = i;
            this.zzaNO[i2] = zznv;
            this.mSize++;
            return;
        }
        this.zzaNN[i2] = i;
        this.zzaNO[i2] = zznv;
    }

    public zznv zzjC(int i) {
        int zzjE = zzjE(i);
        if (zzjE < 0 || this.zzaNO[zzjE] == zzaNL) {
            return null;
        }
        return this.zzaNO[zzjE];
    }

    public zznv zzjD(int i) {
        if (this.zzaNM) {
            gc();
        }
        return this.zzaNO[i];
    }

    /* renamed from: zzzS */
    public final zznu clone() {
        int size = size();
        zznu zznu = new zznu(size);
        System.arraycopy(this.zzaNN, 0, zznu.zzaNN, 0, size);
        for (int i = 0; i < size; i++) {
            if (this.zzaNO[i] != null) {
                zznu.zzaNO[i] = this.zzaNO[i].clone();
            }
        }
        zznu.mSize = size;
        return zznu;
    }
}
