package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzo;

public class zza extends zzo.zza {
    private Context mContext;
    private Account zzJc;
    int zzSR;

    public static Account zzb(zzo zzo) {
        Account account = null;
        if (zzo != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = zzo.getAccount();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
        return account;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof zza)) {
            return false;
        }
        return this.zzJc.equals(((zza) o).zzJc);
    }

    public Account getAccount() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.zzSR) {
            return this.zzJc;
        }
        if (GooglePlayServicesUtil.zzd(this.mContext, callingUid)) {
            this.zzSR = callingUid;
            return this.zzJc;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }
}
