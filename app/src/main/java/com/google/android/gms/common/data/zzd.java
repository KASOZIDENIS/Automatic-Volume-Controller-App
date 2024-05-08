package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzRy = {"data"};
    private final Parcelable.Creator<T> zzRz;

    public zzd(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zzRz = creator;
    }

    /* renamed from: zzaC */
    public T get(int i) {
        byte[] zzg = this.zzPy.zzg("data", i, this.zzPy.zzaD(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) this.zzRz.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}
