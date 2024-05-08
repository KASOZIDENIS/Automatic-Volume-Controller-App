package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Parcelable.Creator<BitmapTeleporter> {
    static void zza(BitmapTeleporter bitmapTeleporter, Parcel parcel, int i) {
        int zzM = zzb.zzM(parcel);
        zzb.zzc(parcel, 1, bitmapTeleporter.zzzH);
        zzb.zza(parcel, 2, (Parcelable) bitmapTeleporter.zzzI, i, false);
        zzb.zzc(parcel, 3, bitmapTeleporter.zzMG);
        zzb.zzH(parcel, zzM);
    }

    /* renamed from: zzB */
    public BitmapTeleporter createFromParcel(Parcel parcel) {
        int zzg;
        ParcelFileDescriptor parcelFileDescriptor;
        int i;
        int i2 = 0;
        int zzL = com.google.android.gms.common.internal.safeparcel.zza.zzL(parcel);
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzL) {
            int zzK = com.google.android.gms.common.internal.safeparcel.zza.zzK(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzaV(zzK)) {
                case 1:
                    int i4 = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    zzg = i4;
                    break;
                case 2:
                    i = i3;
                    ParcelFileDescriptor parcelFileDescriptor3 = (ParcelFileDescriptor) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzK, ParcelFileDescriptor.CREATOR);
                    zzg = i2;
                    parcelFileDescriptor = parcelFileDescriptor3;
                    break;
                case 3:
                    zzg = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzK);
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzK);
                    zzg = i2;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    i = i3;
                    break;
            }
            i3 = i;
            parcelFileDescriptor2 = parcelFileDescriptor;
            i2 = zzg;
        }
        if (parcel.dataPosition() == zzL) {
            return new BitmapTeleporter(i3, parcelFileDescriptor2, i2);
        }
        throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
    }

    /* renamed from: zzaA */
    public BitmapTeleporter[] newArray(int i) {
        return new BitmapTeleporter[i];
    }
}
