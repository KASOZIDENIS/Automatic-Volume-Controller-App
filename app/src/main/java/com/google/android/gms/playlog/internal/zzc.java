package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Parcelable.Creator<LogEvent> {
    static void zza(LogEvent logEvent, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, logEvent.versionCode);
        zzb.zza(parcel, 2, logEvent.zzayr);
        zzb.zza(parcel, 3, logEvent.tag, false);
        zzb.zza(parcel, 4, logEvent.zzays, false);
        zzb.zza(parcel, 5, logEvent.zzayt, false);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzeO */
    public LogEvent createFromParcel(Parcel parcel) {
        Bundle bundle = null;
        int zzL = zza.zzL(parcel);
        int i = 0;
        long j = 0;
        byte[] bArr = null;
        String str = null;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzK);
                    break;
                case 3:
                    str = zza.zzo(parcel, zzK);
                    break;
                case 4:
                    bArr = zza.zzr(parcel, zzK);
                    break;
                case 5:
                    bundle = zza.zzq(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new LogEvent(i, j, str, bArr, bundle);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzhg */
    public LogEvent[] newArray(int i) {
        return new LogEvent[i];
    }
}
