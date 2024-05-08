package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ValidateAccountRequest implements SafeParcelable {
    public static final Parcelable.Creator<ValidateAccountRequest> CREATOR = new zzab();
    final IBinder zzSS;
    private final Scope[] zzST;
    private final int zzUe;
    private final Bundle zzUf;
    private final String zzUg;
    final int zzzH;

    ValidateAccountRequest(int versionCode, int clientVersion, IBinder accountAccessorBinder, Scope[] scopes, Bundle extraArgs, String callingPackage) {
        this.zzzH = versionCode;
        this.zzUe = clientVersion;
        this.zzSS = accountAccessorBinder;
        this.zzST = scopes;
        this.zzUf = extraArgs;
        this.zzUg = callingPackage;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ValidateAccountRequest(zzo accountAccessor, Scope[] scopes, String callingPackage, Bundle extraArgs) {
        this(1, 7327000, accountAccessor == null ? null : accountAccessor.asBinder(), scopes, extraArgs, callingPackage);
    }

    public int describeContents() {
        return 0;
    }

    public String getCallingPackage() {
        return this.zzUg;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzab.zza(this, dest, flags);
    }

    public int zzmq() {
        return this.zzUe;
    }

    public Scope[] zzmr() {
        return this.zzST;
    }

    public Bundle zzms() {
        return this.zzUf;
    }
}
