package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.server.response.FieldMappingDictionary;
import java.util.ArrayList;

public class zzc implements Parcelable.Creator<FieldMappingDictionary> {
    static void zza(FieldMappingDictionary fieldMappingDictionary, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, fieldMappingDictionary.getVersionCode());
        zzb.zzc(parcel, 2, fieldMappingDictionary.zzmN(), false);
        zzb.zza(parcel, 3, fieldMappingDictionary.zzmO(), false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzT */
    public FieldMappingDictionary createFromParcel(Parcel parcel) {
        String str = null;
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
                    arrayList = zza.zzc(parcel, zzK, FieldMappingDictionary.Entry.CREATOR);
                    break;
                case 3:
                    str = zza.zzo(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new FieldMappingDictionary(i, arrayList, str);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzbd */
    public FieldMappingDictionary[] newArray(int i) {
        return new FieldMappingDictionary[i];
    }
}
