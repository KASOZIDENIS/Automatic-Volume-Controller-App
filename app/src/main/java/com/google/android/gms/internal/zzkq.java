package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzkq extends zzki<zzkq> {
    private String mName;
    private String zzGM;
    private String zzarQ;
    private String zzawB;
    private String zzawC;
    private String zzawD;
    private String zzawE;
    private String zzawF;
    private String zzawG;
    private String zzsu;

    public String getContent() {
        return this.zzsu;
    }

    public String getId() {
        return this.zzGM;
    }

    public String getName() {
        return this.mName;
    }

    public String getSource() {
        return this.zzarQ;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.mName);
        hashMap.put("source", this.zzarQ);
        hashMap.put("medium", this.zzawB);
        hashMap.put("keyword", this.zzawC);
        hashMap.put("content", this.zzsu);
        hashMap.put("id", this.zzGM);
        hashMap.put("adNetworkId", this.zzawD);
        hashMap.put("gclid", this.zzawE);
        hashMap.put("dclid", this.zzawF);
        hashMap.put("aclid", this.zzawG);
        return zzu(hashMap);
    }

    public void zza(zzkq zzkq) {
        if (!TextUtils.isEmpty(this.mName)) {
            zzkq.setName(this.mName);
        }
        if (!TextUtils.isEmpty(this.zzarQ)) {
            zzkq.zzcR(this.zzarQ);
        }
        if (!TextUtils.isEmpty(this.zzawB)) {
            zzkq.zzcS(this.zzawB);
        }
        if (!TextUtils.isEmpty(this.zzawC)) {
            zzkq.zzcT(this.zzawC);
        }
        if (!TextUtils.isEmpty(this.zzsu)) {
            zzkq.zzcU(this.zzsu);
        }
        if (!TextUtils.isEmpty(this.zzGM)) {
            zzkq.zzcV(this.zzGM);
        }
        if (!TextUtils.isEmpty(this.zzawD)) {
            zzkq.zzcW(this.zzawD);
        }
        if (!TextUtils.isEmpty(this.zzawE)) {
            zzkq.zzcX(this.zzawE);
        }
        if (!TextUtils.isEmpty(this.zzawF)) {
            zzkq.zzcY(this.zzawF);
        }
        if (!TextUtils.isEmpty(this.zzawG)) {
            zzkq.zzcZ(this.zzawG);
        }
    }

    public void zzcR(String str) {
        this.zzarQ = str;
    }

    public void zzcS(String str) {
        this.zzawB = str;
    }

    public void zzcT(String str) {
        this.zzawC = str;
    }

    public void zzcU(String str) {
        this.zzsu = str;
    }

    public void zzcV(String str) {
        this.zzGM = str;
    }

    public void zzcW(String str) {
        this.zzawD = str;
    }

    public void zzcX(String str) {
        this.zzawE = str;
    }

    public void zzcY(String str) {
        this.zzawF = str;
    }

    public void zzcZ(String str) {
        this.zzawG = str;
    }

    public String zzut() {
        return this.zzawB;
    }

    public String zzuu() {
        return this.zzawC;
    }

    public String zzuv() {
        return this.zzawD;
    }

    public String zzuw() {
        return this.zzawE;
    }

    public String zzux() {
        return this.zzawF;
    }

    public String zzuy() {
        return this.zzawG;
    }
}
