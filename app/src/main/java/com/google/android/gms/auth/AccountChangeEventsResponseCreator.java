package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class AccountChangeEventsResponseCreator implements Parcelable.Creator<AccountChangeEventsResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(AccountChangeEventsResponse accountChangeEventsResponse, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, accountChangeEventsResponse.zzKu);
        zzb.zzc(parcel, 2, accountChangeEventsResponse.zznq, false);
        zzb.zzH(parcel, zzM);
    }

    public AccountChangeEventsResponse createFromParcel(Parcel parcel) {
        int zzL = zza.zzL(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    arrayList = zza.zzc(parcel, zzK, AccountChangeEvent.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new AccountChangeEventsResponse(i, arrayList);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    public AccountChangeEventsResponse[] newArray(int size) {
        return new AccountChangeEventsResponse[size];
    }
}
