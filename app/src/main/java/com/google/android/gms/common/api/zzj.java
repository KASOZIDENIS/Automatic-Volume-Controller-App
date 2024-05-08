package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj implements Parcelable.Creator<Scope> {
    static void zza(Scope scope, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, scope.zzzH);
        zzb.zza(parcel, 2, scope.zzle(), false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzA */
    public Scope createFromParcel(Parcel parcel) {
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
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new Scope(i, str);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaw */
    public Scope[] newArray(int i) {
        return new Scope[i];
    }
}
