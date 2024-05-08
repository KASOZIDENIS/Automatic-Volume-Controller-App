package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ResolveAccountRequest implements SafeParcelable {
    public static final Parcelable.Creator<ResolveAccountRequest> CREATOR = new zzw();
    private final Account zzJc;
    private final int zzUa;
    final int zzzH;

    ResolveAccountRequest(int versionCode, Account account, int sessionId) {
        this.zzzH = versionCode;
        this.zzJc = account;
        this.zzUa = sessionId;
    }

    public ResolveAccountRequest(Account account, int sessionId) {
        this(1, account, sessionId);
    }

    public int describeContents() {
        return 0;
    }

    public Account getAccount() {
        return this.zzJc;
    }

    public int getSessionId() {
        return this.zzUa;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzw.zza(this, dest, flags);
    }
}
