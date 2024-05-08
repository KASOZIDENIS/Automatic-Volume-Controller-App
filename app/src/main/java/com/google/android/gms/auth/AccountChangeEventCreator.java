package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class AccountChangeEventCreator implements Parcelable.Creator<AccountChangeEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(AccountChangeEvent accountChangeEvent, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, accountChangeEvent.zzKu);
        zzb.zza(parcel, 2, accountChangeEvent.zzKv);
        zzb.zza(parcel, 3, accountChangeEvent.zzKw, false);
        zzb.zzc(parcel, 4, accountChangeEvent.zzKx);
        zzb.zzc(parcel, 5, accountChangeEvent.zzKy);
        zzb.zza(parcel, 6, accountChangeEvent.zzKz, false);
        zzb.zzH(parcel, zzM);
    }

    public AccountChangeEvent createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzL = zza.zzL(parcel);
        long j = 0;
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i3 = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzK);
                    break;
                case 3:
                    str2 = zza.zzo(parcel, zzK);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                case 5:
                    i = zza.zzg(parcel, zzK);
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
            return new AccountChangeEvent(i3, j, str2, i2, i, str);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    public AccountChangeEvent[] newArray(int size) {
        return new AccountChangeEvent[size];
    }
}
