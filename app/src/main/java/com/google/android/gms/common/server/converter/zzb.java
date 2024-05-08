package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import java.util.ArrayList;

public class zzb implements Parcelable.Creator<StringToIntConverter> {
    static void zza(StringToIntConverter stringToIntConverter, Parcel parcel, int i) {
        int zzM = com.google.android.gms.common.internal.safeparcel.zzb.zzM(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, stringToIntConverter.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, stringToIntConverter.zzmv(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzP */
    public StringToIntConverter createFromParcel(Parcel parcel) {
        int zzL = zza.zzL(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    arrayList = zza.zzc(parcel, zzK, StringToIntConverter.Entry.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new StringToIntConverter(i, arrayList);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaZ */
    public StringToIntConverter[] newArray(int i) {
        return new StringToIntConverter[i];
    }
}
