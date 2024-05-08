package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Parcelable.Creator<GetServiceRequest> {
    static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzTh);
        zzb.zzc(parcel, 3, getServiceRequest.zzTi);
        zzb.zza(parcel, 4, getServiceRequest.zzTj, false);
        zzb.zza(parcel, 5, getServiceRequest.zzTk, false);
        zzb.zza(parcel, 6, (T[]) getServiceRequest.zzTl, i, false);
        zzb.zza(parcel, 7, getServiceRequest.zzTm, false);
        zzb.zza(parcel, 8, (Parcelable) getServiceRequest.zzTn, i, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzG */
    public GetServiceRequest createFromParcel(Parcel parcel) {
        int i = 0;
        Account account = null;
        int zzL = zza.zzL(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i3 = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 4:
                    str = zza.zzo(parcel, zzK);
                    break;
                case 5:
                    iBinder = zza.zzp(parcel, zzK);
                    break;
                case 6:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzK, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zza.zzq(parcel, zzK);
                    break;
                case 8:
                    account = (Account) zza.zza(parcel, zzK, Account.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaN */
    public GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
