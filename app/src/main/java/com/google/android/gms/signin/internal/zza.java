package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Parcelable.Creator<AuthAccountResult> {
    static void zza(AuthAccountResult authAccountResult, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, authAccountResult.zzzH);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzfg */
    public AuthAccountResult createFromParcel(Parcel parcel) {
        int zzL = com.google.android.gms.common.internal.safeparcel.zza.zzL(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = com.google.android.gms.common.internal.safeparcel.zza.zzK(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzaV(zzK)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new AuthAccountResult(i);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzhz */
    public AuthAccountResult[] newArray(int i) {
        return new AuthAccountResult[i];
    }
}
