package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Parcelable.Creator<ConnectionEvent> {
    static void zza(ConnectionEvent connectionEvent, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, connectionEvent.zzzH);
        zzb.zza(parcel, 2, connectionEvent.getTimeMillis());
        zzb.zza(parcel, 3, connectionEvent.zzmS(), false);
        zzb.zza(parcel, 4, connectionEvent.zzmT(), false);
        zzb.zza(parcel, 5, connectionEvent.zzmU(), false);
        zzb.zza(parcel, 6, connectionEvent.zzmV(), false);
        zzb.zza(parcel, 7, connectionEvent.zzmW(), false);
        zzb.zza(parcel, 8, connectionEvent.zzmX(), false);
        zzb.zza(parcel, 9, connectionEvent.zzmY());
        zzb.zza(parcel, 10, connectionEvent.zzna());
        zzb.zza(parcel, 11, connectionEvent.zzmZ());
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzW */
    public ConnectionEvent createFromParcel(Parcel parcel) {
        int zzL = com.google.android.gms.common.internal.safeparcel.zza.zzL(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = com.google.android.gms.common.internal.safeparcel.zza.zzK(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzaV(zzK)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    break;
                case 2:
                    j = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzK);
                    break;
                case 3:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 4:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 5:
                    str3 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 6:
                    str4 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 7:
                    str5 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 8:
                    str6 = com.google.android.gms.common.internal.safeparcel.zza.zzo(parcel, zzK);
                    break;
                case 9:
                    j2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzK);
                    break;
                case 10:
                    j3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzK);
                    break;
                case 11:
                    j4 = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzK);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new ConnectionEvent(i, j, str, str2, str3, str4, str5, str6, j2, j3, j4);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzbg */
    public ConnectionEvent[] newArray(int i) {
        return new ConnectionEvent[i];
    }
}
