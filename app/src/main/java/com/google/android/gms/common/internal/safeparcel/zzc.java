package com.google.android.gms.common.internal.safeparcel;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzv;

public final class zzc {
    public static <T extends SafeParcelable> T zza(Intent intent, String str, Parcelable.Creator<T> creator) {
        byte[] byteArrayExtra = intent.getByteArrayExtra(str);
        if (byteArrayExtra == null) {
            return null;
        }
        return zza(byteArrayExtra, creator);
    }

    public static <T extends SafeParcelable> T zza(byte[] bArr, Parcelable.Creator<T> creator) {
        zzv.zzr(creator);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T t = (SafeParcelable) creator.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }

    public static <T extends SafeParcelable> void zza(T t, Intent intent, String str) {
        intent.putExtra(str, zza(t));
    }

    public static <T extends SafeParcelable> byte[] zza(T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
