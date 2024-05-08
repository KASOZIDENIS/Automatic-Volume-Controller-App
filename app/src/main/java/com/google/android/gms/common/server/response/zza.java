package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.server.response.FastJsonResponse;

public class zza implements Parcelable.Creator<FastJsonResponse.Field> {
    static void zza(FastJsonResponse.Field field, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, field.getVersionCode());
        zzb.zzc(parcel, 2, field.zzmw());
        zzb.zza(parcel, 3, field.zzmC());
        zzb.zzc(parcel, 4, field.zzmx());
        zzb.zza(parcel, 5, field.zzmD());
        zzb.zza(parcel, 6, field.zzmE(), false);
        zzb.zzc(parcel, 7, field.zzmF());
        zzb.zza(parcel, 8, field.zzmH(), false);
        zzb.zza(parcel, 9, (Parcelable) field.zzmJ(), i, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzR */
    public FastJsonResponse.Field createFromParcel(Parcel parcel) {
        ConverterWrapper converterWrapper = null;
        int i = 0;
        int zzL = com.google.android.gms.common.internal.safeparcel.zza.zzL(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = com.google.android.gms.common.internal.safeparcel.zza.zzK(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzaV(zzK)) {
                case 1:
                    i4 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 2:
                    i3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 3:
                    z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzK);
                    break;
                case 4:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 5:
                    z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzK);
                    break;
                case 6:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 7:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 8:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 9:
                    converterWrapper = (ConverterWrapper) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzK, ConverterWrapper.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new FastJsonResponse.Field(i4, i3, z2, i2, z, str2, i, str, converterWrapper);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzbb */
    public FastJsonResponse.Field[] newArray(int i) {
        return new FastJsonResponse.Field[i];
    }
}
