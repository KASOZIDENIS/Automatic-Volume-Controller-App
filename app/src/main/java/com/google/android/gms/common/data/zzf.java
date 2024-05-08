package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T> extends AbstractDataBuffer<T> {
    private boolean zzRO = false;
    private ArrayList<Integer> zzRP;

    protected zzf(DataHolder dataHolder) {
        super(dataHolder);
    }

    private void zzlu() {
        synchronized (this) {
            if (!this.zzRO) {
                int count = this.zzPy.getCount();
                this.zzRP = new ArrayList<>();
                if (count > 0) {
                    this.zzRP.add(0);
                    String zzlt = zzlt();
                    String zzd = this.zzPy.zzd(zzlt, 0, this.zzPy.zzaD(0));
                    int i = 1;
                    while (i < count) {
                        int zzaD = this.zzPy.zzaD(i);
                        String zzd2 = this.zzPy.zzd(zzlt, i, zzaD);
                        if (zzd2 == null) {
                            throw new NullPointerException("Missing value for markerColumn: " + zzlt + ", at row: " + i + ", for window: " + zzaD);
                        }
                        if (!zzd2.equals(zzd)) {
                            this.zzRP.add(Integer.valueOf(i));
                        } else {
                            zzd2 = zzd;
                        }
                        i++;
                        zzd = zzd2;
                    }
                }
                this.zzRO = true;
            }
        }
    }

    public final T get(int position) {
        zzlu();
        return zzh(zzaG(position), zzaH(position));
    }

    public int getCount() {
        zzlu();
        return this.zzRP.size();
    }

    /* access modifiers changed from: package-private */
    public int zzaG(int i) {
        if (i >= 0 && i < this.zzRP.size()) {
            return this.zzRP.get(i).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    /* access modifiers changed from: protected */
    public int zzaH(int i) {
        if (i < 0 || i == this.zzRP.size()) {
            return 0;
        }
        int count = i == this.zzRP.size() + -1 ? this.zzPy.getCount() - this.zzRP.get(i).intValue() : this.zzRP.get(i + 1).intValue() - this.zzRP.get(i).intValue();
        if (count != 1) {
            return count;
        }
        int zzaG = zzaG(i);
        int zzaD = this.zzPy.zzaD(zzaG);
        String zzlv = zzlv();
        if (zzlv == null || this.zzPy.zzd(zzlv, zzaG, zzaD) != null) {
            return count;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract T zzh(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String zzlt();

    /* access modifiers changed from: protected */
    public String zzlv() {
        return null;
    }
}
