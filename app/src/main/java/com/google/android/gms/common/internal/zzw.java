package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzw implements Parcelable.Creator<ResolveAccountRequest> {
    static void zza(ResolveAccountRequest resolveAccountRequest, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, resolveAccountRequest.zzzH);
        zzb.zza(parcel, 2, (Parcelable) resolveAccountRequest.getAccount(), i, false);
        zzb.zzc(parcel, 3, resolveAccountRequest.getSessionId());
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzH */
    public ResolveAccountRequest createFromParcel(Parcel parcel) {
        int zzg;
        Account account;
        int i;
        int i2 = 0;
        int zzL = zza.zzL(parcel);
        Account account2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    int i4 = i2;
                    account = account2;
                    i = zza.zzg(parcel, zzK);
                    zzg = i4;
                    break;
                case 2:
                    i = i3;
                    Account account3 = (Account) zza.zza(parcel, zzK, Account.CREATOR);
                    zzg = i2;
                    account = account3;
                    break;
                case 3:
                    zzg = zza.zzg(parcel, zzK);
                    account = account2;
                    i = i3;
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    zzg = i2;
                    account = account2;
                    i = i3;
                    break;
            }
            i3 = i;
            account2 = account;
            i2 = zzg;
        }
        if (parcel.dataPosition() == zzL) {
            return new ResolveAccountRequest(i3, account2, i2);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaS */
    public ResolveAccountRequest[] newArray(int i) {
        return new ResolveAccountRequest[i];
    }
}
