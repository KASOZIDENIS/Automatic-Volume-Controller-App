package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Parcelable.Creator<DataHolder> {
    static void zza(DataHolder dataHolder, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zza(parcel, 1, dataHolder.zzlr(), false);
        zzb.zzc(parcel, 1000, dataHolder.getVersionCode());
        zzb.zza(parcel, 2, (T[]) dataHolder.zzls(), i, false);
        zzb.zzc(parcel, 3, dataHolder.getStatusCode());
        zzb.zza(parcel, 4, dataHolder.zzlm(), false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzC */
    public DataHolder createFromParcel(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzL = zza.zzL(parcel);
        CursorWindow[] cursorWindowArr = null;
        String[] strArr = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    strArr = zza.zzA(parcel, zzK);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) zza.zzb(parcel, zzK, CursorWindow.CREATOR);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 4:
                    bundle = zza.zzq(parcel, zzK);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() != zzL) {
            throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
        }
        DataHolder dataHolder = new DataHolder(i2, strArr, cursorWindowArr, i, bundle);
        dataHolder.zzlq();
        return dataHolder;
    }

    /* renamed from: zzaF */
    public DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
