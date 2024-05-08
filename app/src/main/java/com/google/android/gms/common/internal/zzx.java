package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzx implements Parcelable.Creator<ResolveAccountResponse> {
    static void zza(ResolveAccountResponse resolveAccountResponse, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.zzzH);
        zzb.zza(parcel, 2, resolveAccountResponse.zzSS, false);
        zzb.zza(parcel, 3, (Parcelable) resolveAccountResponse.zzmn(), i, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzmo());
        zzb.zza(parcel, 5, resolveAccountResponse.zzmp());
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzI */
    public ResolveAccountResponse createFromParcel(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int zzL = zza.zzL(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            switch (zza.zzaV(zzK)) {
                case 1:
                    i = zza.zzg(parcel, zzK);
                    break;
                case 2:
                    iBinder = zza.zzp(parcel, zzK);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) zza.zza(parcel, zzK, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzK);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzK);
                    break;
                default:
                    zza.zzb(parcel, zzK);
                    break;
            }
        }
        if (parcel.dataPosition() == zzL) {
            return new ResolveAccountResponse(i, iBinder, connectionResult, z2, z);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaT */
    public ResolveAccountResponse[] newArray(int i) {
        return new ResolveAccountResponse[i];
    }
}
