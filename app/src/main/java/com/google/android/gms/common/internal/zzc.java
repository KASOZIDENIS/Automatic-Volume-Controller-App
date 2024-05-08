package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Parcelable.Creator<AuthAccountRequest> {
    static void zza(AuthAccountRequest authAccountRequest, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, authAccountRequest.zzzH);
        zzb.zza(parcel, 2, authAccountRequest.zzSS, false);
        zzb.zza(parcel, 3, (T[]) authAccountRequest.zzST, i, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzE */
    public AuthAccountRequest createFromParcel(Parcel parcel) {
        Scope[] scopeArr = null;
        int zzL = zza.zzL(parcel);
        int i = 0;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    iBinder = zza.zzp(parcel, zzK);
                    break;
                case 3:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzK, Scope.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new AuthAccountRequest(i, iBinder, scopeArr);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaL */
    public AuthAccountRequest[] newArray(int i) {
        return new AuthAccountRequest[i];
    }
}
