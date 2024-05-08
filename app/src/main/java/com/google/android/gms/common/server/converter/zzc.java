package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.server.converter.StringToIntConverter;

public class zzc implements Parcelable.Creator<StringToIntConverter.Entry> {
    static void zza(StringToIntConverter.Entry entry, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, entry.versionCode);
        zzb.zza(parcel, 2, entry.zzUw, false);
        zzb.zzc(parcel, 3, entry.zzUx);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzQ */
    public StringToIntConverter.Entry createFromParcel(Parcel parcel) {
        int i = 0;
        int zzL = zza.zzL(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    str = zza.zzo(parcel, zzK);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new StringToIntConverter.Entry(i2, str, i);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzba */
    public StringToIntConverter.Entry[] newArray(int i) {
        return new StringToIntConverter.Entry[i];
    }
}
