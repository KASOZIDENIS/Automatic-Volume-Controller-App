package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.server.response.FieldMappingDictionary;
import java.util.ArrayList;

public class zzd implements Parcelable.Creator<FieldMappingDictionary.Entry> {
    static void zza(FieldMappingDictionary.Entry entry, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, entry.versionCode);
        zzb.zza(parcel, 2, entry.className, false);
        zzb.zzc(parcel, 3, entry.zzUL, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzU */
    public FieldMappingDictionary.Entry createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
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
                    arrayList = zza.zzc(parcel, zzK, FieldMappingDictionary.FieldMapPair.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new FieldMappingDictionary.Entry(i, str, arrayList);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzbe */
    public FieldMappingDictionary.Entry[] newArray(int i) {
        return new FieldMappingDictionary.Entry[i];
    }
}
