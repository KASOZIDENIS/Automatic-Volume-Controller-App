package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AuthAccountResult implements SafeParcelable {
    public static final Parcelable.Creator<AuthAccountResult> CREATOR = new zza();
    final int zzzH;

    public AuthAccountResult() {
        this(1);
    }

    AuthAccountResult(int versionCode) {
        this.zzzH = versionCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zza.zza(this, dest, flags);
    }
}
