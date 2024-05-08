package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzkw extends zzki<zzkw> {
    public String zzawU;
    public String zzawV;
    public String zzso;

    public String getAction() {
        return this.zzso;
    }

    public String getTarget() {
        return this.zzawV;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("network", this.zzawU);
        hashMap.put("action", this.zzso);
        hashMap.put("target", this.zzawV);
        return zzu(hashMap);
    }

    public void zza(zzkw zzkw) {
        if (!TextUtils.isEmpty(this.zzawU)) {
            zzkw.zzdf(this.zzawU);
        }
        if (!TextUtils.isEmpty(this.zzso)) {
            zzkw.zzdb(this.zzso);
        }
        if (!TextUtils.isEmpty(this.zzawV)) {
            zzkw.zzdg(this.zzawV);
        }
    }

    public void zzdb(String str) {
        this.zzso = str;
    }

    public void zzdf(String str) {
        this.zzawU = str;
    }

    public void zzdg(String str) {
        this.zzawV = str;
    }

    public String zzuS() {
        return this.zzawU;
    }
}
