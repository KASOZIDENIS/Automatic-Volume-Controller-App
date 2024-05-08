package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

public class ConverterWrapper implements SafeParcelable {
    public static final zza CREATOR = new zza();
    private final StringToIntConverter zzUs;
    private final int zzzH;

    ConverterWrapper(int versionCode, StringToIntConverter stringToIntConverter) {
        this.zzzH = versionCode;
        this.zzUs = stringToIntConverter;
    }

    private ConverterWrapper(StringToIntConverter stringToIntConverter) {
        this.zzzH = 1;
        this.zzUs = stringToIntConverter;
    }

    public static ConverterWrapper zza(FastJsonResponse.zza<?, ?> zza) {
        if (zza instanceof StringToIntConverter) {
            return new ConverterWrapper((StringToIntConverter) zza);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public int describeContents() {
        zza zza = CREATOR;
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.zzzH;
    }

    public void writeToParcel(Parcel out, int flags) {
        zza zza = CREATOR;
        zza.zza(this, out, flags);
    }

    /* access modifiers changed from: package-private */
    public StringToIntConverter zzmt() {
        return this.zzUs;
    }

    public FastJsonResponse.zza<?, ?> zzmu() {
        if (this.zzUs != null) {
            return this.zzUs;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
