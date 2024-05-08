package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzkp extends zzki<zzkp> {
    private String zzHY;
    private String zzHZ;
    private String zzawA;
    private String zzawz;

    public void setAppId(String value) {
        this.zzawz = value;
    }

    public void setAppInstallerId(String value) {
        this.zzawA = value;
    }

    public void setAppName(String value) {
        this.zzHY = value;
    }

    public void setAppVersion(String value) {
        this.zzHZ = value;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("appName", this.zzHY);
        hashMap.put("appVersion", this.zzHZ);
        hashMap.put("appId", this.zzawz);
        hashMap.put("appInstallerId", this.zzawA);
        return zzu(hashMap);
    }

    public void zza(zzkp zzkp) {
        if (!TextUtils.isEmpty(this.zzHY)) {
            zzkp.setAppName(this.zzHY);
        }
        if (!TextUtils.isEmpty(this.zzHZ)) {
            zzkp.setAppVersion(this.zzHZ);
        }
        if (!TextUtils.isEmpty(this.zzawz)) {
            zzkp.setAppId(this.zzawz);
        }
        if (!TextUtils.isEmpty(this.zzawA)) {
            zzkp.setAppInstallerId(this.zzawA);
        }
    }

    public String zziE() {
        return this.zzHY;
    }

    public String zziG() {
        return this.zzHZ;
    }

    public String zzqT() {
        return this.zzawz;
    }

    public String zzus() {
        return this.zzawA;
    }
}
