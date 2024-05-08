package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class StatusCreator implements Parcelable.Creator<Status> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(Status status, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, status.getStatusCode());
        zzb.zzc(parcel, 1000, status.getVersionCode());
        zzb.zza(parcel, 2, status.getStatusMessage(), false);
        zzb.zza(parcel, 3, (Parcelable) status.zzlf(), i, false);
        zzb.zzH(parcel, zzM);
    }

    public Status createFromParcel(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int i = 0;
        int zzL = zza.zzL(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    str = zza.zzo(parcel, zzK);
                    break;
                case 3:
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzK, PendingIntent.CREATOR);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new Status(i2, i, str, pendingIntent);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    public Status[] newArray(int size) {
        return new Status[size];
    }
}
