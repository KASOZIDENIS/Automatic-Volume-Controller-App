package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class ConnectionResultCreator implements Parcelable.Creator<ConnectionResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(ConnectionResult connectionResult, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, connectionResult.zzzH);
        zzb.zzc(parcel, 2, connectionResult.getErrorCode());
        zzb.zza(parcel, 3, (Parcelable) connectionResult.getResolution(), i, false);
        zzb.zzH(parcel, zzM);
    }

    public ConnectionResult createFromParcel(Parcel parcel) {
        int i = 0;
        int zzL = zza.zzL(parcel);
        PendingIntent pendingIntent = null;
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
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzK, PendingIntent.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new ConnectionResult(i2, i, pendingIntent);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    public ConnectionResult[] newArray(int size) {
        return new ConnectionResult[size];
    }
}
