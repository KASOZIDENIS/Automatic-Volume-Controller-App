package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzv;

public final class Scope implements SafeParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new zzj();
    private final String zzQT;
    final int zzzH;

    Scope(int versionCode, String scopeUri) {
        zzv.zzh(scopeUri, "scopeUri must not be null or empty");
        this.zzzH = versionCode;
        this.zzQT = scopeUri;
    }

    public Scope(String scopeUri) {
        this(1, scopeUri);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Scope)) {
            return false;
        }
        return this.zzQT.equals(((Scope) o).zzQT);
    }

    public int hashCode() {
        return this.zzQT.hashCode();
    }

    public String toString() {
        return this.zzQT;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzj.zza(this, dest, flags);
    }

    public String zzle() {
        return this.zzQT;
    }
}
