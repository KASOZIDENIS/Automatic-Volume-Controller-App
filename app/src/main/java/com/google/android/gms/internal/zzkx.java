package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzkx extends zzki<zzkx> {
    public String mCategory;
    public String zzawK;
    public String zzawW;
    public long zzawX;

    public String getLabel() {
        return this.zzawK;
    }

    public long getTimeInMillis() {
        return this.zzawX;
    }

    public void setTimeInMillis(long milliseconds) {
        this.zzawX = milliseconds;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("variableName", this.zzawW);
        hashMap.put("timeInMillis", Long.valueOf(this.zzawX));
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzawK);
        return zzu(hashMap);
    }

    public void zza(zzkx zzkx) {
        if (!TextUtils.isEmpty(this.zzawW)) {
            zzkx.zzdh(this.zzawW);
        }
        if (this.zzawX != 0) {
            zzkx.setTimeInMillis(this.zzawX);
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzkx.zzda(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzawK)) {
            zzkx.zzdc(this.zzawK);
        }
    }

    public void zzda(String str) {
        this.mCategory = str;
    }

    public void zzdc(String str) {
        this.zzawK = str;
    }

    public void zzdh(String str) {
        this.zzawW = str;
    }

    public String zzuI() {
        return this.mCategory;
    }

    public String zzuT() {
        return this.zzawW;
    }
}
