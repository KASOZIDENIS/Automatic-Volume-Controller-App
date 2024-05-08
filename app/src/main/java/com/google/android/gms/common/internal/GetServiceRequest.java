package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzo;

public class GetServiceRequest implements SafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzh();
    final int version;
    final int zzTh;
    int zzTi;
    String zzTj;
    IBinder zzTk;
    Scope[] zzTl;
    Bundle zzTm;
    Account zzTn;

    public GetServiceRequest(int serviceId) {
        this.version = 2;
        this.zzTi = 7327000;
        this.zzTh = serviceId;
    }

    GetServiceRequest(int version2, int serviceId, int clientVersion, String callingPackage, IBinder accountAccessorBinder, Scope[] scopes, Bundle extraArgs, Account clientRequestedAccount) {
        this.version = version2;
        this.zzTh = serviceId;
        this.zzTi = clientVersion;
        this.zzTj = callingPackage;
        if (version2 < 2) {
            this.zzTn = zzP(accountAccessorBinder);
        } else {
            this.zzTk = accountAccessorBinder;
            this.zzTn = clientRequestedAccount;
        }
        this.zzTl = scopes;
        this.zzTm = extraArgs;
    }

    private Account zzP(IBinder iBinder) {
        if (iBinder != null) {
            return zza.zzb(zzo.zza.zzQ(iBinder));
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zzh.zza(this, dest, flags);
    }

    public GetServiceRequest zza(Scope[] scopeArr) {
        this.zzTl = scopeArr;
        return this;
    }

    public GetServiceRequest zzb(Account account) {
        this.zzTn = account;
        return this;
    }

    public GetServiceRequest zzbL(String str) {
        this.zzTj = str;
        return this;
    }

    public GetServiceRequest zzc(zzo zzo) {
        if (zzo != null) {
            this.zzTk = zzo.asBinder();
        }
        return this;
    }

    public GetServiceRequest zzf(Bundle bundle) {
        this.zzTm = bundle;
        return this;
    }
}
