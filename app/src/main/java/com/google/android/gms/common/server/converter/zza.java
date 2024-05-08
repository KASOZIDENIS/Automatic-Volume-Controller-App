package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Parcelable.Creator<ConverterWrapper> {
    static void zza(ConverterWrapper converterWrapper, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, converterWrapper.getVersionCode());
        zzb.zza(parcel, 2, (Parcelable) converterWrapper.zzmt(), i, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzO */
    public ConverterWrapper createFromParcel(Parcel parcel) {
        int zzL = com.google.android.gms.common.internal.safeparcel.zza.zzL(parcel);
        int i = 0;
        StringToIntConverter stringToIntConverter = null;
        while (parcel.dataPosition() < zzL) {
            int zzK = com.google.android.gms.common.internal.safeparcel.zza.zzK(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzaV(zzK)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 2:
                    stringToIntConverter = (StringToIntConverter) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzK, StringToIntConverter.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new ConverterWrapper(i, stringToIntConverter);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaY */
    public ConverterWrapper[] newArray(int i) {
        return new ConverterWrapper[i];
    }
}
