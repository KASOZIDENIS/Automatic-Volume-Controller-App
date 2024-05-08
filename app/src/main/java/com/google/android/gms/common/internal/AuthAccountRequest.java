package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Set;

public class AuthAccountRequest implements SafeParcelable {
    public static final Parcelable.Creator<AuthAccountRequest> CREATOR = new zzc();
    final IBinder zzSS;
    final Scope[] zzST;
    final int zzzH;

    AuthAccountRequest(int versionCode, IBinder accountAccessorBinder, Scope[] scopes) {
        this.zzzH = versionCode;
        this.zzSS = accountAccessorBinder;
        this.zzST = scopes;
    }

    public AuthAccountRequest(zzo accountAccessor, Set<Scope> scopes) {
        this(1, accountAccessor.asBinder(), (Scope[]) scopes.toArray(new Scope[scopes.size()]));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzc.zza(this, dest, flags);
    }
}
