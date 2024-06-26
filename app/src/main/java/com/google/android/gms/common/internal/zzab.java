package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzab implements Parcelable.Creator<ValidateAccountRequest> {
    static void zza(ValidateAccountRequest validateAccountRequest, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.zzzH);
        zzb.zzc(parcel, 2, validateAccountRequest.zzmq());
        zzb.zza(parcel, 3, validateAccountRequest.zzSS, false);
        zzb.zza(parcel, 4, (T[]) validateAccountRequest.zzmr(), i, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzms(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzJ */
    public ValidateAccountRequest createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzL = zza.zzL(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 3:
                    iBinder = zza.zzp(parcel, zzK);
                    break;
                case 4:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzK, Scope.CREATOR);
                    break;
                case 5:
                    bundle = zza.zzq(parcel, zzK);
                    break;
                case 6:
                    str = zza.zzo(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new ValidateAccountRequest(i2, i, iBinder, scopeArr, bundle, str);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaU */
    public ValidateAccountRequest[] newArray(int i) {
        return new ValidateAccountRequest[i];
    }
}
