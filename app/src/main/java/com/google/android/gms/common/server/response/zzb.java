package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FieldMappingDictionary;

public class zzb implements Parcelable.Creator<FieldMappingDictionary.FieldMapPair> {
    static void zza(FieldMappingDictionary.FieldMapPair fieldMapPair, Parcel parcel, int i) {
        int zzM = com.google.android.gms.common.internal.safeparcel.zzb.zzM(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, fieldMapPair.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, fieldMapPair.zzgk, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable) fieldMapPair.zzUM, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzS */
    public FieldMappingDictionary.FieldMapPair createFromParcel(Parcel parcel) {
        FastJsonResponse.Field field = null;
        int zzL = zza.zzL(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    str = zza.zzo(parcel, zzK);
                    break;
                case 3:
                    field = (FastJsonResponse.Field) zza.zza(parcel, zzK, FastJsonResponse.Field.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new FieldMappingDictionary.FieldMapPair(i, str, field);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzbc */
    public FieldMappingDictionary.FieldMapPair[] newArray(int i) {
        return new FieldMappingDictionary.FieldMapPair[i];
    }
}
