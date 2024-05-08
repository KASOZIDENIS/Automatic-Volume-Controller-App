package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class AccountChangeEventsRequest implements SafeParcelable {
    public static final AccountChangeEventsRequestCreator CREATOR = new AccountChangeEventsRequestCreator();
    Account zzJc;
    final int zzKu;
    @Deprecated
    String zzKw;
    int zzKy;

    public AccountChangeEventsRequest() {
        this.zzKu = 1;
    }

    AccountChangeEventsRequest(int version, int eventIndex, String accountName, Account account) {
        this.zzKu = version;
        this.zzKy = eventIndex;
        this.zzKw = accountName;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.zzJc = account;
        } else {
            this.zzJc = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        }
    }

    public int describeContents() {
        return 0;
    }

    public Account getAccount() {
        return this.zzJc;
    }

    public String getAccountName() {
        return this.zzKw;
    }

    public int getEventIndex() {
        return this.zzKy;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzJc = account;
        return this;
    }

    public AccountChangeEventsRequest setAccountName(String accountName) {
        this.zzKw = accountName;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int eventIndex) {
        this.zzKy = eventIndex;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventsRequestCreator.zza(this, dest, flags);
    }
}
