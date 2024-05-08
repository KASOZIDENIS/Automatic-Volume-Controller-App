package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzc implements Parcelable.Creator<CheckServerAuthResult> {
    static void zza(CheckServerAuthResult checkServerAuthResult, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.zzzH);
        zzb.zza(parcel, 2, checkServerAuthResult.zzaBL);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzaBM, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzfh */
    public CheckServerAuthResult createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzL = zza.zzL(parcel);
        ArrayList<Scope> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    z = zza.zzc(parcel, zzK);
                    break;
                case 3:
                    arrayList = zza.zzc(parcel, zzK, Scope.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new CheckServerAuthResult(i, z, arrayList);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzhA */
    public CheckServerAuthResult[] newArray(int i) {
        return new CheckServerAuthResult[i];
    }
}
