package com.google.android.gms.common.server;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FavaDiagnosticsEntity implements SafeParcelable {
    public static final zza CREATOR = new zza();
    public final String zzUq;
    public final int zzUr;
    final int zzzH;

    public FavaDiagnosticsEntity(int versionCode, String namespace, int typeNum) {
        this.zzzH = versionCode;
        this.zzUq = namespace;
        this.zzUr = typeNum;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        zza.zza(this, out, flags);
    }
}
