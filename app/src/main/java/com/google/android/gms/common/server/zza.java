package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Parcelable.Creator<FavaDiagnosticsEntity> {
    static void zza(FavaDiagnosticsEntity favaDiagnosticsEntity, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, favaDiagnosticsEntity.zzzH);
        zzb.zza(parcel, 2, favaDiagnosticsEntity.zzUq, false);
        zzb.zzc(parcel, 3, favaDiagnosticsEntity.zzUr);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzN */
    public FavaDiagnosticsEntity createFromParcel(Parcel parcel) {
        int i = 0;
        int zzL = com.google.android.gms.common.internal.safeparcel.zza.zzL(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = com.google.android.gms.common.internal.safeparcel.zza.zzK(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzaV(zzK)) {
                case 1:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 2:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 3:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new FavaDiagnosticsEntity(i2, str, i);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaX */
    public FavaDiagnosticsEntity[] newArray(int i) {
        return new FavaDiagnosticsEntity[i];
    }
}
