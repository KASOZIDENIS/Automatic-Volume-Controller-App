package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.internal.zzv;

public class AccountChangeEvent implements SafeParcelable {
    public static final AccountChangeEventCreator CREATOR = new AccountChangeEventCreator();
    final int zzKu;
    final long zzKv;
    final String zzKw;
    final int zzKx;
    final int zzKy;
    final String zzKz;

    AccountChangeEvent(int version, long id, String accountName, int changeType, int eventIndex, String changeData) {
        this.zzKu = version;
        this.zzKv = id;
        this.zzKw = (String) zzv.zzr(accountName);
        this.zzKx = changeType;
        this.zzKy = eventIndex;
        this.zzKz = changeData;
    }

    public AccountChangeEvent(long id, String accountName, int changeType, int eventIndex, String changeData) {
        this.zzKu = 1;
        this.zzKv = id;
        this.zzKw = (String) zzv.zzr(accountName);
        this.zzKx = changeType;
        this.zzKy = eventIndex;
        this.zzKz = changeData;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) that;
        return this.zzKu == accountChangeEvent.zzKu && this.zzKv == accountChangeEvent.zzKv && zzu.equal(this.zzKw, accountChangeEvent.zzKw) && this.zzKx == accountChangeEvent.zzKx && this.zzKy == accountChangeEvent.zzKy && zzu.equal(this.zzKz, accountChangeEvent.zzKz);
    }

    public String getAccountName() {
        return this.zzKw;
    }

    public String getChangeData() {
        return this.zzKz;
    }

    public int getChangeType() {
        return this.zzKx;
    }

    public int getEventIndex() {
        return this.zzKy;
    }

    public int hashCode() {
        return zzu.hashCode(Integer.valueOf(this.zzKu), Long.valueOf(this.zzKv), this.zzKw, Integer.valueOf(this.zzKx), Integer.valueOf(this.zzKy), this.zzKz);
    }

    public String toString() {
        String str = "UNKNOWN";
        switch (this.zzKx) {
            case 1:
                str = "ADDED";
                break;
            case 2:
                str = "REMOVED";
                break;
            case 3:
                str = "RENAMED_FROM";
                break;
            case 4:
                str = "RENAMED_TO";
                break;
        }
        return "AccountChangeEvent {accountName = " + this.zzKw + ", changeType = " + str + ", changeData = " + this.zzKz + ", eventIndex = " + this.zzKy + "}";
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventCreator.zza(this, dest, flags);
    }
}
