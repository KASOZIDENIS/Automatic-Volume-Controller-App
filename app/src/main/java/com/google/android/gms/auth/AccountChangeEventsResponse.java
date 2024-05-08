package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzv;
import java.util.List;

public class AccountChangeEventsResponse implements SafeParcelable {
    public static final AccountChangeEventsResponseCreator CREATOR = new AccountChangeEventsResponseCreator();
    final int zzKu;
    final List<AccountChangeEvent> zznq;

    AccountChangeEventsResponse(int version, List<AccountChangeEvent> events) {
        this.zzKu = version;
        this.zznq = (List) zzv.zzr(events);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> events) {
        this.zzKu = 1;
        this.zznq = (List) zzv.zzr(events);
    }

    public int describeContents() {
        return 0;
    }

    public List<AccountChangeEvent> getEvents() {
        return this.zznq;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventsResponseCreator.zza(this, dest, flags);
    }
}
