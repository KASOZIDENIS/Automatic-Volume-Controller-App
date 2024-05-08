package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzo;

public class ResolveAccountResponse implements SafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new zzx();
    private boolean zzQe;
    private ConnectionResult zzRm;
    IBinder zzSS;
    private boolean zzUb;
    final int zzzH;

    public ResolveAccountResponse(int connectionResultStatusCode) {
        this(new ConnectionResult(connectionResultStatusCode, (PendingIntent) null));
    }

    ResolveAccountResponse(int versionCode, IBinder accountAccessorBinder, ConnectionResult connectionResult, boolean saveDefaultAccount, boolean isFromCrossClientAuth) {
        this.zzzH = versionCode;
        this.zzSS = accountAccessorBinder;
        this.zzRm = connectionResult;
        this.zzQe = saveDefaultAccount;
        this.zzUb = isFromCrossClientAuth;
    }

    public ResolveAccountResponse(ConnectionResult result) {
        this(1, (IBinder) null, result, false, false);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) o;
        return this.zzRm.equals(resolveAccountResponse.zzRm) && zzmm().equals(resolveAccountResponse.zzmm());
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzx.zza(this, dest, flags);
    }

    public zzo zzmm() {
        return zzo.zza.zzQ(this.zzSS);
    }

    public ConnectionResult zzmn() {
        return this.zzRm;
    }

    public boolean zzmo() {
        return this.zzQe;
    }

    public boolean zzmp() {
        return this.zzUb;
    }
}
