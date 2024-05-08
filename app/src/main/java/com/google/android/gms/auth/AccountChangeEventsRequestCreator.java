package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class AccountChangeEventsRequestCreator implements Parcelable.Creator<AccountChangeEventsRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(AccountChangeEventsRequest accountChangeEventsRequest, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, accountChangeEventsRequest.zzKu);
        zzb.zzc(parcel, 2, accountChangeEventsRequest.zzKy);
        zzb.zza(parcel, 3, accountChangeEventsRequest.zzKw, false);
        zzb.zza(parcel, 4, (Parcelable) accountChangeEventsRequest.zzJc, i, false);
        zzb.zzH(parcel, zzM);
    }

    public AccountChangeEventsRequest createFromParcel(Parcel parcel) {
        Account account = null;
        int i = 0;
        int zzL = zza.zzL(parcel);
        String str = null;
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
                    str = zza.zzo(parcel, zzK);
                    break;
                case 4:
                    account = (Account) zza.zza(parcel, zzK, Account.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new AccountChangeEventsRequest(i2, i, str, account);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    public AccountChangeEventsRequest[] newArray(int size) {
        return new AccountChangeEventsRequest[size];
    }
}
