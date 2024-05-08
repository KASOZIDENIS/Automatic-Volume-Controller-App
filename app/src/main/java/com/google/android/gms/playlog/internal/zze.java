package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Parcelable.Creator<PlayLoggerContext> {
    static void zza(PlayLoggerContext playLoggerContext, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, playLoggerContext.versionCode);
        zzb.zza(parcel, 2, playLoggerContext.packageName, false);
        zzb.zzc(parcel, 3, playLoggerContext.zzayB);
        zzb.zzc(parcel, 4, playLoggerContext.zzayC);
        zzb.zza(parcel, 5, playLoggerContext.zzayD, false);
        zzb.zza(parcel, 6, playLoggerContext.zzayE, false);
        zzb.zza(parcel, 7, playLoggerContext.zzayF);
        zzb.zza(parcel, 8, playLoggerContext.zzayG, false);
        zzb.zza(parcel, 9, playLoggerContext.zzayH);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzeP */
    public PlayLoggerContext createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzL = zza.zzL(parcel);
        boolean z2 = true;
        String str2 = null;
        String str3 = null;
        int i = 0;
        int i2 = 0;
        String str4 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i3 = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    str4 = zza.zzo(parcel, zzK);
                    break;
                case 3:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                case 4:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 5:
                    str3 = zza.zzo(parcel, zzK);
                    break;
                case 6:
                    str2 = zza.zzo(parcel, zzK);
                    break;
                case 7:
                    z2 = zza.zzc(parcel, zzK);
                    break;
                case 8:
                    str = zza.zzo(parcel, zzK);
                    break;
                case 9:
                    z = zza.zzc(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new PlayLoggerContext(i3, str4, i2, i, str3, str2, z2, str, z);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzhh */
    public PlayLoggerContext[] newArray(int i) {
        return new PlayLoggerContext[i];
    }
}
